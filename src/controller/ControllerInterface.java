package controller;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;
import worldmodel.character.GameCharacter;
import worldmodel.model.WorldInterface;

/**
 * ControllerInterface defines implementation of the controller of this game.
 * 
 */
public interface ControllerInterface {

  /**
   * This method is responsible for start and implementation
   * of the game play.
   */
  public void gamePlay();

  /**
   * This method sets a model for the game when user selects
   * which world to play with.
   *
   * @param filePath the string containing filepath of the world
   */
  public void setModel(String filePath);

  /**
   * This method is used for controller testing.
   *
   * @param model the mock model
   */
  public void setModelM(WorldInterface model);
  
  /**
   * This method ends the game entirely.
   */
  public void quitGame();
  
  /**
   * This method is used to get status information of the game
   * during various points in the game play.
   *
   * @return the string containing status information
   */
  public String getStatus();
  
  /**
   * This method is used to get no of turns left in the game.
   *
   * @return no of turns left
   */
  public int getConTurnCount();
  
  /**
   * This method is used to name of the turn owner.
   *
   * @return name of the turn owner
   */
  public String getConTurnOwnerName();
  
  /**
   * This method is used to get target's current health.
   *
   * @return the target's health
   */
  public int getConTargetHealth();
  
  /**
   * This method is used to get target's location information.
   *
   * @return string containing target's location in formation
   */
  public String getConTargetStatus();

  /**
   * This method is used to execute command in the game.
   *
   * @param command the command to be executed
   */
  public void setCommand(CommandInterface command);

  /**
   * This method is used to get graphical representation of the world.
   *
   * @return the image containing graphical representation of the game
   */
  public BufferedImage getConWorldImage();
  
  /**
   * This method is used to get room details of the world used to 
   * show players in the game graphically.
   *
   * @return Map of room indices and respective room information
   */
  public Map<Integer, Map<String, List<String>>> getConRoomDetails();
  
  /**
   * This method is used to get the overall dimentions of the world.
   *
   * @return list of world dimensions
   */
  public List<Integer> getConWorldDimensions();

  /**
   * This method is used to get player information
   * for the specified player.
   *
   * @param eachPlayer the player name
   * @return string containing the palyer information
   */
  public String getPlayerInfo(String eachPlayer);

  /**
   * This method is used to get list of weapons for specified player.
   *
   * @param playerName name of the player
   * @return list of player weapons
   */
  public List<String> getListOfConPlayerWeapons(String playerName);

  /**
   * This method is used to get list of weapons for specified room.
   *
   * @param spaceName name of the room
   * @return list of weapons in the room
   */
  public List<String> getListOfRoomWeapons(String spaceName);

  /**
   * This method is used to get turn owner's room name.
   *
   * @return string containing name of the room
   */
  public String getTurnOwnerRoomName();

  /**
   * This method is used to get list of all rooms.
   *
   * @return list of all rooms
   */
  public List<String> getListOfAllRooms();

  /**
   * This method is used to get turn owner.
   *
   * @return the player with current turn
   */
  public GameCharacter getTurnOwner();
}
