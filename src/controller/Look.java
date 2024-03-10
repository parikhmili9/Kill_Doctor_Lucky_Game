package controller;

import worldmodel.model.WorldInterface;

/**
 * This class implements command of displaying neighbour information.
 *
 * @author shreyas.terdalkar
 *
 */
public class Look implements CommandInterface {
  
  private String lookInfo;
  
  /**
   * Constructor.
   */
  public Look() {
    this.lookInfo = "";
  }
  
  @Override
  public void commandOperation(WorldInterface model) {
    //get Neighbour of any room
    this.lookInfo = model.look(model.getTurnOwner());
  }

  /**
   * This method is used to get looking around details from the model.
   *
   * @return string containing looking around information
   */
  public String getLookInfo() {
    return this.lookInfo;
  }
}
