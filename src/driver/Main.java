package driver;

import controller.ControllerInterface;
import controller.WorldController;
import java.util.HashMap;
import java.util.Map;
import view.WorldView;
import view.WorldViewInterface;

/**
 * Main Driver Class drives, controls and implements the Game by creating a view
 * and a controller for the game.
 *
 * @author shreyas.terdalkar
 *
 */
public class Main {

  /**
   * Driver main method acts as origin of the game by creating a view and
   * a controller in the game.
   *
   * @param args file path, noOfTurns represents path of specification file and no of turns allowed.
   * @throws IllegalArgumentException if command line arguments are null or incorrect in count.
   */
  public static void main(String[] args) {
    if (args == null || args.length != 3) {
      throw new IllegalArgumentException("Arguments cannot be null");
    }
    Map<String, String> configuration = new HashMap<String, String>();
    configuration.put("filePath", args[0]);
    configuration.put("noOfTurnsString", args[1]);
    configuration.put("isMocked", args[2]);
    
    WorldViewInterface view = new WorldView();
    
    ControllerInterface controller = new WorldController(configuration, view);
    
    controller.gamePlay();
  }

}
