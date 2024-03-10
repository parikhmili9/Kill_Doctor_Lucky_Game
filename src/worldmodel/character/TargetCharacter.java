package worldmodel.character;

/**
 * This class creates the target character in the game world.
 *
 * @author shreyas.terdalkar
 *
 */
public class TargetCharacter implements TargetInterface {
  
  private final String name;
  private int health;
  private int locationRoomIndex;
  private final int playerRank;
  
  
  /**
   * This constructor creates the character of Doctor Lucky by initializing its name and health.
   *
   * @param locationRoomIndex represents index of the room where target character is currently in.
   * @param playerRank rank of the target character
   * @param health represents Doctor Lucky's health.
   * @param name represents Doctor Lucky's name.
   */
  public TargetCharacter(int locationRoomIndex, int playerRank, int health, String name) {
    
    if (name == null) {
      throw new IllegalArgumentException("Target Character name cannot be null");
    }
    
    this.health = health;
    this.name = name;
    this.locationRoomIndex = locationRoomIndex;
    this.playerRank = playerRank;
  }
  
  @Override
  public void updateLocation(int locationRoomIndex) {
    this.locationRoomIndex = locationRoomIndex;
  }
  
  @Override
  public int getLocation() {
    return this.locationRoomIndex;
  }
  
  @Override
  public String getName() {
    return this.name;
  }
  
  @Override
  public int getRank() {
    return this.playerRank;
  }
  
  @Override
  public void setHealth(int health) {
    this.health = health;
  }
  
  @Override
  public int getHealth() {
    return this.health;
  }

  @Override
  public boolean isComputer() {
    return false;
  }
}
