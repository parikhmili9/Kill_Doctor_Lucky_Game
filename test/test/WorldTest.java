package test;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import worldmodel.model.WorldInterface;
import worldmodel.model.WorldModel;

/**
 * This class tests the game model.
 *
 * @author shreyas.terdalkar
 *
 */
public class WorldTest {

  private WorldInterface model;
  private Map<String, String> configuration;

  /**
   * Setup method.
   */
  @Before
  public void setUp() {
    configuration = new HashMap<String, String>();
    configuration.put("filePath", "././res/mansion.txt");
    configuration.put("noOfTurnsString", "100");
    configuration.put("isMocked", "0");
    model = new WorldModel(configuration);
  }
  
  @Test
  public void addHumanPlayerTest() {
    model.addPlayer("mili", "Armory", true);
    String expected = "Name of the player : mili\n"
        + "Location of the player : Armory\n";
    assertEquals(expected, model.getPlayerInfo("mili"));
  }
  
  @Test
  public void addComputerPlayerTest() {
    model.addPlayer("mili", "Armory", false);
    String expected = "Name of the player : mili\n"
        + "Location of the player : Armory\n";
    assertEquals(expected, model.getPlayerInfo("mili"));
    
  }
  
  @Test
  public void getRoomInfoTest() {
    model.addPlayer("mili", "Armory", true);
    String expected = "Name of the room : Armory\n"
        + "Neighbours of the room : [Dining Hall, Drawing Room, Billiard Room]\n"
        + "Weapon/s in the room : [Revolver]\n"
        + "Target in the room : Doctor Lucky\n"
        + "Pet in the room : Fortune the Cat\n"
        + "Player/s in the room : [mili]\n";
    assertEquals(expected, model.getRoomInfo("Armory"));
    
  }
  
  @Test
  public void getRoomDetailsTest() {
    String expected = "{0=null, "
        + "1={RoomCoordinates=[16, 21, 22, 29], "
        + "RoomTarget=null, "
        + "RoomName=[Billiard Room], "
        + "RoomWeapons=[Billiard Cue], "
        + "RoomPlayers=null}, "
        + "2={RoomCoordinates=[28, 0, 36, 6], "
        + "RoomTarget=null, "
        + "RoomName=[Carriage House], "
        + "RoomWeapons=[Chain Saw, Big Red Hammer], "
        + "RoomPlayers=null}, "
        + "3={RoomCoordinates=[12, 11, 22, 21], "
        + "RoomTarget=null, "
        + "RoomName=[Dining Hall], "
        + "RoomWeapons=null, "
        + "RoomPlayers=null}, "
        + "4={RoomCoordinates=[22, 13, 26, 19], "
        + "RoomTarget=null, "
        + "RoomName=[Drawing Room], "
        + "RoomWeapons=[Letter Opener], "
        + "RoomPlayers=null}, "
        + "5={RoomCoordinates=[26, 13, 28, 19], "
        + "RoomTarget=null, "
        + "RoomName=[Foyer], "
        + "RoomWeapons=null, "
        + "RoomPlayers=null}, "
        + "6={RoomCoordinates=[28, 26, 36, 30], "
        + "RoomTarget=null, "
        + "RoomName=[Green House], "
        + "RoomWeapons=[Trowel, Pinking Shears], "
        + "RoomPlayers=null}, "
        + "7={RoomCoordinates=[30, 20, 36, 26], "
        + "RoomTarget=null, RoomName=[Hedge Maze], "
        + "RoomWeapons=[Loud Noise], RoomPlayers=null}, "
        + "8={RoomCoordinates=[16, 3, 22, 11], "
        + "RoomTarget=null, RoomName=[Kitchen], "
        + "RoomWeapons=[Crepe Pan, Sharp Knife], "
        + "RoomPlayers=null}, 9={RoomCoordinates=[0, 3, 6, 9], "
        + "RoomTarget=null, RoomName=[Lancaster Room], RoomWeapons=[Silken Cord], "
        + "RoomPlayers=null}, 10={RoomCoordinates=[4, 23, 10, 29], RoomTarget=null, "
        + "RoomName=[Library], RoomWeapons=null, RoomPlayers=null}, "
        + "11={RoomCoordinates=[2, 9, 8, 15], RoomTarget=null, "
        + "RoomName=[Lilac Room], RoomWeapons=[Tight Hat], RoomPlayers=null}, "
        + "12={RoomCoordinates=[2, 15, 8, 23], RoomTarget=null, RoomName=[Master Suite], "
        + "RoomWeapons=[Shoe Horn], RoomPlayers=null}, "
        + "13={RoomCoordinates=[0, 23, 4, 29], RoomTarget=null, "
        + "RoomName=[Nursery], RoomWeapons=[Bad Cream], RoomPlayers=null}, "
        + "14={RoomCoordinates=[10, 5, 16, 11], RoomTarget=null, RoomName=[Parlor], "
        + "RoomWeapons=null, RoomPlayers=null}, 15={RoomCoordinates=[28, 12, 36, 20], "
        + "RoomTarget=null, RoomName=[Piazza], RoomWeapons=[Civil War Cannon], "
        + "RoomPlayers=null}, 16={RoomCoordinates=[6, 3, 10, 9], RoomTarget=null, "
        + "RoomName=[Servants' Quarters], RoomWeapons=[Broom Stick], "
        + "RoomPlayers=null}, 17={RoomCoordinates=[8, 11, 12, 21], "
        + "RoomTarget=null, RoomName=[Tennessee Room], "
        + "RoomWeapons=null, RoomPlayers=null}, 18={RoomCoordinates=[10, 21, 16, 27], "
        + "RoomTarget=null, RoomName=[Trophy Room], "
        + "RoomWeapons=[Duck Decoy, Monkey Hand], RoomPlayers=null}, "
        + "19={RoomCoordinates=[22, 5, 24, 13], RoomTarget=null, "
        + "RoomName=[Wine Cellar], RoomWeapons=[Rat Poison, Piece of Rope], "
        + "RoomPlayers=null}, 20={RoomCoordinates=[30, 6, 36, 12], RoomTarget=null, "
        + "RoomName=[Winter Garden], RoomWeapons=null, RoomPlayers=null}}";
    assertEquals(expected, model.getRoomDetails().toString());
  }
  
  @Test
  public void pickWeaponTest() {
    model.addPlayer("mili", "Armory", true);
    model.pickWeapon("Revolver", model.getTurnOwner());
    String expected = "Name of the player : mili\n"
        + "Location of the player : Armory\n"
        + "Player has weapon/s : [Revolver]\n"
        + "";
    assertEquals(expected, model.getPlayerInfo("mili"));
    
  }
  
  @Test
  public void movePlayerTest() {
    model.addPlayer("mili", "Armory", true);
    model.movePlayer("Billiard Room", model.getTurnOwner());
    String expected = "Name of the room : Billiard Room\n"
        + "Neighbours of the room : [Trophy Room, Dining Hall, Armory]\n"
        + "Weapon/s in the room : [Billiard Cue]\n"
        + "Target in the room : Doctor Lucky\n"
        + "Player/s in the room : [mili]\n"
        + "";
    assertEquals(expected, model.getRoomInfo("Billiard Room"));
    
  }
  
  @Test
  public void lookTest() {
    model.addPlayer("mili", "Armory", true);
    String expected = "Name of the room : Armory\n"
        + "Neighbours of the room : [Dining Hall, Drawing Room, Billiard Room]\n"
        + "Weapon/s in the room : [Revolver]\n"
        + "Target in the room : Doctor Lucky\n"
        + "Pet in the room : Fortune the Cat\n"
        + "Player/s in the room : [mili]\n"
        + "Neighbour Info: \n"
        + "Name of the room : Dining Hall\n"
        + "Neighbours of the room : [Tennessee Room, Trophy Room, Kitchen, Drawing Room, "
        + "Armory, Billiard Room, Parlor, Wine Cellar]\n"
        + "Neighbour Info: \n"
        + "Name of the room : Drawing Room\n"
        + "Neighbours of the room : [Foyer, Dining Hall, Armory, Wine Cellar]\n"
        + "Weapon/s in the room : [Letter Opener]\n"
        + "Neighbour Info: \n"
        + "Name of the room : Billiard Room\n"
        + "Neighbours of the room : [Trophy Room, Dining Hall, Armory]\n"
        + "Weapon/s in the room : [Billiard Cue]\n"
        + "";
    assertEquals(expected, model.look(model.getTurnOwner()));
    
  }
  
  @Test
  public void targetMovementTest() {
    model.addPlayer("mili", "Armory", true);
    model.movePlayer("Billiard Room", model.getTurnOwner());
    String expected = "Name of the room : Billiard Room\n"
        + "Neighbours of the room : [Trophy Room, Dining Hall, Armory]\n"
        + "Weapon/s in the room : [Billiard Cue]\n"
        + "Target in the room : Doctor Lucky\n"
        + "Player/s in the room : [mili]\n"
        + "";
    assertEquals(expected, model.getRoomInfo("Billiard Room"));
    
  }
  
  @Test
  public void targetPetMovementTest() {
    model.addPlayer("mili", "Armory", true);
    model.movePlayer("Billiard Room", model.getTurnOwner());
    String expected = "Name of the room : Carriage House\n"
        + "Neighbours of the room : [Winter Garden]\n"
        + "Weapon/s in the room : [Chain Saw, Big Red Hammer]\n"
        + "Pet in the room : Fortune the Cat\n"
        + "";
    assertEquals(expected, model.getRoomInfo("Carriage House"));
    
  }
  
  @Test
  public void movePetTest() {
    model.addPlayer("mili", "Armory", true);
    model.movePetCharacter("Billiard Room", model.getTurnOwner());
    String expected = "Name of the room : Dining Hall\n"
        + "Neighbours of the room : [Tennessee Room, Trophy Room, Kitchen, "
        + "Drawing Room, Armory, Billiard Room, Parlor, Wine Cellar]\n"
        + "Pet in the room : Fortune the Cat\n"
        + "";
    assertEquals(expected, model.getRoomInfo("Dining Hall"));
    
  }
  
  @Test
  public void pokeTest() {
    model.addPlayer("mili", "Armory", true);
    model.poke(model.getTurnOwner());
    int expected = 49;
    assertEquals(expected, model.getTargetHealth());
    
  }
  
  @Test
  public void attackTest() {
    model.addPlayer("mili", "Billiard Room", true);
    model.pickWeapon("Billiard Cue", model.getTurnOwner());
    model.killAttempt("Billiard Cue", model.getTurnOwner());
    int expected = 48;
    assertEquals(expected, model.getTargetHealth());
    
  }
  
  @Test (expected = IllegalStateException.class)
  public void gameOverTest() {
    Map<String, String> newconfiguration = new HashMap<String, String>();
    newconfiguration.put("filePath", "././res/mansion.txt");
    newconfiguration.put("noOfTurnsString", "1");
    newconfiguration.put("isMocked", "0");
    WorldInterface newmodel = new WorldModel(newconfiguration);
    newmodel.addPlayer("mili", "Armory", true);
    newmodel.look(newmodel.getTurnOwner());
    
  }
  
  @Test (expected = IllegalStateException.class)
  public void gameWinnerTest() {
    Map<String, String> newconfiguration = new HashMap<String, String>();
    newconfiguration.put("filePath", "././res/mansion.txt");
    newconfiguration.put("noOfTurnsString", "1");
    newconfiguration.put("isMocked", "0");
    WorldInterface newmodel = new WorldModel(newconfiguration);
    newmodel.addPlayer("mili", "Armory", true);
    newmodel.poke(newmodel.getTurnOwner());
    String expected = "mili";
    assertEquals(expected, newmodel.getWinner().getName());
    
  }
}
