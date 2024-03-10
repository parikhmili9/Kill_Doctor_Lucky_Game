package controller;

import worldmodel.model.WorldInterface;

/**
 * This class implements command of moving the target's pet within the World.
 *
 * @author shreyas.terdalkar
 */
public class MovePet implements CommandInterface {

  private String roomName;
  
  /**
   * This method constructs the command of moving the target's pet to the specified room.
   *
   * @param roomName represents room name
   */
  public MovePet(String roomName) {
    if (roomName == null) {
      throw new IllegalArgumentException("Input cannot be null");
    }
    this.roomName = roomName;
  }
  
  @Override
  public void commandOperation(WorldInterface model) {
    if (model == null) {
      throw new IllegalArgumentException("model cannot be null");
    }
    model.movePetCharacter(this.roomName, model.getTurnOwner());
  }

}
