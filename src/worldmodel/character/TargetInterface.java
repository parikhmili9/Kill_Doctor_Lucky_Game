package worldmodel.character;

/**
 * This interface defines behaviour specific to the
 * target character in the game.
 *
 * @author shreyas.terdalkar
 *
 */
public interface TargetInterface extends GameCharacter {

  /**
   * This method updates target character's health after a blow.
   *
   * @param health represents health of the target character
   */
  public void setHealth(int health);
  
  /**
   * Returns health of the target character in the game.
   *
   * @return health of the target character
   */
  public int getHealth();
}
