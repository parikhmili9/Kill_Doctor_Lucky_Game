package controller;

import worldmodel.model.WorldInterface;

/**
 * This class implements command of attempting to kill target character.
 *
 * @author shreyas.terdalkar
 * @param weaponName represents name of the weapon
 *
 */
public class Kill implements CommandInterface {
  
  private String weaponName;
  
  /**
   * This method constructs the command of attempting to kill target character.
   *
   * @param weaponName represents name of the weapon to be used
   */
  public Kill(String weaponName) {
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
    model.killAttempt(this.weaponName, model.getTurnOwner());
  }

}
