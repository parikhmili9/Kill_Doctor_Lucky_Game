package worldmodel.character;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class creates a player character the game world.
 *
 * @author shreyas.terdalkar
 *
 */
public class Player implements PlayerInterface {
  
  private final String playerName;
  private final int playerRank;
  private int locationRoomIndex;
  private List<String> weaponList;
  private boolean isHuman;
  
  /**
   * This constructor creates a player character by initializing 
   * its name location and rank in series of turns.
   *
   * @param currentRank represents rank of the player in series of turns
   * @param playerName represents name of the player
   * @param locationRoomIndex represents location room of the player
   * @param isHuman represents flag that determines if the player is human or computer
   */
  public Player(int currentRank, String playerName, int locationRoomIndex, boolean isHuman) {
    
    if (playerName == null) {
      throw new IllegalArgumentException("Player name cannot be null");
    }
    
    this.playerRank = currentRank;
    this.playerName = playerName;
    this.locationRoomIndex = locationRoomIndex;
    this.weaponList = null;
    this.isHuman = isHuman;
  }

  @Override
  public void updateLocation(int locationRoomIndex) {
    this.locationRoomIndex = locationRoomIndex;

  }

  @Override
  public int getLocation() {
    return locationRoomIndex;
  }
  
  @Override
  public String getName() {
    return this.playerName;
  }
  
  @Override
  public int getRank() {
    return this.playerRank;
  }
  
  @Override
  public List<String> getWeaponList() {
    if (this.weaponList == null) {
      return null;
    } else {
      List<String> list2 = this.weaponList.stream().collect(Collectors.toList());
      return list2;
    }
  }
  
  @Override
  public void addWeaponList(String weaponName) {
    if (weaponName == null) {
      throw new IllegalArgumentException("input cannot be null");
    }
    if (this.weaponList == null) {
      this.weaponList = new ArrayList<String>();
    }
    this.weaponList.add(weaponName);
  }
  
  @Override
  public void removeWeaponList(String weaponName) {
    if (weaponName == null) {
      throw new IllegalArgumentException("input cannot be null");
    }
    this.weaponList.remove(weaponName);
    //System.out.println(this.weaponList);
  }
  
  @Override
  public boolean hasWeapon() {
    if (this.weaponList == null || this.weaponList.isEmpty()) {
      return false;
    } else {
      return true;
    }
  }

  @Override
  public boolean isComputer() {
    if (this.isHuman) {
      return false;
    } else {
      return true;
    }
  }

}
