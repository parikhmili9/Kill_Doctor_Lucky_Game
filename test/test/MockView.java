package test;

import controller.ControllerInterface;
import view.WorldViewInterface;

/**
 * MockView class assists in mocking of the world view in order to test
 * the world controller in isolation.
 *
 */
public class MockView implements WorldViewInterface {

  private StringBuilder log;

  /**
   * This method constructs the MockView by initializing the logs and unique codes
   * for the mock view.
   *
   * @param log represents input logs of the mock model
   */
  public MockView(StringBuilder log, String uniqueCode) {
    this.log = log;
  }

  @Override
  public void makeVisible(boolean input) {
    log.append("The view is made visible.").append("\n");
  }

  @Override
  public void refresh() {
    log.append("Refresh method called.").append("\n");
  }

  @Override
  public void startView() {
    log.append("Start view called.").append("\n");
  }

  @Override
  public void setListener(ControllerInterface worldController) {
    log.append("Listener is set.").append("\n");
  }

  @Override
  public void showErrorPopup(String errorMessage) {
    log.append("Error pop up called.").append("\n");
  }

  @Override
  public void quitView() {
    log.append("Quit view called.").append("\n");
  }

  @Override
  public void startGame() {
    log.append("Game started.").append("\n");
  }

  @Override
  public void gameEnd(String string) {
    log.append("Game ended.").append("\n");
  }
}
