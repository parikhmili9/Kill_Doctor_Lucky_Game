package worldmodel.room;

import java.util.List;

/**
 * Room interface defines creation of rooms within the game
 * and getter methods to access its different attributes.
 *
 * @author shreyas.terdalkar
 *
 */
public interface Room {
  
  /**
   * This getter method returns Room Index of specified room object.
   *
   * @return represents room index of mansion rooms
   */
  public int getRoomIndex();
  
  /**
   * This getter method returns Room Name of specified room object.
   *
   * @return represents Room Name of mansion rooms
   */
  public String getRoomName();
  
  /**
   * This getter method returns top wall specification of
   * given room object.
   *
   * @return top wall represents top wall specification of given room
   */
  public int getX1();
  
  /**
   * This getter method returns bottom wall specification of
   * given room object.
   *
   * @return bottom wall represents bottom wall specification of given room
   */
  public int getX2();
  
  /**
   * This getter method returns left wall specification of
   * given room object.
   *
   * @return left wall represents left wall specification of given room
   */
  public int getY1();
  
  /**
   * This getter method returns right wall specification of
   * given room object.
   *
   * @return right wall represents right wall specification of given room
   */
  public int getY2();
  
  /**
   * This getter method returns Target Character Name if it is in the room
   * at present.
   *
   * @return String containing Target Character Name
   */
  public String getTarget();
  
  /**
   * This method adds Target Character when it enters this room.
   *
   * @param targetCharacterName represents name of the target character
   */
  public void addTarget(String targetCharacterName);
  
  /**
   * This method removes Target Character when it moves to next room.
   */
  public void removeTarget();
  
  /**
   * This getter method returns list of all players present in the room.
   *
   * @return list of strings of player names
   */
  public List<String> getPlayer();
  
  /**
   * This method adds player/s to this room.
   *
   * @param playerName represents name of the player being added
   */
  public void addPlayer(String playerName);
  
  /**
   * This method removes player/s from this room.
   *
   * @param playerName represents name of the player being removed
   */
  public void removePlayer(String playerName);
  
  /**
   * This getter method returns list of weapons in the room.
   *
   * @return list of strings of weapon names in this room
   */
  public List<String> getWeapon();
  
  /**
   * This method adds weapon/s to this room.
   *
   * @param weaponName name of the weapon being added
   */
  public void addWeapon(String weaponName);
  
  /**
   * This method removes weapon/s from this room.
   *
   * @param weaponName name of the weapon being added
   */
  public void removeWeapon(String weaponName);
  
  /**
   * This method verifies if this room has Target Character.
   *
   * @return true if this room has Target Character
   */
  public boolean hasTarget();
  
  /**
   * This method verifies if this room has any player.
   *
   * @return true if this room has any player
   */
  public boolean hasPlayer();
  
  /**
   * This method verifies if this room has any weapon.
   *
   * @return true if this room has any weapon
   */
  public boolean hasWeapon();
  
  /**
   * This getter method returns Target Character's Pet's Name if it is in the room
   * at present.
   *
   * @return String containing Target Character's pet's Name
   */
  public String getPet();
  
  /**
   * This method adds Target Character's pet when it enters this room.
   *
   * @param petCharacterName represents name of the target character
   */
  public void addPet(String petCharacterName);
  
  /**
   * This method removes Target Character's pet when it moves to next room.
   */
  public void removePet();

  /**
   * This method verifies if this room has Target Character's pet.
   *
   * @return true if this room has Target Character's pet
   */
  boolean hasPet();
}
