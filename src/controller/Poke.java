package controller;

import worldmodel.model.WorldInterface;

/**
 * This class implements command of attempting to kill target character
 * without any weapon.
 *
 * @author shreyas.terdalkar
 *
 */
public class Poke implements CommandInterface {
  
  /**
   * This method constructs the command of attempting to kill target character
   * without any weapon.
   */
  @Override
  public void commandOperation(WorldInterface model) {
    if (model == null) {
      throw new IllegalArgumentException("model cannot be null");
    }
    model.poke(model.getTurnOwner());
  }

}
