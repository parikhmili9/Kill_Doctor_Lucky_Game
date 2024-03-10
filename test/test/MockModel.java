package test;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;
import worldmodel.character.PlayerInterface;
import worldmodel.model.WorldInterface;

/**
 * MockModel class assists in mocking of the world model in order to test
 * the world controller in isolation.
 *
 * @author shreyas.terdalkar
 */
public class MockModel implements WorldInterface {

  private StringBuilder log;
  private String uniqueCode;
  
  /**
   * This method constructs the MockModel by initializing the logs and unique codes
   * for the mock model.
   *
   * @param log represents input logs of the mock model
   * @param uniqueCode represents unique code used for identifying correct method
   */
  public MockModel(StringBuilder log, String uniqueCode) {
    this.log = log;
    this.uniqueCode = uniqueCode;
  }
  
  @Override
  public void addPlayer(String playerName, String playerRoomName, boolean isHuman) {
    log.append("Player has been added.").append("\n");
    log.append("Player: " + playerName + " Room: " + playerRoomName + "\n");
  }

  @Override
  public String getRoomInfo(String roomName) {
    log.append("Room information is returned.").append("\n");
    log.append("Room: " + roomName + "\n");
    return uniqueCode + "\n";
  }

  @Override
  public String getPlayerInfo(String playerName) {
    log.append("Player information is returned.").append("\n");
    log.append("Player: " + playerName + "\n");
    return uniqueCode + "\n";
  }

  @Override
  public void movePlayer(String roomName, PlayerInterface player) {
    log.append("Player has been moved.").append("\n");
    log.append("Room: " + roomName + " Player is null\n");
  }

  @Override
  public void pickWeapon(String weaponName, PlayerInterface player) {
    log.append("Player has picked up a weapon.").append("\n");
    log.append("Weapon: " + weaponName + " Player is null\n");
  }

  @Override
  public String look(PlayerInterface player) {
    log.append("Player looked around the current room.").append("\n");
    log.append("Player is null\n");
    return uniqueCode + "\n";
  }

  @Override
  public BufferedImage getWorldGraphicImage() {
    log.append("Graphic image of the world is returned.").append("\n");
    return null;
  }

  //@Override
  //public String getWorldGraphicImage() {
  //  return uniqueCode + "\n";
  //}

  @Override
  public PlayerInterface getTurnOwner() {
    log.append("Current turn's player object is returned.").append("\n");
    log.append(uniqueCode + "\n");
    return null;
  }

  @Override
  public void computerPlay(PlayerInterface computer) {
    log.append("Computer player's play method is called.").append("\n");
  }

  @Override
  public void movePetCharacter(String roomName, PlayerInterface player) {
    log.append("Pet is moved.").append("\n");
    log.append("Room Name: " + roomName + " Player is null\n");
  }

  @Override
  public void poke(PlayerInterface player) {
    log.append("Target's eye has been poked.").append("\n");
  }

  @Override
  public void killAttempt(String weaponName, PlayerInterface player) {
    log.append("Player has attacked the target with a weapon.").append("\n");
    log.append("weapon Name: " + weaponName + " Player is null\n");
  }

  @Override
  public boolean isGameOver() {
    log.append("Checked if game is over.").append("\n");
    return false;
  }

  @Override
  public PlayerInterface getWinner() {
    log.append("Winner of the game is returned.").append("\n");
    log.append(uniqueCode + "\n");
    return null;
  }

  @Override
  public String getRoomInfoByPlayer(PlayerInterface turnOwner) {
    log.append("Player's current room information is returned.").append("\n");
    return uniqueCode + "\n";
  }

  @Override
  public String getStatusMessage() {
    log.append("Game's status message is returned.").append("\n");
    return uniqueCode + "\n";
  }

  @Override
  public int getTurnCount() {
    log.append("Current turn count is returned.").append("\n");
    return 0;
  }

  @Override
  public int getTargetHealth() {
    log.append("Target's health is returned.").append("\n");
    return 0;
  }

  @Override
  public String getTargetStatusMessage() {
    log.append("Target's status message is returned.").append("\n");
    return uniqueCode + "\n";
  }

  @Override
  public String getTurnOwnerName() {
    log.append("Current turn's player's name is returned ").append("\n");
    return uniqueCode + "\n";
  }

  @Override
  public List<Integer> getWorldDimensions() {
    log.append("World's dimensions are returned.").append("\n");
    return null;
  }

  @Override
  public List<String> getListOfPlayerWeapons(String playerName) {
    log.append("Player's weapons list is returned.").append("\n");
    return null;
  }

  @Override
  public List<String> getListOfRoomWeapons(String spaceName) {
    log.append("Room's weapons list is returned").append("\n");
    return null;
  }

  @Override
  public String getTurnOwnerRoomName() {
    log.append("Current turn's player's room name is returned.").append("\n");
    return uniqueCode + "\n";
  }

  @Override
  public List<String> getListOfAllRooms() {
    log.append("List of all rooms returned.").append("\n");
    return null;
  }

  @Override
  public Map<Integer, Map<String, List<String>>> getRoomDetails() {
    log.append("Room details returned.");
    return null;
  }

}
