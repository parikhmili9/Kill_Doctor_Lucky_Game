package worldmodel.character;

/**
 * This interface defines various characters including
 * the Target Characters and other players in the game.
 *
 * @author shreyas.terdalkar
 *
 */
public interface GameCharacter {

  /**
   * This method updates Character's current location.
   *
   * @param location of the character
   */
  public void updateLocation(int location);
  
  /**
   * This getter method returns current location of the character.
   *
   * @return current location of the character
   */
  public int getLocation();

  /**
   * Returns name of the Character in the game.
   *
   * @return name of the Character
   */
  public String getName();

  /**
   * Returns rank of the character in the game.
   *
   * @return rank of the character
   */
  public int getRank();
  
  /**
   * This method determines if the turnOwner is a computer.
   *
   * @return true if the turnOwner is computer
   */
  public boolean isComputer();
}
