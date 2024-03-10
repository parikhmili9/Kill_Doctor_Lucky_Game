package worldmodel.character;

/**
 * This class creates the target character's pet in the game world.
 *
 * @author shreyas.terdalkar
 *
 */
public class Pet implements GameCharacter {
  
  private final String petName;
  private int locationRoomIndex;
  private final int playerRank;
  
  /**
   * This constructor creates the character of Target's pet by initializing 
   * its name and location.
   *
   * @param petName represents name of the pet
   * @param locationRoomIndex represents index of the room where pet is currently in.
   * @param playerRank rank of the pet
   */
  public Pet(String petName, int locationRoomIndex, int playerRank) {
    
    if (petName == null) {
      throw new IllegalArgumentException("Pet name cannot be null");
    }
    
    this.petName = petName;
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
    return this.petName;
  }
  
  @Override
  public int getRank() {
    return this.playerRank;
  }


  @Override
  public boolean isComputer() {
    return false;
  }

}
