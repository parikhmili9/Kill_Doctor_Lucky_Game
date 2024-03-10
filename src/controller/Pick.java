package controller;

import worldmodel.model.WorldInterface;

/**
 * This class implements command of picking weapon from the current room.
 *
 * @author shreyas.terdalkar
 *
 */
public class Pick implements CommandInterface {

  private String weaponName;
  
  /**
   * This constructor initializes the Pick command by taking weapon's name from user
   * and assigning it to the command.
   *
   * @param weaponName represents name of the weapon in specified room
   */
  public Pick(String weaponName) {
    
    if (weaponName == null) {
      throw new IllegalArgumentException("Input cannot be null");
    }
    this.weaponName = weaponName;
  }
  
  @Override
  public void commandOperation(WorldInterface model) {
    
    if (model == null) {
      throw new IllegalArgumentException("model cannot be null");
    }
    model.pickWeapon(this.weaponName, model.getTurnOwner());

  }

}
