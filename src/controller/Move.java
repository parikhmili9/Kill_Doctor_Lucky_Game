package controller;

import worldmodel.model.WorldInterface;

/**
 * This class implements command of moving a game character within the World.
 *
 * @author shreyas.terdalkar
 * @param roomName represents name of the room
 * @param out represents output of the console
 */
public class Move implements CommandInterface {

  private String roomName;
  
  /**
   * This method constructs the command of moving player it its neighbour.
   *
   * @param roomName represents room name
   */
  public Move(String roomName) {
    
    if (roomName == null) {
      throw new IllegalArgumentException("Input and Output cannot be null");
    }
    
    this.roomName = roomName;
  }
  
  @Override
  public void commandOperation(WorldInterface model) {
    
    if (model == null) {
      throw new IllegalArgumentException("model cannot be null");
    }
    model.movePlayer(this.roomName, model.getTurnOwner());
  }

}