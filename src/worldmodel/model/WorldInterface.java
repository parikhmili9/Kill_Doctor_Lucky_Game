package worldmodel.model;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;
import worldmodel.character.PlayerInterface;

/**
 * WorldInterface creates the World for Doctor Lucky's Game. Its class implements the creation of
 * this world by accepting the World Specification text file and creating objects of its components.
 *
 * @author shreyas.terdalkar
 */
public interface WorldInterface {

  /**
   * This method adds new player to the game.
   *
   * @param playerName represents name of the player
   * @param playerRoomName represents name of the room where the player starts the game
   * @param isHuman represents flag that determines if the player is human or computer
   */
  public void addPlayer(String playerName, String playerRoomName, boolean isHuman);
  
  /**
   * Returns Room Details such as its name , its neighbour rooms,
   * weapons, players and target character in it if present.
   *
   * @param roomName represents name of the room
   * @return string containing name of the room, its neighbour rooms,
   *     weapons, players and target character in it if present 
   */
  public String getRoomInfo(String roomName);
  
  /**
   * Returns player info such as its name, its weapons and its current location.
   *
   * @param playerName name of the player
   * @return String containing all player info
   */
  public String getPlayerInfo(String playerName);
  
  /**
   * This method is used to move player from its current room to one of its neighbours.
   *
   * @param roomName represents name of the room that player is trying to move to
   * @param player represents the player having current turn in the game
   */
  public void movePlayer(String roomName, PlayerInterface player);
  
  /**
   * This method allows the player to pick weapon from its current room.
   *
   * @param weaponName represents name of the room the player is trying to pick
   * @param player represents the player having current turn in the game
   */
  public void pickWeapon(String weaponName, PlayerInterface player);
  
  /**
   * This method allows the player to look around from its current room.
   *
   * @param player represents the player having current turn in the game
   */
  public String look(PlayerInterface player);
  
  /**
   * This method creates graphical representation of Doctor Lucky's Mansion in the form of 2D Grid.
   *
   * @throws IllegalArgumentException if file not found/ incorrect format
   */
  public BufferedImage getWorldGraphicImage();
  
  /**
   * Returns the player having current turn in the game.
   *
   * @return player object having current turn
   */
  public PlayerInterface getTurnOwner();

  /**
   * This method implements the game play for computer player.
   *
   * @param computer player having current turn
   */
  public void computerPlay(PlayerInterface computer);

  /**
   * This method is used to move player from its current room to one of its neighbours.
   *
   * @param roomName represents name of the room that player is trying to move pet into
   * @param player represents the player having current turn in the game
   */
  public void movePetCharacter(String roomName, PlayerInterface player);

  /**
   * This method is used to attempt murder of the target character by poking it
   * in the eye that is without any weapon.
   *
   * @param player represents the player having current turn in the game
   */
  public void poke(PlayerInterface player);

  /**
   * This method is used to attempt murder of the target character by with a weapon.
   *
   * @param weaponName represents name of the weapon to be used
   * @param player represents the player having current turn in the game
   */
  public void killAttempt(String weaponName, PlayerInterface player);

  /**
   * This method verifies if the game is over ie if no of turns
   * have reached its limit after executing its current turn.
   *
   * @return true if game is over, otherwise returns false
   */
  public boolean isGameOver();
  
  /**
   * Returns the winner player of the game.
   *
   * @return null until there is a winner, and winner when game ends
   */
  public PlayerInterface getWinner();

  /**
   * Returns Room Information using player details.
   *
   * @param turnOwner represents player with the turn
   * @return String containing player's room information
   */
  public String getRoomInfoByPlayer(PlayerInterface turnOwner);
  
  /**
   * This method is used to get status message of the game.
   *
   * @return string containing the status message
   */
  public String getStatusMessage();
  
  /**
   * This method is used to get no of turns left before the game ends.
   *
   * @return the no of turns left
   */
  public int getTurnCount();
  
  /**
   * This method is used to get target's health.
   *
   * @return health of the target
   */
  public int getTargetHealth();
  
  /**
   * This method is used to target's location information.
   *
   * @return location of the target
   */
  public String getTargetStatusMessage();

  /**
   * This method is used to get current turn owner's name.
   *
   * @return name of the turn owner
   */
  public String getTurnOwnerName();

  /**
   * This method is used to get the world dimensions.
   *
   * @return list of world dimensions
   */
  public List<Integer> getWorldDimensions();

  /**
   * This method is used to get the list of current player's weapons.
   *
   * @param playerName name of the player
   * @return list of player's weapons
   */
  public List<String> getListOfPlayerWeapons(String playerName);

  /**
   * This method is used to list of weapons in the room available.
   *
   * @param spaceName name of the room
   * @returnlist of the weapons in the room
   */
  public List<String> getListOfRoomWeapons(String spaceName);

  /**
   * This method is used get player's name whose turn it is.
   *
   * @return name of the player with current turn
   */
  public String getTurnOwnerRoomName();

  /**
   * This method is used to get list of all the rooms in the world.
   *
   * @return list of rooms in the world
   */
  public List<String> getListOfAllRooms();

  /**
   * This method is used to get information regarding the entire game world in order
   * to create its graphical representation.
   *
   * @return map of room indices and the information of respective rooms
   */
  public Map<Integer, Map<String, List<String>>> getRoomDetails();
}
