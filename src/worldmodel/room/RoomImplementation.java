package worldmodel.room;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates and implements different rooms in Doctor Lucky's mansion.
 *
 * @author shreyas.terdalkar
 *
 */
public class RoomImplementation implements Room {
  
  private final String roomName;
  private final int roomIndex;
  private final int leftWall;
  private final int rightWall;
  private final int topWall;
  private final int bottomWall;
  private final int x1;
  private final int y1;
  private final int x2;
  private final int y2;
  private List<String> playerInTheRoom;
  private List<String> weaponInTheRoom;
  private String targetInTheRoom;
  private String petInTheRoom;
  
  /**
   * Constructs room in Doctor Lucky's mansion by initializing its co-ordinates, 
   * name and index order.
   *
   * @param roomIndex represents room index
   * @param roomName represents room name
   * @param leftWall represents right wall
   * @param rightWall represents left wall
   * @param topWall represents top wall
   * @param bottomWall represents bottom wall
   */
  public RoomImplementation(int roomIndex, String roomName, int leftWall, int rightWall, 
      int topWall, int bottomWall) {
    
    if (roomName == null) {
      throw new IllegalArgumentException("room name cannot be null");
    }
    
    this.roomIndex = roomIndex;
    this.roomName = roomName;
    this.leftWall = leftWall;
    this.rightWall = rightWall;
    this.topWall = topWall;
    this.bottomWall = bottomWall;
    
    this.x1 = this.topWall;
    this.y1 = this.leftWall;
    this.x2 = this.bottomWall + 1;
    this.y2 = this.rightWall + 1;
    
    this.targetInTheRoom = null;
    this.petInTheRoom = null;
    this.playerInTheRoom = new ArrayList<String>();
    this.weaponInTheRoom = new ArrayList<String>();
    
  }
  
  @Override
  public int getRoomIndex() {
    return this.roomIndex;
  }
  
  @Override
  public String getRoomName() {
    return this.roomName;
  }
  
  @Override
  public int getX1() {
    return this.x1;
  }
  
  @Override
  public int getX2() {
    return this.x2;
  }
  
  @Override
  public int getY1() {
    return this.y1;
  }
  
  @Override
  public int getY2() {
    return this.y2;
  }
  
  @Override
  public String getTarget() {
    return new String(this.targetInTheRoom);
  }
  
  @Override
  public void addTarget(String targetCharacterName) {
    if (targetCharacterName == null) {
      throw new IllegalArgumentException("input cannot be null");
    }
    this.targetInTheRoom = targetCharacterName;
  }
  
  @Override
  public void removeTarget() {
    this.targetInTheRoom = null;
  }
  
  @Override
  public List<String> getPlayer() {
    return new ArrayList<String>(this.playerInTheRoom);
  }
  
  @Override
  public void addPlayer(String playerName) {
    if (playerName == null) {
      throw new IllegalArgumentException("input cannot be null");
    }
    this.playerInTheRoom.add(playerName);
  }
  
  @Override
  public void removePlayer(String playerName) {
    if (playerName == null) {
      throw new IllegalArgumentException("input cannot be null");
    }
    this.playerInTheRoom.remove(playerName);
  }
  
  @Override
  public List<String> getWeapon() {
    return new ArrayList<String>(this.weaponInTheRoom);
  }
  
  @Override
  public void addWeapon(String weaponName) {
    if (weaponName == null) {
      throw new IllegalArgumentException("input cannot be null");
    }
    this.weaponInTheRoom.add(weaponName);
  }
  
  @Override
  public void removeWeapon(String weaponName) {
    if (weaponName == null) {
      throw new IllegalArgumentException("input cannot be null");
    }
    this.weaponInTheRoom.remove(weaponName);
  }
  
  @Override
  public boolean hasTarget() {
    if (this.targetInTheRoom != null) {
      return true;
    } else {
      return false;
    }
  }
  
  @Override
  public boolean hasPlayer() {
    if (this.playerInTheRoom.isEmpty()) {
      return false;
    } else {
      return true;
    }
  }

  @Override
  public boolean hasWeapon() {
    if (this.weaponInTheRoom.isEmpty()) {
      return false;
    } else {
      return true;
    }
  }

  @Override
  public String getPet() {
    return new String(this.petInTheRoom);
  }

  @Override
  public void addPet(String petCharacterName) {
    
    if (petCharacterName == null) {
      throw new IllegalArgumentException("pet name cannot be null");
    }
    this.petInTheRoom = petCharacterName;
  }

  @Override
  public void removePet() {
    this.petInTheRoom = null;
  }
  
  @Override
  public boolean hasPet() {
    if (this.petInTheRoom != null) {
      return true;
    } else {
      return false;
    }
  }
}
