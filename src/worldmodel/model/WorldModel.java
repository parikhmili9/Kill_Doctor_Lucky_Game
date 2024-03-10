package worldmodel.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import worldmodel.character.GameCharacter;
import worldmodel.character.Pet;
import worldmodel.character.Player;
import worldmodel.character.PlayerInterface;
import worldmodel.character.TargetCharacter;
import worldmodel.character.TargetInterface;
import worldmodel.gameprop.GameProp;
import worldmodel.gameprop.Weapon;
import worldmodel.room.Room;
import worldmodel.room.RoomImplementation;

/**
 * WorldModel is the model class implementation of this game.
 * It provides all the functionalities required to play the game which includes
 * creation of the world, graphical representation of the world, display information
 * about its players and rooms, providing different playing options for the players 
 * in the game.
 *
 * @author shreyas.terdalkar
 * 
 */
public class WorldModel implements WorldInterface {
  
  private int noOfTurns;
  private int turn;
  private String worldName;
  private int dimensionX;
  private int dimensionY;
  private int noOfRooms;
  private int noOfWeapons;
  private final String filePath;
  private final Map<String, Room> worldMap;
  private final Map<Integer, String> roomMap;
  private final Map<String, GameProp> weaponsMap;
  private final Map<String, GameCharacter> gameCharacterMap;
  private final Map<String, PlayerInterface> playerMap;
  private final Map<String, TargetInterface> targetMap;
  private final Map<Integer, String> characterMap;
  private PlayerInterface turnOwner;
  private PlayerInterface winner;
  private RandomInterface randomObject;
  private int isMockedNum;
  private String statusMessage;
  private int turnsLeft;
  
  /**
   * This constructor method initializes WorldModel class 
   * by reading the World Specification text file.
   *
   * @throws FileNotFoundException if file path not found
   * @throws IllegalArgumentException if no argument passed.
   */
  public WorldModel(Map<String, String> configuration) {
    
    if (configuration == null || configuration.isEmpty()) {
      throw new IllegalArgumentException("input cannot be null");
    }
    
    if (configuration.get("filePath") == null) {
      throw new IllegalArgumentException("No path received");
    }
    this.filePath = configuration.get("filePath");
    
    if (configuration.get("noOfTurnsString") == null) {
      throw new IllegalArgumentException("number of turns cannot be null");
    }
    this.noOfTurns = 0;
    
    if (configuration.get("isMocked") == null) {
      throw new IllegalArgumentException("No value received");
    }
    
    try {
      this.noOfTurns = Integer.parseInt(configuration.get("noOfTurnsString"));
      this.isMockedNum = Integer.parseInt(configuration.get("isMocked"));
    } catch (NumberFormatException nfe) {
      throw new IllegalArgumentException("The no of turns is not in the integer format");
    }
    this.turnsLeft = this.noOfTurns;
    
    this.worldMap = new HashMap<String, Room>();
    this.roomMap = new HashMap<Integer, String>();
    this.weaponsMap = new HashMap<String, GameProp>();
    this.gameCharacterMap = new HashMap<String, GameCharacter>();
    this.characterMap = new HashMap<Integer, String>();
    this.playerMap = new HashMap<String, PlayerInterface>();
    this.targetMap = new HashMap<String, TargetInterface>();
    this.turnOwner = null;
    this.winner = null;
    
    if (isMockedNum <= 0) {
      this.randomObject = new RandomGenerator();
    } else {
      this.randomObject = new RandomGenerator(isMockedNum);
    }
    
    parseWorld();
    
  }
  
  /**
   * Helper method parseWorld helps in parsing the world specification file
   * and initializing essential data structures to store objects of rooms,
   * weapons, characters in the game.
   */
  private void parseWorld() {
    
    Readable specificationFile;
    try {
      specificationFile = new FileReader(this.filePath);
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("No such file found");
    }
    
    Scanner scanner1 = new Scanner(specificationFile);
    List<String> specificationList = new ArrayList<String>();
    while (scanner1.hasNextLine()) {
      specificationList.add(scanner1.nextLine());
    }
    scanner1.close();
    
    //line referring to the world information
    int lineWorldInfo = 0;
    scanner1 = new Scanner(specificationList.get(lineWorldInfo));
    this.dimensionY = scanner1.nextInt();
    this.dimensionX = scanner1.nextInt();
    this.worldName = scanner1.nextLine().substring(1);
    scanner1.close();
    
    //line referring to target character info
    int lineTargetInfo = 1;
    int targetCharacterHealth;
    String targetCharacterName;
    scanner1 = new Scanner(specificationList.get(lineTargetInfo));
    targetCharacterHealth = scanner1.nextInt();
    targetCharacterName = scanner1.nextLine().substring(1);
    //Target character enters in room with index 0 and has player rank 0
    TargetInterface newCharacter = new TargetCharacter(0, 0, 
        targetCharacterHealth, targetCharacterName);
    gameCharacterMap.put(targetCharacterName, newCharacter);
    targetMap.put(targetCharacterName, newCharacter);
    //Target character has player rank 0
    characterMap.put(0, targetCharacterName);
    scanner1.close();
    
    //Line referring to pet information
    int linePetInfo = 2;
    String petCharacterName;
    scanner1 = new Scanner(specificationList.get(linePetInfo));
    petCharacterName = scanner1.nextLine();
    //The pet enters in room with index 0 and has player rank 1
    GameCharacter petCharacter = new Pet(petCharacterName, 0, 1);
    gameCharacterMap.put(petCharacterName, petCharacter);
    //Pet has player rank 1
    characterMap.put(1, petCharacterName);
    scanner1.close();
    
    //Line referring to no of rooms in the world
    int lineRoomCount = 3;
    scanner1 = new Scanner(specificationList.get(lineRoomCount));
    this.noOfRooms = scanner1.nextInt();
    scanner1.close();

    String roomName;
    int roomIndex;
    int topWall;
    int leftWall;
    int bottomWall;
    int rightWall;
    int lineRoomOne = lineRoomCount + 1;
    int lineRoomLast = lineRoomCount + noOfRooms;
    for (int m = lineRoomOne; m <= lineRoomLast; m++) {
      scanner1 = new Scanner(specificationList.get(m));
      topWall = scanner1.nextInt();
      leftWall = scanner1.nextInt();
      bottomWall = scanner1.nextInt();
      rightWall = scanner1.nextInt();
      roomName = scanner1.nextLine().substring(1);
      //Offsetting room index with respect to line no
      roomIndex = m - 4;
      Room newroom = new RoomImplementation(roomIndex, roomName, 
          leftWall, rightWall, topWall, bottomWall);
      worldMap.put(roomName, newroom);
      roomMap.put(roomIndex, roomName);
      scanner1.close();
    }
    //Adding target character to room 0
    worldMap.get(roomMap.get(gameCharacterMap.get(targetCharacterName)
        .getLocation())).addTarget(targetCharacterName);
    //Adding pet to room 0
    worldMap.get(roomMap.get(gameCharacterMap.get(petCharacterName)
        .getLocation())).addPet(petCharacterName);
    //Initializing turn as Target and its pet has arrived in the game
    this.turn = 1;
    
    //Line referring to no of weapons in the world
    int lineWeaponCount = lineRoomLast + 1;
    scanner1 = new Scanner(specificationList.get(lineWeaponCount));
    this.noOfWeapons = scanner1.nextInt();
    scanner1.close();

    int lineWeaponOne = lineWeaponCount + 1;
    int lineWeaponLast = lineWeaponCount + noOfWeapons;
    int weaponRoomIndex;
    int weaponPower;
    String weaponName;
    for (int m = lineWeaponOne; m <= lineWeaponLast; m++) {
      scanner1 = new Scanner(specificationList.get(m));
      weaponRoomIndex = scanner1.nextInt();
      weaponPower = scanner1.nextInt();
      weaponName = scanner1.nextLine().substring(1);
      GameProp newWeapon = new Weapon(roomMap.get(weaponRoomIndex), weaponPower, weaponName);
      weaponsMap.put(weaponName, newWeapon);
      //Adding weapon to the room
      worldMap.get(roomMap.get(weaponRoomIndex)).addWeapon(weaponName);
    }   
    scanner1.close();
    
    this.statusMessage = "Welcome to the world of " + this.worldName;
  }
  
  @Override
  public BufferedImage getWorldGraphicImage() {
    BufferedImage image = new BufferedImage(dimensionX * 50, dimensionY * 50, 
        BufferedImage.TYPE_INT_RGB);
    Graphics2D graphicImage = (Graphics2D) image.getGraphics();
    graphicImage.setColor(Color.WHITE);
    graphicImage.fillRect(0, 0, image.getWidth(), image.getHeight());
    
    int x1;
    int y1;
    int x2;
    int y2;
    for (String i : worldMap.keySet()) {
      x1 = worldMap.get(i).getX1();
      y1 = worldMap.get(i).getY1();
      x2 = worldMap.get(i).getX2();
      y2 = worldMap.get(i).getY2();
      graphicImage.setColor(Color.BLACK);
      graphicImage.drawRect(y1 * 30, x1 * 30, (y2 - y1) * 30, (x2 - x1) * 30);
      graphicImage.drawString(worldMap.get(i).getRoomName(), (y1 * 30) + 20, (x1 * 30) + 20);
    }
    
    //File worldSpecification = new File("././res/Images/finalImage.png");
    //try {
    //  ImageIO.write(image, "png", worldSpecification);
    //} catch (IOException e) {
    //  throw new IllegalArgumentException("format of the file is incorrect");
    //}
    return image;
  }
  
  /**
   * Helper method getNeighbour helps in finding neighbours of its current room.
   *
   * @param roomName represents the name of current room
   * @return list of all neighbours of current room
   */
  private List<String> getNeighbour(String roomName) {
    
    int thisX1 = worldMap.get(roomName).getX1();
    int thisY1 = worldMap.get(roomName).getY1();
    int thisX2 = worldMap.get(roomName).getX2();
    int thisY2 = worldMap.get(roomName).getY2();
    int otherX1;
    int otherY1;
    int otherX2;
    int otherY2;
    List<String> neighbours = new ArrayList<String>();
    
    for (String i : worldMap.keySet()) {
      
      if (i != roomName) {
        
        otherX1 = worldMap.get(i).getX1();
        otherY1 = worldMap.get(i).getY1();
        otherX2 = worldMap.get(i).getX2();
        otherY2 = worldMap.get(i).getY2();
          
        if (thisX1 == otherX2) {
          if ((otherY1 <= thisY1 && thisY1 <= otherY2) 
              || (otherY1 <= thisY2 && thisY2 <= otherY2) 
              || (thisY1 < otherY1 && otherY2 < thisY2)) {
            neighbours.add(worldMap.get(i).getRoomName());
          }
        }
          
        if (thisY1 == otherY2) {
          if ((otherX1 <= thisX1 && thisX1 <= otherX2) 
              || (otherX1 <= thisX2 && thisX2 <= otherX2)
              || (thisX1 < otherX1 && otherX2 < thisX2)) {
            neighbours.add(worldMap.get(i).getRoomName());
          }
            
        }
          
        if (thisX2 == otherX1) {
          if ((otherY1 <= thisY1 && thisY1 <= otherY2) 
              || (otherY1 <= thisY2 && thisY2 <= otherY2)
              || (thisY1 < otherY1 && otherY2 < thisY2)) {
            neighbours.add(worldMap.get(i).getRoomName());
          }
        }
          
        if (thisY2 == otherY1) {
          if ((otherX1 <= thisX1 && thisX1 <= otherX2) 
              || (otherX1 <= thisX2 && thisX2 <= otherX2)
              || (thisX1 < otherX1 && otherX2 < thisX2)) {
            neighbours.add(worldMap.get(i).getRoomName());
          }
        }
          
      }
    }
    return neighbours;
  }
  
  @Override
  public void addPlayer(String playerName, String playerRoomName, boolean isHuman) {
    
    if (playerName == null || playerRoomName == null
        || "".equals(playerName) || "".equals(playerRoomName)) {
      throw new IllegalArgumentException("Player name cannot be null");
    }
    
    boolean playerExists = false;
    for (String eachPlayerName : gameCharacterMap.keySet()) {
      if (eachPlayerName.equals(playerName)) {
        playerExists = true;
        break;
      }
    }
    if (playerExists == true) {
      throw new IllegalArgumentException("Player already exists");
    }
    
    boolean roomExists = false;
    for (String eachRoomName : worldMap.keySet()) {
      if (eachRoomName.equals(playerRoomName)) {
        roomExists = true;
        break;
      }
    }
    
    if (roomExists == false) {
      throw new IllegalArgumentException("Room does not exist");
    }
    
    int currentRank = characterMap.keySet().size();
    PlayerInterface newCharacter = new Player(currentRank, playerName, 
        worldMap.get(playerRoomName).getRoomIndex(), isHuman);
    characterMap.put(currentRank, playerName);
    gameCharacterMap.put(playerName, newCharacter);
    playerMap.put(playerName, newCharacter);
    worldMap.get(playerRoomName).addPlayer(playerName);
    
    //Giving first turn to first player in the game
    if (currentRank == 2) {
      this.turnOwner = newCharacter;
    }
    String output = "Player " + playerName 
        + " has been created.\n" + getRoomInfo(playerRoomName);
    this.statusMessage = output;
    
  }

  @Override
  public String getRoomInfo(String roomName) {
    
    if (roomName == null || "".equals(roomName)) {
      throw new IllegalArgumentException("Room name cannot be null");
    }
    
    boolean roomExists = false;
    for (String eachRoomName : worldMap.keySet()) {
      if (eachRoomName.equals(roomName)) {
        roomExists = true;
        break;
      }
    }
    if (roomExists == false) {
      throw new IllegalArgumentException("Room does not exist");
    }
    
    StringBuilder output = new StringBuilder();
    
    if (worldMap.get(roomName).hasWeapon()) {
      output.append("Weapon/s in the room : " 
          + worldMap.get(roomName).getWeapon().toString() + "\n");
    }
    
    if (worldMap.get(roomName).hasTarget()) {
      output.append("Target in the room : " + worldMap.get(roomName).getTarget() + "\n");
    }
    
    if (worldMap.get(roomName).hasPet()) {
      output.append("Pet in the room : " + worldMap.get(roomName).getPet() + "\n");
    }
    
    if (worldMap.get(roomName).hasPlayer()) {
      output.append("Player/s in the room : " 
          + worldMap.get(roomName).getPlayer().toString() + "\n");
    }
    
    StringBuilder output1 = new StringBuilder();
    output1.append("Name of the room : ").append(roomName).append("\n")
      .append("Neighbours of the room : ").append(getNeighbour(roomName).toString())
      .append("\n").append(output);
    return output1.toString();
  }

  @Override
  public Map<Integer, Map<String, List<String>>> getRoomDetails() {

    Map<Integer, Map<String, List<String>>> roomInfoMap = new HashMap<Integer, 
        Map<String, List<String>>>();

    for (int eachRoomIndex = 0; eachRoomIndex < this.noOfRooms; eachRoomIndex++) {

      if (worldMap.get(roomMap.get(eachRoomIndex)).hasPet()) {
        roomInfoMap.put(eachRoomIndex, null);
      } else {

        Map<String, List<String>> eachRoomInfoMap = new HashMap<String, List<String>>();
        List<String> roomName = new ArrayList<String>();
        List<String> roomCoordinates = new ArrayList<String>();
        List<String> roomWeapons = new ArrayList<String>();
        List<String> roomPlayers = new ArrayList<String>();
        List<String> roomTarget = new ArrayList<String>();

        roomName.add(roomMap.get(eachRoomIndex));
        eachRoomInfoMap.put("RoomName", roomName);

        roomCoordinates.add(Integer.toString(worldMap.get(roomMap.get(eachRoomIndex)).getX1()));
        roomCoordinates.add(Integer.toString(worldMap.get(roomMap.get(eachRoomIndex)).getY1()));
        roomCoordinates.add(Integer.toString(worldMap.get(roomMap.get(eachRoomIndex)).getX2()));
        roomCoordinates.add(Integer.toString(worldMap.get(roomMap.get(eachRoomIndex)).getY2()));
        eachRoomInfoMap.put("RoomCoordinates", roomCoordinates);

        if (worldMap.get(roomMap.get(eachRoomIndex)).hasWeapon()) {
          roomWeapons = worldMap.get(roomMap.get(eachRoomIndex)).getWeapon();
        } else {
          roomWeapons = null;
        }
        eachRoomInfoMap.put("RoomWeapons", roomWeapons);

        if (worldMap.get(roomMap.get(eachRoomIndex)).hasTarget()) {
          roomTarget.add(worldMap.get(roomMap.get(eachRoomIndex)).getTarget());
        } else {
          roomTarget = null;
        }
        eachRoomInfoMap.put("RoomTarget", roomTarget);

        if (worldMap.get(roomMap.get(eachRoomIndex)).hasPlayer()) {
          roomPlayers = worldMap.get(roomMap.get(eachRoomIndex)).getPlayer();
        } else {
          roomPlayers = null;
        }
        eachRoomInfoMap.put("RoomPlayers", roomPlayers);

        roomInfoMap.put(eachRoomIndex, eachRoomInfoMap);
      }
    }
    //System.out.println(roomInfoMap);
    return roomInfoMap;
  }

  /**
   * Helper method moveTargetCharacter helps in moving target character
   * around the world in specified order of rooms after every turn.
   */
  private void moveTargetCharacter() {
    //Target character has player rank 0
    int location = gameCharacterMap.get(characterMap.get(0)).getLocation();
    //Ofsetting no of rooms
    int count = this.noOfRooms - 1;
    if (location >= count) {
      //Target character has player rank 0
      //Reset Target's location to the start that is in room 0
      gameCharacterMap.get(characterMap.get(0)).updateLocation(0);
      worldMap.get(roomMap.get(location)).removeTarget();
      worldMap.get(roomMap.get(0)).addTarget(gameCharacterMap.get(characterMap.get(0)).getName());
    } else {
      worldMap.get(roomMap.get(location)).removeTarget();
      location += 1;
      //Target character has player rank 0
      gameCharacterMap.get(characterMap.get(0)).updateLocation(location);
      worldMap.get(roomMap.get(location)).addTarget(characterMap.get(0));
    }
    
    //Target character has player rank 0
    //String output = characterMap.get(0) + " has moved to the " + worldMap.get(roomMap
    //    .get(gameCharacterMap.get(characterMap.get(0)).getLocation())).getRoomName() + "\n";
    //return output;
  }

  @Override
  public void pickWeapon(String weaponName, PlayerInterface player) {
    
    if (weaponName == null || player == null
        || "".equals(weaponName)) {
      throw new IllegalArgumentException("input cannot be null");
    }
    
    boolean weaponExists = false;
    for (String eachWeaponame : weaponsMap.keySet()) {
      if (eachWeaponame.equals(weaponName)) {
        weaponExists = true;
        break;
      }
    }
    
    if (weaponExists == false) {
      throw new IllegalArgumentException("Weapon does not exist\n");
    }
    
    if (weaponsMap.get(weaponName).getWeaponRoomName() 
        == worldMap.get(roomMap.get(player.getLocation())).getRoomName()) {
      String output = "";
      List<String> theWeaponList = player.getWeaponList();
      int size;
      if (theWeaponList == null) {
        size = 0;
      } else {
        size = theWeaponList.size();
      }
      if (size == 3) {
        output = getTurnOwner().getName() 
            + " cannot pick " + weaponName + ". Maximum no of weapons limit reached!\n";
        throw new IllegalArgumentException(output);
      } else {
        player.addWeaponList(weaponName);
        worldMap.get(roomMap.get(player.getLocation())).removeWeapon(weaponName); 
        //Weapon can either be possessed by a player or a room, but never by both
        weaponsMap.get(weaponName).setWeaponRoomName(null);
        weaponsMap.get(weaponName).setWeaponPlayerName(player.getName());
        output = getTurnOwner().getName() 
            + " has picked " + weaponName + "\n";
        turnManager();
      }
      this.statusMessage = output;
    } else {
      throw new IllegalArgumentException("This weapon is not available\n");
    }
    
  }
  
  @Override
  public void movePlayer(String roomName, PlayerInterface player) {
    
    if (roomName == null || player == null || "".equals(roomName)) {
      throw new IllegalArgumentException("input cannot be null");
    }
    
    boolean roomExists = false;
    for (String eachRoomName : worldMap.keySet()) {
      if (eachRoomName.equals(roomName)) {
        roomExists = true;
        break;
      }
    }
    
    if (roomExists == false) {
      throw new IllegalArgumentException("Room does not exist\n");
    }
    
    String currentRoom = worldMap.get(roomMap.get(player.getLocation())).getRoomName();
    List<String> neighbourList = getNeighbour(currentRoom);
    boolean isNeighbour = false;
    for (String room : neighbourList) {
      //Verify if the input room is indeed a neighbour to current room
      if (room.equals(roomName)) {
        int previousRoomName = player.getLocation();
        //Update new location for the player
        player.updateLocation(worldMap.get(roomName).getRoomIndex());
        //Remove player from previous room
        worldMap.get(roomMap.get(previousRoomName)).removePlayer(player.getName());
        //Add player to the new room
        worldMap.get(roomName).addPlayer(player.getName());
        isNeighbour = true;
      }
    }
    if (isNeighbour == false) {
      throw new IllegalArgumentException("The player cannot move to this room.\n");
    } else {
      String output = "Player " + getTurnOwner().getName() 
          + " has moved to the " + roomName + "\n";
      turnManager();
      this.statusMessage = output;
    }
  }
  
  @Override
  public String look(PlayerInterface player) {
    
    if (player == null) {
      throw new IllegalArgumentException("input cannot be null");
    }
    
    String currentRoom = worldMap.get(roomMap.get(player.getLocation())).getRoomName();
    List<String> neighbours = getNeighbour(currentRoom);
    
    String output = getRoomInfo(currentRoom);
    for (int i = 0; i < neighbours.size(); i++) {
      String thisNeighbour = neighbours.get(i);
      if (!(worldMap.get(thisNeighbour).hasPet())) {
        output += "Neighbour Info: \n" + getRoomInfo(thisNeighbour);
      }
    }
    this.statusMessage = "Player " + this.turnOwner.getName() + " has looked.\n";
    turnManager();
    return output;
  }
  
  @Override
  public String getPlayerInfo(String playerName) {
    
    if (playerName == null || "".equals(playerName)) {
      throw new IllegalArgumentException("input cannot be null");
    }
    
    boolean playerExists = false;
    for (String eachPlayerName : gameCharacterMap.keySet()) {
      if (eachPlayerName.equals(playerName)) {
        playerExists = true;
        break;
      }
    }
    if (playerExists == false) {
      throw new IllegalArgumentException("Player does not exist");
    }
    
    String output1 = "Name of the player : " + playerName + "\n";
    String output2 = "Location of the player : " 
        + worldMap.get(roomMap.get(gameCharacterMap.get(playerName).getLocation())).getRoomName() 
        + "\n";
    String output3 = "";
    if (playerMap.get(playerName).hasWeapon()) {
      output3 = "Player has weapon/s : " 
        + playerMap.get(playerName).getWeaponList() + "\n";
    }
    
    return output1 + output2 + output3;
  }
  
  @Override
  public PlayerInterface getTurnOwner() {
    return turnOwner;
  }
  
  @Override
  public boolean isGameOver() {
    int targetHealth = targetMap.get(characterMap.get(0)).getHealth();
    if (this.turn >= this.noOfTurns) {
      return true;
    } else if (targetHealth <= 0) {
      this.winner = getTurnOwner();
      return true;
    } else {
      return false;
    }
  }
  
  /**
   * This helper method helps assigning the next turn to the next player in
   * the game.
   */
  private void turnManager() {
    if (isGameOver()) {
      throw new IllegalStateException("Game over!");
    }
    //Move target character after every turn
    moveTargetCharacter();
    //Move pet after every turn
    strollingPet();
    //Increment turn
    this.turn += 1;
    this.turnsLeft -= 1;
    //System.out.println(this.turn);
    int lastPlayerRank = characterMap.keySet().size() - 1;
    //Reset the round of players
    if (this.turnOwner.getRank() == lastPlayerRank) {
      //Reset turn to first player in the map after target and its pet ie 2
      this.turnOwner = playerMap.get(characterMap.get(2));
    } else {
      //Give turn to next player in the ongoing round
      int nextTurnOwner = this.turnOwner.getRank() + 1;
      this.turnOwner = playerMap.get(characterMap.get(nextTurnOwner));
    }
  }

  /**
   * Helper method strollingPet helps in moving target character's pet
   * around the world.
   */
  private void strollingPet() {
    //Pet has player rank 1
    int location = gameCharacterMap.get(characterMap.get(1)).getLocation();
    int count = this.noOfRooms - 3;
    if (location >= count) {
      //Pet has player rank 1
      //Reset Pet's location to the start that is in room 0
      gameCharacterMap.get(characterMap.get(1)).updateLocation(0);
      worldMap.get(roomMap.get(location)).removePet();
      //Pet has player rank 1
      worldMap.get(roomMap.get(0)).addPet(gameCharacterMap.get(characterMap.get(1)).getName());;
    } else {
      worldMap.get(roomMap.get(location)).removePet();
      location += 2;
      //System.out.println(location);
      //Pet has player rank 1
      gameCharacterMap.get(characterMap.get(1)).updateLocation(location);
      worldMap.get(roomMap.get(location)).addPet(characterMap.get(1));
    }
    
  }

  @Override
  public void computerPlay(PlayerInterface computer) {
    
    if (computer == null) {
      throw new IllegalArgumentException("Input cannot be null");
    }
    
    if (computer.getLocation() == gameCharacterMap.get(characterMap.get(0)).getLocation() 
        && !(isVisible(computer))) {
      if (computer.getWeaponList() == null) {
        poke(computer);
      } else if (computer.getWeaponList().isEmpty()) {
        poke(computer);
      } else {
        List<String> weaponList = computer.getWeaponList();
        Map<Integer, String> computerWeapons = new HashMap<Integer, String>();
        for (String eachWeapon : weaponList) {
          int currentPower = weaponsMap.get(eachWeapon).getKillPower();
          String weaponName = weaponsMap.get(eachWeapon).getWeaponName();
          computerWeapons.put(currentPower, weaponName);
        }
        int maxPower = Collections.max(computerWeapons.keySet());
        String weaponName = computerWeapons.get(maxPower);
        killAttempt(weaponName, computer);
      }
    } else {
      //Random r = new Random();
      int nextAction = randomObject.randomGenerator() + 1;
      
      switch (nextAction) {
        case 1:
          //move
          List<String> neighbourList = getNeighbour(worldMap
              .get(roomMap.get(computer.getLocation())).getRoomName());
          int roomInt;
          if (this.isMockedNum <= 0) {
            Random randomNeighbour = new Random();
            roomInt = randomNeighbour.nextInt(neighbourList.size());
          } else {
            //predicatable outcome
            roomInt = 0;
          }
          String nextRoom = neighbourList.get(roomInt);
          movePlayer(nextRoom, computer);
          break;
          
        case 2:
          //pick
          List<String> theWeaponList = computer.getWeaponList();
          int size;
          if (theWeaponList == null) {
            size = 0;
          } else {
            size = theWeaponList.size();
          }
          if (worldMap.get(roomMap.get(computer.getLocation())).hasWeapon() 
              && size < 3) {
            List<String> weaponList = worldMap.get(roomMap
                .get(computer.getLocation())).getWeapon();
            int weaponInt;
            if (this.isMockedNum <= 0) {
              Random randomWeapon = new Random();
              weaponInt = randomWeapon.nextInt(weaponList.size());
            } else {
              //predicatable outcome
              weaponInt = 0;
            }
            String weaponName = weaponList.get(weaponInt);
            pickWeapon(weaponName, computer);
          } else {
            look(computer);
            this.statusMessage = "Computer has looked.\n";
          }
          break;
          
        case 3:
          //look
          look(computer);
          this.statusMessage = "Computer has looked.\n";
          break;
          
        case 4:
          //move pet
          String roomName = null;
          int petLocation = gameCharacterMap.get(characterMap.get(1)).getLocation();
          Set<Integer> newSet = new HashSet<Integer>();
          newSet.addAll(roomMap.keySet());
          newSet.remove(petLocation);
          int one;
          if (this.isMockedNum <= 0) {
            Random randRoomIndex = new Random();
            one = randRoomIndex.nextInt(newSet.size());
          } else {
            //predicatable outcome
            one = 0;
          }
          int i = 0;
          for (Integer two : newSet) {
            if (i == one) {
              roomName = roomMap.get(two);
            }
            i++;
          }
          movePetCharacter(roomName, computer);
          this.statusMessage = "Computer has moved the pet.\n";
          break;
          
        default:
          look(computer);
          this.statusMessage = "Computer has looked.\n";
      }
    }
    
  }

  @Override
  public void movePetCharacter(String roomName, PlayerInterface player) {
    
    if (roomName == null || player == null || "".equals(roomName)) {
      throw new IllegalArgumentException("input cannot be null");
    }
    
    boolean roomExists = false;
    for (String eachRoomName : worldMap.keySet()) {
      if (eachRoomName.equals(roomName)) {
        roomExists = true;
        break;
      }
    }
    if (roomExists == false) {
      throw new IllegalArgumentException("Room does not exist\n");
    }
    
    //Pet has player rank 1
    worldMap.get(roomMap.get(gameCharacterMap
        .get(characterMap.get(1)).getLocation())).removePet();
    gameCharacterMap.get(characterMap.get(1)).updateLocation(
        worldMap.get(roomName).getRoomIndex());
    //Pet has player rank 1
    worldMap.get(roomName).addPet(characterMap.get(1));
    StringBuilder output = new StringBuilder();
    output.append("Player ").append(getTurnOwner().getName())
      .append(" has moved ").append(characterMap.get(1))
      .append(" to the ").append(roomName).append("\n");
    turnManager();
    this.statusMessage = output.toString();
  }
  

  @Override
  public void poke(PlayerInterface player) {
    
    if (player == null) {
      throw new IllegalArgumentException("Input cannot be null");
    }
    StringBuilder output = new StringBuilder();
    
    if (player.getLocation() == gameCharacterMap.get(characterMap.get(0)).getLocation()) {
      
      if (isVisible(player)) {
        output.append("Alas! Player ").append(player.getName())
          .append("'s damage attempt at ").append(gameCharacterMap
              .get(characterMap.get(0)).getName())
          .append("'s life was unsuccessful!\n");
      } else {
        int reducedHeath = targetMap
            .get(characterMap.get(0)).getHealth() - 1;
        targetMap.get(characterMap.get(0)).setHealth(reducedHeath);
        
        output.append("Player ").append(player.getName())
          .append(" has successfully attempted damage on ")
          .append(gameCharacterMap.get(characterMap.get(0)).getName()).append("'s life!\n");
      }
      
      this.statusMessage = output.toString();
      turnManager();
    } else {
      throw new IllegalArgumentException("Target is not in your room, please re-enter\n");
    }
    
  }
  

  @Override
  public void killAttempt(String weaponName, PlayerInterface player) {
    
    if (weaponName == null || player == null || "".equals(weaponName)) {
      throw new IllegalArgumentException("Input cannot be null");
    }
    StringBuilder output = new StringBuilder();
    
    if (player.getWeaponList() == null || player.getWeaponList().isEmpty()) {
      throw new IllegalArgumentException("player has no weapons");
    }
    
    boolean weaponExists = false;
    for (String eachWeaponame : player.getWeaponList()) {
      if (eachWeaponame.equals(weaponName)) {
        weaponExists = true;
        break;
      }
    }
    
    if (weaponExists == false) {
      throw new IllegalArgumentException("Weapon does not exist\n");
    }
    
    if (player.getLocation() == gameCharacterMap.get(characterMap.get(0)).getLocation()) {
      
      if (isVisible(player)) {
        output.append("Alas! Player ").append(player.getName())
        .append("'s damage attempt at ").append(gameCharacterMap
            .get(characterMap.get(0)).getName())
        .append("'s life was unsuccessful!\n");
      } else {
        int killPower = weaponsMap.get(weaponName).getKillPower();
        int reducedHeath = targetMap
            .get(characterMap.get(0)).getHealth() - killPower;
        targetMap.get(characterMap.get(0)).setHealth(reducedHeath);
        
        player.removeWeaponList(weaponName);
        weaponsMap.remove(weaponName);
        
        output.append("Player ").append(player.getName())
        .append(" has successfully attempted damage on ")
        .append(gameCharacterMap.get(characterMap.get(0)).getName()).append("'s life!\n");
      }
      
      this.statusMessage = output.toString();
      turnManager();
    } else {
      throw new IllegalArgumentException("Target is not in your room, please re-enter\n");
    }
    
  }

  /**
   * This method verifies if the player input can indeed be seen by another players or not.
   *
   * @param player represents player in the game
   * @return true if the player can be seen by other players
   */
  private boolean isVisible(GameCharacter player) {
    boolean output = false;
    
    List<String> currentRoomPlayers = worldMap.get(roomMap.get(player.getLocation())).getPlayer();
    if (!(currentRoomPlayers == null)) {
      for (String eachPlayer : currentRoomPlayers) {
        if (eachPlayer != player.getName()) {
          output = true;
        }
      }
    }
    
    List<String> neighbourRooms = getNeighbour(roomMap.get(player.getLocation()));
    for (String eachNeighbour : neighbourRooms) {
      List<String> neighbourRoomPlayers = worldMap.get(eachNeighbour).getPlayer();
      if (!(neighbourRoomPlayers == null)) {
        for (String eachPlayer : neighbourRoomPlayers) {
          if (eachPlayer != player.getName()) {
            output = true;
          }
        }
      }
    }
    
    //Pet makes the room invisible
    if (gameCharacterMap.get(characterMap.get(1)).getLocation() == player.getLocation()) {
      output = false;
    }
    return output;
  }
  

  @Override
  public PlayerInterface getWinner() {
    return this.winner;
  }
  

  @Override
  public String getRoomInfoByPlayer(PlayerInterface player) {
    if (player == null) {
      throw new IllegalArgumentException("player cannot be null");
    }
    String output = getRoomInfo(roomMap.get(player.getLocation()));
    return output;
  }

  @Override
  public String getStatusMessage() {
    return new String(this.statusMessage);
  }
  
  @Override
  public int getTurnCount() {
    int count = this.turnsLeft;
    return count;
  }
  
  @Override
  public int getTargetHealth() {
    int health = targetMap.get(characterMap.get(0)).getHealth();
    return health;
  }
  
  @Override
  public String getTargetStatusMessage() {
    StringBuilder message = new StringBuilder();
    message.append(characterMap.get(0)).append(" has entered in the ")
      .append(roomMap.get(targetMap.get(characterMap.get(0)).getLocation()));
    return message.toString();
  }

  @Override
  public String getTurnOwnerName() {
    if (getTurnOwner() == null) {
      return "No one";
    } else {
      return getTurnOwner().getName();
    }
  }

  @Override
  public List<Integer> getWorldDimensions() {
    List<Integer> dimensionList = new ArrayList<Integer>();
    dimensionList.add(this.dimensionX);
    dimensionList.add(this.dimensionY);
    dimensionList.add(this.noOfRooms);
    return dimensionList;
  }

  @Override
  public String getTurnOwnerRoomName() {
    int currentLoc = getTurnOwner().getLocation();
    String currentRoomName = roomMap.get(currentLoc);
    return currentRoomName;
  }

  @Override
  public List<String> getListOfPlayerWeapons(String playerName) {
    List<String> listOfPlayerWeapons = new ArrayList<>();
    listOfPlayerWeapons = playerMap.get(playerName).getWeaponList();
    return listOfPlayerWeapons;
  }

  @Override
  public List<String> getListOfRoomWeapons(String spaceName) {
    List<String> listOfRoomWeapons = new ArrayList<>();
    listOfRoomWeapons = worldMap.get(spaceName).getWeapon();
    return listOfRoomWeapons;
  }

  @Override
  public List<String> getListOfAllRooms() {
    List<String> listOfRooms = new ArrayList<>();
    for (int i = 0; i < roomMap.size(); i++) {
      listOfRooms.add(roomMap.get(i));
    }
    return listOfRooms;
  }
  
}