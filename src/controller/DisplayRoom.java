package controller;

import worldmodel.model.WorldInterface;

/**
 * This class implements command of displaying room information.
 *
 * @author shreyas.terdalkar
 *
 */
public class DisplayRoom implements CommandInterface {
  
  private String roomName;
  
  /**
   * This constructor initializes the DisplayRoom Command by taking specified room name
   * as input and assigning it to the command.
   *
   * @param roomName represents name of the room specified by user
   */
  public DisplayRoom(String roomName) {
    
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
    model.getRoomInfo(this.roomName);
  }

}
