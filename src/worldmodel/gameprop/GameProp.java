package worldmodel.gameprop;

/**
 * This interface defines different items and getter methods 
 * to access their attributes in the game.
 *
 * @author shreyas.terdalkar
 *
 */
public interface GameProp {
  
  /**
   * This getter method returns weapon's name.
   *
   * @return name of the weapon
   */
  public String getWeaponName();
  
  /**
   * This getter method returns name of the room where this weapon is placed.
   *
   * @return name of the room containing this weapon
   */
  public String getWeaponRoomName();
  
  /**
   * This setter method sets room where this weapon is initially placed.
   *
   * @param roomName name of the room where weapon is initially kept
   */
  public void setWeaponRoomName(String roomName);
  
  /**
   * This getter method returns name of the player who has picked this weapon.
   *
   * @return name of the player who has picked this weapon
   */
  public String getWeaponPlayerName();
  
  /**
   * This setter method sets player who is picking this weapon.
   *
   * @param playerName name of the player who is picking this weapon
   */
  public void setWeaponPlayerName(String playerName);

  /**
   * Returns kill power of this weapon that is to be used.
   *
   * @return kill power represents kill power of the weapon
   */
  public int getKillPower();
  

}
