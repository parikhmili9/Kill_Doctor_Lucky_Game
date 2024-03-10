package worldmodel.character;

import java.util.List;

/**
 * PlayerInterface defines player and the methods specific to players
 * of the game.
 *
 * @author shreyas.terdalkar
 *
 */
public interface PlayerInterface extends GameCharacter {
  
  /**
   * Returns list of string names of the weapons picked by the player.
   *
   * @return list of string names of the weapons
   */
  public List<String> getWeaponList();
  
  /**
   * This method adds new weapons picked by the player.
   *
   * @param weaponName name of the weapon picked
   */
  public void addWeaponList(String weaponName);
  
  /**
   * This method removes weapons from the player.
   *
   * @param weaponName name of the weapon
   */
  public void removeWeaponList(String weaponName);
  
  /**
   * This method verifies if the player has any weapon.
   *
   * @return true if this player has any weapon, otherwise returns false
   */
  public boolean hasWeapon();

}
