package view;

import controller.ControllerInterface;

/**
 * This class defines the functions responsible for graphical
 * implementation of the game.
 *
 * @author shreyas.terdalkar
 *
 */
public interface WorldViewInterface {

  /**
   * This method makes the game frame visible.
   *
   * @param input true to set game frame visible, otherwise false
   */
  public void makeVisible(boolean input);


  /**
   * This method is used to refresh the view.
   */
  public void refresh();

  /**
   * This method is used to start the view of the game.
   */
  public void startView();

  /**
   * This method sets the listeners for the view for
   * player actions to be performed.
   *
   * @param worldController the controller of the game
   */
  public void setListener(ControllerInterface worldController);
  
  /**
   * This method is used to display error message during the game play.
   *
   * @param errorMessage the string containing the error message
   */
  public void showErrorPopup(String errorMessage);

  /**
   * This method is used to quit the view.
   */
  public void quitView();
  
  /**
   * This method is used to start the game by initiating game world.
   */
  public void startGame();


  /**
   * This method is used to finish the game if the game is over.
   *
   * @param string the message of the game ending
   */
  public void gameEnd(String string);

}
