package controller;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import view.WorldViewInterface;
import worldmodel.character.GameCharacter;
import worldmodel.model.WorldInterface;
import worldmodel.model.WorldModel;

/**
 * WorldController controls actual implementation of this game by controlling user input and output
 * and making use of different fuctionalities of the model and the view to play the game.
 *
 * @author shreyas.terdalkar
 *
 */
public class WorldController implements ControllerInterface {

  private Map<String, String> configuration;
  private WorldViewInterface view;
  private WorldInterface model;
  private CommandInterface command;
  
  /**
   * This method constructs the controller by giving it a view and
   * the configuration for the current world specifications.
   *
   * @param configuration represents configuration required to initialize the model
   * @param view represents the view of the game
   */
  public WorldController(Map<String, String> configuration, WorldViewInterface view) {
    if (configuration == null || configuration.isEmpty() || view == null) {
      throw new IllegalArgumentException("input cannot be null");
    }
    this.configuration = configuration;
    this.view = view;
    this.command = null;
    this.model = null;
  }

  @Override
  public void gamePlay() {
    view.startView();
    view.setListener(this);
  }

  @Override
  public void setModel(String filePath) {
    try {
      if (filePath == null) {
        this.model = new WorldModel(this.configuration);
      } else {
        Map<String, String> newWorldconfiguration = new HashMap<String, String>();
        newWorldconfiguration.put("filePath", filePath);
        newWorldconfiguration.put("noOfTurnsString", configuration.get("noOfTurnsString"));
        newWorldconfiguration.put("isMocked", configuration.get("isMocked"));
        this.model = new WorldModel(newWorldconfiguration);
      }
      view.startGame();
    } catch (IllegalArgumentException iae) {
      view.showErrorPopup(iae.getMessage());
    }
  }

  @Override
  public void setModelM(WorldInterface model) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.model = model;
    view.startGame();
  }

  @Override
  public void quitGame() {
    System.exit(0);
  }

  @Override
  public String getStatus() {
    return new String(model.getStatusMessage());
  }

  @Override
  public int getConTurnCount() {
    int count = model.getTurnCount();
    return count;
  }
  
  @Override
  public String getConTurnOwnerName() {
    return new String(model.getTurnOwnerName());
  }
  
  @Override
  public int getConTargetHealth() {
    int health = model.getTargetHealth();
    return health;
  }

  @Override
  public String getConTargetStatus() {
    return new String(model.getTargetStatusMessage());
  }

  @Override
  public void setCommand(CommandInterface command) {
    //command can be null here hence no validation required
    this.command = command;
    if (this.command != null) {
      try {
        this.command.commandOperation(model);
      } catch (IllegalArgumentException iae) {
        view.showErrorPopup(iae.getMessage());
      } catch (IllegalStateException ise) {
        if (model.isGameOver()) {
          if (model.getWinner() == null) {
            view.gameEnd("Game over! Maximum no of turns reached\n");
          } else {
            view.gameEnd("Game over! " + model.getTurnOwner().getName() 
                + " has won the game!\n");
          }
        }
      }
      this.command = null;
      view.refresh();
      try {
        if (this.getTurnOwner().isComputer()) {
          model.computerPlay(model.getTurnOwner());
          view.refresh();
        }
      } catch (NullPointerException npe) {
        view.showErrorPopup(npe.getMessage());
      }

    }
  }

  @Override
  public BufferedImage getConWorldImage() {
    return model.getWorldGraphicImage();
  }

  @Override
  public List<String> getListOfConPlayerWeapons(String playerName) {
    return new ArrayList<String>(model.getListOfPlayerWeapons(playerName));
  }

  @Override
  public List<String> getListOfRoomWeapons(String spaceName) {
    return new ArrayList<String>(model.getListOfRoomWeapons(spaceName));
  }

  @Override
  public String getTurnOwnerRoomName() {
    return new String(model.getTurnOwnerRoomName());
  }

  @Override
  public List<String> getListOfAllRooms() {
    return new ArrayList<String>(model.getListOfAllRooms());
  }
  
  @Override
  public Map<Integer, Map<String, List<String>>> getConRoomDetails() {
    return new HashMap<Integer, Map<String, List<String>>>(model.getRoomDetails());
  }
  
  @Override
  public List<Integer> getConWorldDimensions() {
    return new ArrayList<Integer>(model.getWorldDimensions());
  }

  @Override
  public String getPlayerInfo(String eachPlayer) {
    return new String(model.getPlayerInfo(eachPlayer));
  }

  @Override
  public GameCharacter getTurnOwner() {
    return model.getTurnOwner();
  }
  
}
