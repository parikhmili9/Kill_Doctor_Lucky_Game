package controller;

import worldmodel.model.WorldInterface;

/**
 * This class implements command of creating graphical representation of the World.
 *
 * @author shreyas.terdalkar
 *
 */
public class Image implements CommandInterface {
  
  @Override
  public void commandOperation(WorldInterface model) {
    
    if (model == null) {
      throw new IllegalArgumentException("model cannot be null");
    }
    model.getWorldGraphicImage();
  }
}
