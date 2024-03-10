package test;

import static org.junit.Assert.assertEquals;

import controller.AddGamePlayer;
import controller.CommandInterface;
import controller.ControllerInterface;
import controller.DisplayPlayer;
import controller.DisplayRoom;
import controller.Image;
import controller.Kill;
import controller.Look;
import controller.Move;
import controller.MovePet;
import controller.Pick;
import controller.Poke;
import controller.WorldController;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

/**
 * This class is used to test controller in isolation.
 */
public class ControllerTestUsingMockModelMockView {

  @Test
  public void testSetModel() {
    StringBuilder log = new StringBuilder();
    MockView mockView = new MockView(log, "04242022");
    Map<String, String> configuration = new HashMap<String, String>();
    configuration.put("filePath", "././res/mansion.txt");
    configuration.put("noOfTurnsString", "10");
    configuration.put("isMocked", "0");
    ControllerInterface controller = new WorldController(configuration, mockView);
    MockModel mockModel = new MockModel(log, "04242022");
    controller.setModelM(mockModel);
    controller.gamePlay();
    String expectedOutput = "Game started.\n"
        + "Start view called.\n"
        + "Listener is set.\n";
    assertEquals(expectedOutput, log.toString());
  }

  @Test
  public void testQuitGame() {
    StringBuilder log = new StringBuilder();
    MockView mockView = new MockView(log, "04242022");
    Map<String, String> configuration = new HashMap<String, String>();
    configuration.put("filePath", "././res/mansion.txt");
    configuration.put("noOfTurnsString", "10");
    configuration.put("isMocked", "0");
    ControllerInterface controller = new WorldController(configuration, mockView);
    MockModel mockModel = new MockModel(log, "04242022");
    controller.setModelM(mockModel);
    controller.gamePlay();
    //controller.quitGame();
    String expectedOutput = "Game started.\n"
        + "Start view called.\n"
        + "Listener is set.\n";
    assertEquals(expectedOutput, log.toString());
  }

  @Test
  public void testGetStatus() {
    StringBuilder log = new StringBuilder();
    MockView mockView = new MockView(log, "04242022");
    Map<String, String> configuration = new HashMap<String, String>();
    configuration.put("filePath", "././res/mansion.txt");
    configuration.put("noOfTurnsString", "10");
    configuration.put("isMocked", "0");
    ControllerInterface controller = new WorldController(configuration, mockView);
    MockModel mockModel = new MockModel(log, "04242022");
    controller.setModelM(mockModel);
    controller.getStatus();
    controller.gamePlay();
    String expectedOutput = "Game started.\n"
        + "Game's status message is returned.\n"
        + "Start view called.\n"
        + "Listener is set.\n";
    assertEquals(expectedOutput, log.toString());
  }

  @Test
  public void testGetConTurnCount() {
    StringBuilder log = new StringBuilder();
    MockView mockView = new MockView(log, "04242022");
    Map<String, String> configuration = new HashMap<String, String>();
    configuration.put("filePath", "././res/mansion.txt");
    configuration.put("noOfTurnsString", "10");
    configuration.put("isMocked", "0");
    ControllerInterface controller = new WorldController(configuration, mockView);
    MockModel mockModel = new MockModel(log, "04242022");
    controller.setModelM(mockModel);
    controller.getConTurnCount();
    controller.gamePlay();
    String expectedOutput = "Game started.\n"
        + "Current turn count is returned.\n"
        + "Start view called.\n"
        + "Listener is set.\n";
    assertEquals(expectedOutput, log.toString());
  }

  @Test
  public void testGetConTurnOwnerName() {
    StringBuilder log = new StringBuilder();
    MockView mockView = new MockView(log, "04242022");
    Map<String, String> configuration = new HashMap<String, String>();
    configuration.put("filePath", "././res/mansion.txt");
    configuration.put("noOfTurnsString", "10");
    configuration.put("isMocked", "0");
    ControllerInterface controller = new WorldController(configuration, mockView);
    MockModel mockModel = new MockModel(log, "04242022");
    controller.setModelM(mockModel);
    controller.getConTurnOwnerName();
    controller.gamePlay();
    String expectedOutput = "Game started.\n"
        + "Current turn's player's name is returned \n"
        + "Start view called.\n"
        + "Listener is set.\n";
    assertEquals(expectedOutput, log.toString());
  }

  @Test
  public void testgetConTargetHealth() {
    StringBuilder log = new StringBuilder();
    MockView mockView = new MockView(log, "04242022");
    Map<String, String> configuration = new HashMap<String, String>();
    configuration.put("filePath", "././res/mansion.txt");
    configuration.put("noOfTurnsString", "10");
    configuration.put("isMocked", "0");
    ControllerInterface controller = new WorldController(configuration, mockView);
    MockModel mockModel = new MockModel(log, "04242022");
    controller.setModelM(mockModel);
    controller.getConTurnOwnerName();
    controller.getConTargetHealth();
    controller.gamePlay();
    String expectedOutput = "Game started.\n"
        + "Current turn's player's name is returned \n"
        + "Target's health is returned.\n"
        + "Start view called.\n"
        + "Listener is set.\n";
    assertEquals(expectedOutput, log.toString());
  }

  @Test
  public void testGetConTargetStatus() {
    StringBuilder log = new StringBuilder();
    MockView mockView = new MockView(log, "04242022");
    Map<String, String> configuration = new HashMap<String, String>();
    configuration.put("filePath", "././res/mansion.txt");
    configuration.put("noOfTurnsString", "10");
    configuration.put("isMocked", "0");
    ControllerInterface controller = new WorldController(configuration, mockView);
    MockModel mockModel = new MockModel(log, "04242022");
    controller.setModelM(mockModel);
    controller.getConTurnOwnerName();
    controller.getConTargetHealth();
    controller.getConTargetStatus();
    controller.gamePlay();
    String expectedOutput = "Game started.\n"
        + "Current turn's player's name is returned \n"
        + "Target's health is returned.\n"
        + "Target's status message is returned.\n"
        + "Start view called.\n"
        + "Listener is set.\n";
    assertEquals(expectedOutput, log.toString());
  }

  @Test
  public void testSetCommandAddHumanPlayer() {
    StringBuilder log = new StringBuilder();
    MockView mockView = new MockView(log, "04242022");
    Map<String, String> configuration = new HashMap<String, String>();
    configuration.put("filePath", "././res/mansion.txt");
    configuration.put("noOfTurnsString", "10");
    configuration.put("isMocked", "0");
    ControllerInterface controller = new WorldController(configuration, mockView);
    MockModel mockModel = new MockModel(log, "04242022");
    controller.setModelM(mockModel);
    CommandInterface cmd = new AddGamePlayer("mili", "Armory", true);
    controller.setCommand(cmd);
    controller.gamePlay();
    String expectedOutput = "Game started.\n"
        + "Player has been added.\n"
        + "Player: mili Room: Armory\n"
        + "Refresh method called.\n"
        + "Current turn's player object is returned.\n"
        + "04242022\n"
        + "Error pop up called.\n"
        + "Start view called.\n"
        + "Listener is set.\n";
    assertEquals(expectedOutput, log.toString());
  }

  @Test
  public void testSetCommandAddComputerPlayer() {
    StringBuilder log = new StringBuilder();
    MockView mockView = new MockView(log, "04242022");
    Map<String, String> configuration = new HashMap<String, String>();
    configuration.put("filePath", "././res/mansion.txt");
    configuration.put("noOfTurnsString", "10");
    configuration.put("isMocked", "0");
    ControllerInterface controller = new WorldController(configuration, mockView);
    MockModel mockModel = new MockModel(log, "04242022");
    controller.setModelM(mockModel);
    CommandInterface cmd = new AddGamePlayer("mili", "Armory", false);
    controller.setCommand(cmd);
    controller.gamePlay();
    String expectedOutput = "Game started.\n"
        + "Player has been added.\n"
        + "Player: mili Room: Armory\n"
        + "Refresh method called.\n"
        + "Current turn's player object is returned.\n"
        + "04242022\n"
        + "Error pop up called.\n"
        + "Start view called.\n"
        + "Listener is set.\n";
    assertEquals(expectedOutput, log.toString());
  }

  @Test
  public void testSetCommandDisplayPlayer() {
    StringBuilder log = new StringBuilder();
    MockView mockView = new MockView(log, "04242022");
    Map<String, String> configuration = new HashMap<String, String>();
    configuration.put("filePath", "././res/mansion.txt");
    configuration.put("noOfTurnsString", "10");
    configuration.put("isMocked", "0");
    ControllerInterface controller = new WorldController(configuration, mockView);
    MockModel mockModel = new MockModel(log, "04242022");
    controller.setModelM(mockModel);
    CommandInterface cmd = new AddGamePlayer("mili", "Armory", true);
    controller.setCommand(cmd);
    CommandInterface cmd2 = new DisplayPlayer("mili");
    controller.setCommand(cmd2);
    controller.gamePlay();
    String expectedOutput = "Game started.\n"
        +
        "Player has been added.\n"
        +
        "Player: mili Room: Armory\n"
        +
        "Refresh method called.\n"
        +
        "Current turn's player object is returned.\n"
        +
        "04242022\n"
        +
        "Error pop up called.\n"
        +
        "Player information is returned.\n"
        +
        "Player: mili\n"
        +
        "Refresh method called.\n"
        +
        "Current turn's player object is returned.\n"
        +
        "04242022\n"
        +
        "Error pop up called.\n"
        +
        "Start view called.\n"
        +
        "Listener is set.\n";
    assertEquals(expectedOutput, log.toString());
  }

  @Test
  public void testSetCommandDisplayRoom() {
    StringBuilder log = new StringBuilder();
    MockView mockView = new MockView(log, "04242022");
    Map<String, String> configuration = new HashMap<String, String>();
    configuration.put("filePath", "././res/mansion.txt");
    configuration.put("noOfTurnsString", "10");
    configuration.put("isMocked", "0");
    ControllerInterface controller = new WorldController(configuration, mockView);
    MockModel mockModel = new MockModel(log, "04242022");
    controller.setModelM(mockModel);
    CommandInterface cmd = new AddGamePlayer("mili", "Armory", true);
    controller.setCommand(cmd);
    CommandInterface cmd2 = new DisplayRoom("Armory");
    controller.setCommand(cmd2);
    controller.gamePlay();
    String expectedOutput = "Game started.\n"
        +
        "Player has been added.\n"
        +
        "Player: mili Room: Armory\n"
        +
        "Refresh method called.\n"
        +
        "Current turn's player object is returned.\n"
        +
        "04242022\n"
        +
        "Error pop up called.\n"
        +
        "Room information is returned.\n"
        +
        "Room: Armory\n"
        +
        "Refresh method called.\n"
        +
        "Current turn's player object is returned.\n"
        +
        "04242022\n"
        +
        "Error pop up called.\n"
        +
        "Start view called.\n"
        +
        "Listener is set.\n";
    assertEquals(expectedOutput, log.toString());
  }

  @Test
  public void testSetCommandImage() {
    StringBuilder log = new StringBuilder();
    MockView mockView = new MockView(log, "04242022");
    Map<String, String> configuration = new HashMap<String, String>();
    configuration.put("filePath", "././res/mansion.txt");
    configuration.put("noOfTurnsString", "10");
    configuration.put("isMocked", "0");
    ControllerInterface controller = new WorldController(configuration, mockView);
    MockModel mockModel = new MockModel(log, "04242022");
    controller.setModelM(mockModel);
    CommandInterface cmd = new Image();
    controller.setCommand(cmd);
    String expectedOutput = "Game started.\n"
        +
        "Graphic image of the world is returned.\n"
        +
        "Refresh method called.\n"
        +
        "Current turn's player object is returned.\n"
        +
        "04242022\n"
        +
        "Error pop up called.\n";
    assertEquals(expectedOutput, log.toString());
  }

  @Test
  public void testSetCommandLook() {
    StringBuilder log = new StringBuilder();
    MockView mockView = new MockView(log, "04242022");
    Map<String, String> configuration = new HashMap<String, String>();
    configuration.put("filePath", "././res/mansion.txt");
    configuration.put("noOfTurnsString", "10");
    configuration.put("isMocked", "0");
    ControllerInterface controller = new WorldController(configuration, mockView);
    MockModel mockModel = new MockModel(log, "04242022");
    controller.setModelM(mockModel);
    controller.gamePlay();
    CommandInterface cmd = new AddGamePlayer("mili", "Armory", true);
    controller.setCommand(cmd);
    CommandInterface cmd1 = new Look();
    controller.setCommand(cmd1);
    String expectedOutput = "Game started.\n"
        + "Start view called.\n"
        + "Listener is set.\n"
        + "Player has been added.\n"
        + "Player: mili Room: Armory\n"
        + "Refresh method called.\n"
        + "Current turn's player object is returned.\n"
        + "04242022\n"
        + "Error pop up called.\n"
        + "Current turn's player object is returned.\n"
        + "04242022\n"
        + "Player looked around the current room.\n"
        + "Player is null\n"
        + "Refresh method called.\n"
        + "Current turn's player object is returned.\n"
        + "04242022\n"
        + "Error pop up called.\n";
    assertEquals(expectedOutput, log.toString());
  }

  @Test
  public void testSetCommandMove() {
    StringBuilder log = new StringBuilder();
    MockView mockView = new MockView(log, "04242022");
    Map<String, String> configuration = new HashMap<String, String>();
    configuration.put("filePath", "././res/mansion.txt");
    configuration.put("noOfTurnsString", "10");
    configuration.put("isMocked", "0");
    ControllerInterface controller = new WorldController(configuration, mockView);
    MockModel mockModel = new MockModel(log, "04242022");
    controller.setModelM(mockModel);
    controller.gamePlay();
    CommandInterface cmd = new AddGamePlayer("mili", "Armory", true);
    controller.setCommand(cmd);
    CommandInterface cmd2 = new Move("Billiard Room");
    controller.setCommand(cmd2);
    String expectedOutput = "Game started.\n"
        + "Start view called.\n"
        + "Listener is set.\n"
        + "Player has been added.\n"
        + "Player: mili Room: Armory\n"
        + "Refresh method called.\n"
        + "Current turn's player object is returned.\n"
        + "04242022\n"
        + "Error pop up called.\n"
        + "Current turn's player object is returned.\n"
        + "04242022\n"
        + "Player has been moved.\n"
        + "Room: Billiard Room Player is null\n"
        + "Refresh method called.\n"
        + "Current turn's player object is returned.\n"
        + "04242022\n"
        + "Error pop up called.\n";
    assertEquals(expectedOutput, log.toString());
  }

  @Test
  public void testSetCommandPick() {
    StringBuilder log = new StringBuilder();
    MockView mockView = new MockView(log, "04242022");
    Map<String, String> configuration = new HashMap<String, String>();
    configuration.put("filePath", "././res/mansion.txt");
    configuration.put("noOfTurnsString", "10");
    configuration.put("isMocked", "0");
    ControllerInterface controller = new WorldController(configuration, mockView);
    MockModel mockModel = new MockModel(log, "04242022");
    controller.setModelM(mockModel);
    controller.gamePlay();
    CommandInterface cmd = new AddGamePlayer("mili", "Armory", true);
    controller.setCommand(cmd);
    CommandInterface cmd1 = new Pick("Revolver");
    controller.setCommand(cmd1);
    String expectedOutput = "Game started.\n"
        + "Start view called.\n"
        + "Listener is set.\n"
        + "Player has been added.\n"
        + "Player: mili Room: Armory\n"
        + "Refresh method called.\n"
        + "Current turn's player object is returned.\n"
        + "04242022\n"
        + "Error pop up called.\n"
        + "Current turn's player object is returned.\n"
        + "04242022\n"
        + "Player has picked up a weapon.\n"
        + "Weapon: Revolver Player is null\n"
        + "Refresh method called.\n"
        + "Current turn's player object is returned.\n"
        + "04242022\n"
        + "Error pop up called.\n";
    assertEquals(expectedOutput, log.toString());
  }

  @Test
  public void testCommandKillUsingPoke() {
    StringBuilder log = new StringBuilder();
    MockView mockView = new MockView(log, "04242022");
    Map<String, String> configuration = new HashMap<String, String>();
    configuration.put("filePath", "././res/mansion.txt");
    configuration.put("noOfTurnsString", "10");
    configuration.put("isMocked", "0");
    ControllerInterface controller = new WorldController(configuration, mockView);
    MockModel mockModel = new MockModel(log, "04242022");
    controller.setModelM(mockModel);
    controller.gamePlay();
    CommandInterface cmd = new AddGamePlayer("mili", "Armory", true);
    controller.setCommand(cmd);
    CommandInterface cmd1 = new Poke();
    controller.setCommand(cmd1);
    String expectedOutput = "Game started.\n"
        + "Start view called.\n"
        + "Listener is set.\n"
        + "Player has been added.\n"
        + "Player: mili Room: Armory\n"
        + "Refresh method called.\n"
        + "Current turn's player object is returned.\n"
        + "04242022\n"
        + "Error pop up called.\n"
        + "Current turn's player object is returned.\n"
        + "04242022\n"
        + "Target's eye has been poked.\n"
        + "Refresh method called.\n"
        + "Current turn's player object is returned.\n"
        + "04242022\n"
        + "Error pop up called.\n";
    assertEquals(expectedOutput, log.toString());
  }

  @Test
  public void testSetCommandKillUsingWeapon() {
    StringBuilder log = new StringBuilder();
    MockView mockView = new MockView(log, "04242022");
    Map<String, String> configuration = new HashMap<String, String>();
    configuration.put("filePath", "././res/mansion.txt");
    configuration.put("noOfTurnsString", "10");
    configuration.put("isMocked", "0");
    ControllerInterface controller = new WorldController(configuration, mockView);
    MockModel mockModel = new MockModel(log, "04242022");
    controller.setModelM(mockModel);
    controller.gamePlay();
    CommandInterface cmd = new AddGamePlayer("mili", "Armory", true);
    controller.setCommand(cmd);
    CommandInterface cmd1 = new Pick("Revolver");
    controller.setCommand(cmd1);
    CommandInterface cmd2 = new Move("Billiard Room");
    controller.setCommand(cmd2);
    CommandInterface cmd3 = new Move("Dining Hall");
    controller.setCommand(cmd3);
    CommandInterface cmd4 = new Kill("Revolver");
    controller.setCommand(cmd4);
    String expectedOutput = "Game started.\n"
        + "Start view called.\n"
        + "Listener is set.\n"
        + "Player has been added.\n"
        + "Player: mili Room: Armory\n"
        + "Refresh method called.\n"
        + "Current turn's player object is returned.\n"
        + "04242022\n"
        + "Error pop up called.\n"
        + "Current turn's player object is returned.\n"
        + "04242022\n"
        + "Player has picked up a weapon.\n"
        + "Weapon: Revolver Player is null\n"
        + "Refresh method called.\n"
        + "Current turn's player object is returned.\n"
        + "04242022\n"
        + "Error pop up called.\n"
        + "Current turn's player object is returned.\n"
        + "04242022\n"
        + "Player has been moved.\n"
        + "Room: Billiard Room Player is null\n"
        + "Refresh method called.\n"
        + "Current turn's player object is returned.\n"
        + "04242022\n"
        + "Error pop up called.\n"
        + "Current turn's player object is returned.\n"
        + "04242022\n"
        + "Player has been moved.\n"
        + "Room: Dining Hall Player is null\n"
        + "Refresh method called.\n"
        + "Current turn's player object is returned.\n"
        + "04242022\n"
        + "Error pop up called.\n"
        + "Current turn's player object is returned.\n"
        + "04242022\n"
        + "Player has attacked the target with a weapon.\n"
        + "weapon Name: Revolver Player is null\n"
        + "Refresh method called.\n"
        + "Current turn's player object is returned.\n"
        + "04242022\n"
        + "Error pop up called.\n";
    assertEquals(expectedOutput, log.toString());
  }

  @Test
  public void testSetCommandMovePet() {
    StringBuilder log = new StringBuilder();
    MockView mockView = new MockView(log, "04242022");
    Map<String, String> configuration = new HashMap<String, String>();
    configuration.put("filePath", "././res/mansion.txt");
    configuration.put("noOfTurnsString", "10");
    configuration.put("isMocked", "0");
    ControllerInterface controller = new WorldController(configuration, mockView);
    MockModel mockModel = new MockModel(log, "04242022");
    controller.setModelM(mockModel);
    controller.gamePlay();
    CommandInterface cmd = new AddGamePlayer("mili", "Armory", true);
    controller.setCommand(cmd);
    CommandInterface cmd1 = new MovePet("Kitchen");
    controller.setCommand(cmd1);
    String expectedOutput = "Game started.\n"
        + "Start view called.\n"
        + "Listener is set.\n"
        + "Player has been added.\n"
        + "Player: mili Room: Armory\n"
        + "Refresh method called.\n"
        + "Current turn's player object is returned.\n"
        + "04242022\n"
        + "Error pop up called.\n"
        + "Current turn's player object is returned.\n"
        + "04242022\n"
        + "Pet is moved.\n"
        + "Room Name: Kitchen Player is null\n"
        + "Refresh method called.\n"
        + "Current turn's player object is returned.\n"
        + "04242022\n"
        + "Error pop up called.\n";
    assertEquals(expectedOutput, log.toString());
  }

  @Test
  public void testNoOfTurnsOver() {
    StringBuilder log = new StringBuilder();
    MockView mockView = new MockView(log, "04242022");
    Map<String, String> configuration = new HashMap<String, String>();
    configuration.put("filePath", "././res/mansion.txt");
    configuration.put("noOfTurnsString", "1");
    configuration.put("isMocked", "0");
    ControllerInterface controller = new WorldController(configuration, mockView);
    MockModel mockModel = new MockModel(log, "04242022");
    controller.setModelM(mockModel);
    controller.gamePlay();
    CommandInterface cmd = new AddGamePlayer("mili", "Armory", true);
    controller.setCommand(cmd);
    CommandInterface cmd1 = new MovePet("Kitchen");
    controller.setCommand(cmd1);
    String expectedOutput = "Game started.\n"
        +
        "Start view called.\n"
        +
        "Listener is set.\n"
        +
        "Player has been added.\n"
        +
        "Player: mili Room: Armory\n"
        +
        "Refresh method called.\n"
        +
        "Current turn's player object is returned.\n"
        +
        "04242022\n"
        +
        "Error pop up called.\n"
        +
        "Current turn's player object is returned.\n"
        +
        "04242022\n"
        +
        "Pet is moved.\n"
        +
        "Room Name: Kitchen Player is null\n"
        +
        "Refresh method called.\n"
        +
        "Current turn's player object is returned.\n"
        +
        "04242022\n"
        +
        "Error pop up called.\n";
    assertEquals(expectedOutput, log.toString());
  }

  @Test
  public void testPlayerWiningTheGame() {
    StringBuilder log = new StringBuilder();
    MockView mockView = new MockView(log, "04242022");
    Map<String, String> configuration = new HashMap<String, String>();
    configuration.put("filePath", "././res/mansion.txt");
    configuration.put("noOfTurnsString", "5");
    configuration.put("isMocked", "0");
    ControllerInterface controller = new WorldController(configuration, mockView);
    MockModel mockModel = new MockModel(log, "04242022");
    controller.setModelM(mockModel);
    controller.gamePlay();
    CommandInterface cmd = new AddGamePlayer("mili", "Armory", true);
    controller.setCommand(cmd);
    //assuming the health of target character = 1
    CommandInterface cmd1 = new Poke();
    controller.setCommand(cmd1);
    String expectedOutput = "Game started.\n"
        +
        "Start view called.\n"
        +
        "Listener is set.\n"
        +
        "Player has been added.\n"
        +
        "Player: mili Room: Armory\n"
        +
        "Refresh method called.\n"
        +
        "Current turn's player object is returned.\n"
        +
        "04242022\n"
        +
        "Error pop up called.\n"
        +
        "Current turn's player object is returned.\n"
        +
        "04242022\n"
        +
        "Target's eye has been poked.\n"
        +
        "Refresh method called.\n"
        +
        "Current turn's player object is returned.\n"
        +
        "04242022\n"
        +
        "Error pop up called.\n";
    assertEquals(expectedOutput, log.toString());
  }

  @Test
  public void testGetConWorldImage() {
    StringBuilder log = new StringBuilder();
    MockView mockView = new MockView(log, "04242022");
    Map<String, String> configuration = new HashMap<String, String>();
    configuration.put("filePath", "././res/mansion.txt");
    configuration.put("noOfTurnsString", "5");
    configuration.put("isMocked", "0");
    ControllerInterface controller = new WorldController(configuration, mockView);
    MockModel mockModel = new MockModel(log, "04242022");
    controller.setModelM(mockModel);
    controller.gamePlay();
    controller.getConWorldImage();
    String expectedOutput = "Game started.\n"
        +
        "Start view called.\n"
        +
        "Listener is set.\n"
        +
        "Graphic image of the world is returned.\n";
    assertEquals(expectedOutput, log.toString());
  }

  @Test
  public void testGetTurnOwnerRoomName() {
    StringBuilder log = new StringBuilder();
    MockView mockView = new MockView(log, "04242022");
    Map<String, String> configuration = new HashMap<String, String>();
    configuration.put("filePath", "././res/mansion.txt");
    configuration.put("noOfTurnsString", "5");
    configuration.put("isMocked", "0");
    ControllerInterface controller = new WorldController(configuration, mockView);
    MockModel mockModel = new MockModel(log, "04242022");
    controller.setModelM(mockModel);
    controller.gamePlay();
    CommandInterface cmd = new AddGamePlayer("mili", "Armory", true);
    controller.setCommand(cmd);
    controller.getTurnOwnerRoomName();
    String expectedOutput = "Game started.\n"
        +
        "Start view called.\n"
        +
        "Listener is set.\n"
        +
        "Player has been added.\n"
        +
        "Player: mili Room: Armory\n"
        +
        "Refresh method called.\n"
        +
        "Current turn's player object is returned.\n"
        +
        "04242022\n"
        +
        "Error pop up called.\n"
        +
        "Current turn's player's room name is returned.\n";
    assertEquals(expectedOutput, log.toString());
  }

  @Test
  public void testGetPlayerInfo() {
    StringBuilder log = new StringBuilder();
    MockView mockView = new MockView(log, "04242022");
    Map<String, String> configuration = new HashMap<String, String>();
    configuration.put("filePath", "././res/mansion.txt");
    configuration.put("noOfTurnsString", "5");
    configuration.put("isMocked", "0");
    ControllerInterface controller = new WorldController(configuration, mockView);
    MockModel mockModel = new MockModel(log, "04242022");
    controller.setModelM(mockModel);
    controller.gamePlay();
    CommandInterface cmd = new AddGamePlayer("mili", "Armory", true);
    controller.setCommand(cmd);
    controller.getPlayerInfo("mili");
    String expectedOutput = "Game started.\n"
        +
        "Start view called.\n"
        +
        "Listener is set.\n"
        +
        "Player has been added.\n"
        +
        "Player: mili Room: Armory\n"
        +
        "Refresh method called.\n"
        +
        "Current turn's player object is returned.\n"
        +
        "04242022\n"
        +
        "Error pop up called.\n"
        +
        "Player information is returned.\n"
        +
        "Player: mili\n";
    assertEquals(expectedOutput, log.toString());
  }

  @Test
  public void testGetTurnOwner() {
    StringBuilder log = new StringBuilder();
    MockView mockView = new MockView(log, "04242022");
    Map<String, String> configuration = new HashMap<String, String>();
    configuration.put("filePath", "././res/mansion.txt");
    configuration.put("noOfTurnsString", "5");
    configuration.put("isMocked", "0");
    ControllerInterface controller = new WorldController(configuration, mockView);
    MockModel mockModel = new MockModel(log, "04242022");
    controller.setModelM(mockModel);
    controller.gamePlay();
    controller.getTurnOwner();
    String expectedOutput = "Game started.\n"
        + "Start view called.\n"
        + "Listener is set.\n"
        + "Current turn's player object is returned.\n"
        + "04242022\n";
    assertEquals(expectedOutput, log.toString());
  }

}
