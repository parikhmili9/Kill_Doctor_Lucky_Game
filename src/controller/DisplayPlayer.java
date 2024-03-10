package controller;

import worldmodel.model.WorldInterface;

/**
 * This class implements command of displaying player information.
 *
 * @author shreyas.terdalkar
 *
 */
public class DisplayPlayer implements CommandInterface {
  
  private String playerName;
  
  /**
   * This constructor initializes the DisplayPlayer Command by taking input from the user
   * as specified room name and assigning it to the command.
   *
   * @param playerName represents name of the player
   */
  public DisplayPlayer(String playerName) {
    
    if (playerName == null) {
      throw new IllegalArgumentException("Input and Output cannot be null");
    }
    
    this.playerName = playerName;
  }

  @Override
  public void commandOperation(WorldInterface model) {
    
    if (model == null) {
      throw new IllegalArgumentException("model cannot be null");
    }
    model.getPlayerInfo(this.playerName);
  }

}
