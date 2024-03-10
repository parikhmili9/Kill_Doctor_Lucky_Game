package controller;

import worldmodel.model.WorldInterface;

/**
 * CommandInterface unifies all command operations under common definition.
 *
 * @author shreyas.terdalkar
 *
 */
public interface CommandInterface {
  
  /**
   * Starting point of the controller.
   *
   * @param model represents the World Model
   * @throws IllegalArgumentException if model is null
   */
  public void commandOperation(WorldInterface model);
}
