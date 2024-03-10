package controller;

import worldmodel.model.WorldInterface;

/**
 * This class implements the command of adding new player to the game.
 *
 * @author shreyas.terdalkar
 *
 */
public class AddGamePlayer implements CommandInterface {
  
  private String playerName;
  private String playerRoomName;
  private boolean isHuman;
  
  /**
   * This method constructs the add command by initializing
   * the player name and its starting location.
   *
   * @param playerName represents name of the player
   * @param playerRoomName represents name of the starting location room
   * @param isHuman true if the player is human player, otherwise false
   */
  public AddGamePlayer(String playerName, String playerRoomName, boolean isHuman) {
    
    if (playerName == null || playerRoomName == null) {
      throw new IllegalArgumentException("Input and Output cannot be null");
    }
    
    this.playerName = playerName;
    this.playerRoomName = playerRoomName;
    this.isHuman = isHuman;
  }

  @Override
  public void commandOperation(WorldInterface model) {
    
    if (model == null) {
      throw new IllegalArgumentException("model cannot be null");
    }
    model.addPlayer(playerName, playerRoomName, isHuman);
  }
}
