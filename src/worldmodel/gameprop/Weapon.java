package worldmodel.gameprop;

/**
 * This class creates weapons used for attempting murder of Doctor Lucky.
 * It initializes Weapon's location, name and kill value.
 *
 * @author shreyas.terdalkar
 *
 */
public class Weapon implements GameProp {
  
  private final String weaponName;
  private final int weaponPower;
  private String weaponRoomName;
  private String playerName;
  
  /**
   * This constructor implements creation of weapons within Doctor Lucky's world
   * by initializing its name, power and location.
   *
   * @param weaponRoomName represents room location name of weapon.
   * @param weaponPower represents weapon's kill power.
   * @param weaponName represents weapon's name
   */
  public Weapon(String weaponRoomName, int weaponPower, String weaponName) {
    
    if (weaponRoomName == null || weaponName == null) {
      throw new IllegalArgumentException("input cannot be null");
    }
    
    this.weaponPower = weaponPower;
    this.weaponName = weaponName;
    this.weaponRoomName = weaponRoomName;
    this.playerName = null;
    
  }
  
  @Override
  public String getWeaponName() {
    return weaponName;
  }
  
  @Override
  public String getWeaponRoomName() {
    return weaponRoomName;
  }
  
  @Override
  public void setWeaponRoomName(String roomName) {
    //roomName can be null if the weapon is to be collected by player
    this.weaponRoomName = roomName;
  }
  
  @Override
  public String getWeaponPlayerName() {
    return playerName;
  }
  
  @Override
  public void setWeaponPlayerName(String playerName) {
    if (playerName == null) {
      throw new IllegalArgumentException("input cannot be null");
    }
    this.playerName = playerName;
  }

  @Override
  public int getKillPower() {
    return this.weaponPower;
  }
}
