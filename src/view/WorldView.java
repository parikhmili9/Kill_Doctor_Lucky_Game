package view;

import controller.ControllerInterface;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

/**
 * This class represents the view implementation of the board game.
 * It displays the graphical representation of the game world, various
 * details necessary to play the game and updates itself as the game progresses.
 *
 * @author shreyas.terdalkar
 *
 */
public class WorldView extends JFrame implements WorldViewInterface {

  /**
   * The serial version uid.
   */
  private static final long serialVersionUID = -5870728866392098181L;
  private JMenuBar menuBar;
  private JMenu newGame;
  private JMenu options;
  private JMenuItem currentGame;
  private JMenuItem customGame;
  private JMenuItem quitOption;
  private JMenuItem helpOption;
  private GamePanel gamePanel;
  private JPanel tempPanel;
  private ControllerInterface listener;
  
  /**
   * This method constructs the visual graphical representation of the game.
   */
  public WorldView() {
    super("Kill Doctor Lucky Board Game");

    createView();
    
    this.tempPanel = new WelcomePanel();
    this.add(BorderLayout.CENTER, tempPanel);
    
    this.gamePanel = null;
    this.listener = null;
  }

  @Override
  public void startView() {
    this.makeVisible(true);
  }
  
  @Override
  public void makeVisible(boolean input) {
    this.setVisible(input);
  }

  @Override
  public void refresh() {
    //gamePanel.refresh();
    repaint();
    gamePanel.refresh();
    //gamePanel.setListener();
    gamePanel.requestFocus();
  }
  
  //original
//  @Override
//  public void refresh() {
//    System.out.println("view refresh");
//    repaint();
//    gamePanel.refresh();
//    gamePanel.setListener();
//    gamePanel.requestFocus();
//  }

  @Override
  public void setListener(ControllerInterface listener) {
    if (listener == null) {
      throw new IllegalArgumentException("controller cannot be null");
    }
    this.listener = listener;
    
    this.quitOption.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        quitView();
        listener.quitGame();
      }
    });
    
    this.helpOption.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        StringBuilder helpInfo = new StringBuilder();
        helpInfo.append("The game begins by displaying Welcome to Doctor Lucky Board Game.\n")
            .append("There are 2 options in the menu bar. Start a game or other options.\n")
            .append("Click on Quit under options to quit the game any time during the play.\n")
            .append("Click on Help to enter this dialog. This contains all the instructions on ")
            .append("how to play the game.\n")
            .append("Select Enter the world into Doctor Lucky's Mansion to start the game with ")
            .append("preloaded specifications.\n")
            .append("Select Enter the world of your choice to start the game with custom-made ")
            .append("game specifications.\n")
            .append("Upon selecting second option, enter the filepath for the custom-made game ")
            .append("to start the game.\n")
            .append("Once the game begins, the game board will appear.\n")
            .append("Top left corner displays the target health and information about its ")
            .append("movements\n")
            .append("Top right corner displays no of turns left before the game to end and ")
            .append("whose turn it is.\n")
            .append("Top center part contains two buttons to add human and computer players ")
            .append("to play the game.\n")
            .append("Select respective buttons, enter the name of player ( it should be single ")
            .append("string without space)\n")
            .append("and name of the room where the player will enter. Room names must be exactly ")
            .append("as per specifications.\n")
            .append("Once a player enters the game, the game begins by alloting turn to the ")
            .append("first player added.\n")
            .append("The cental part of the game board displays the graphical representation ")
            .append("of the game world.\n")
            .append("The players can be added any time during the game. They will be alloted ")
            .append("turns in the order of their addition.\n")
            .append("The bottom part of the game board displays status of the game during ")
            .append("the game play.\n")
            .append("The game board displays the world with its different rooms and the target.\n")
            .append("The game board displays players as they enter the world. You can click ")
            .append("on the player icon of the player\n")
            .append("with current turn to display the player information.\n")
            .append("The game also has a target's pet. It is invisible and makes everything ")
            .append("around it invisible.\n")
            .append("The pet enters the world along with the target and keeps wandering on ")
            .append("each turn making others in its room invisible.\n")
            .append("The target wanders in the world as well evading itself ")
            .append("from getting targetted.\n")
            .append("The player is able to move to its neighbouring rooms by clicking on ")
            .append("it when it is its turn.\n")
            .append("The player is able to pick a weapon to attack the target by ")
            .append("pressing P on keyboard\n")
            .append("The player is given suggestion on what to pick and must enter the ")
            .append("weapon name to pick it up.\n")
            .append("Each player is allowed 3 weapons to be picked at a time.\n")
            .append("The player is able to look around to plan its moves ")
            .append("by pressing L on keyboard.\n")
            .append("This displays all the information regarding the player's room ")
            .append("and its neighbours.\n")
            .append("The player is able to move the pet to its desired location by ")
            .append("pressing M on the keyboard.\n")
            .append("Enter the room to put the pet in that room however, the pet ")
            .append("wanders off to another location immediately.\n")
            .append("If the player is in the same room as the target it can attack the ")
            .append("target by pressing A on keyboard.\n")
            .append("This displays the available weapons with the player from which the ")
            .append("player can choose to attack with.\n")
            .append("The player can always choose poke to damage the target if it does ")
            .append("or doesn't have any weapons.\n")
            .append("With enough damage, target's health reduces to zero and the final ")
            .append("attacker wins the game.\n")
            .append("If no of turns exhaust before anyone winning the game ")
            .append("gets over.");
        JFrame helpFrame = new JFrame("Help");
        helpFrame.setBounds(250, 200, 800, 500);
        JTextArea helpText = new JTextArea();
        helpText.setEditable(false);
        helpText.setText(helpInfo.toString());
        JScrollPane helpScroller = new JScrollPane(helpText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        helpFrame.add(helpScroller);
        helpFrame.setVisible(true);
      }
    });
    
    currentGame.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String info = "The game will take you to "
            + "the world of Doctor Lucky's Mansion";
        int response = JOptionPane.showConfirmDialog(null, info, 
            "Current Game", JOptionPane.OK_OPTION);
        if (response == 0) {
          listener.setModel(null);
        }
      }
    });
    
    customGame.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String customGameInput = JOptionPane.showInputDialog("Please enter "
            + "the file path of your new world specification file");
        try {
          if (customGameInput == null) {
            throw new IllegalArgumentException("input cannot be null");
          } else {
            listener.setModel(customGameInput);
          }
        } catch (IllegalArgumentException iae) {
          showErrorPopup(customGameInput);
        }
      }
    });
  }
  
  

  @Override
  public void showErrorPopup(String errorMessage) {
    if (errorMessage == null) {
      JOptionPane.showMessageDialog(null, "No input given!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
    } else {
      JOptionPane.showMessageDialog(null, errorMessage, "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    
  }
  
  

  @Override
  public void quitView() {
    this.dispose();
  }
  
  

  @Override
  public void startGame() {
    if (this.listener != null) {
      this.remove(tempPanel);
      this.gamePanel = new GamePanel(listener);
      this.add(BorderLayout.CENTER, gamePanel);
      repaint();
      revalidate();
    } else {
      this.showErrorPopup("Game has not yet began!!!");
    }
  }

  /**
   * This helper method helps in creating view
   * by creating and adding its components.
   */
  private void createView() {
    
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());
    this.setBounds(200, 200, 900, 900);
   
    this.menuBar = new JMenuBar();
    this.add(BorderLayout.NORTH, this.menuBar);
   
    this.newGame = new JMenu("New Game");
    this.options = new JMenu("Options");
    this.menuBar.add(this.newGame);
    this.menuBar.add(this.options);
   
    this.currentGame = new JMenuItem("Enter the world of Doctor Lucky's Mansion");
    this.customGame = new JMenuItem("Enter the world of your choice");
    this.quitOption = new JMenuItem("Quit");
    this.helpOption = new JMenuItem("Help");
    this.newGame.add(this.currentGame);
    this.newGame.add(this.customGame);
    this.options.add(this.quitOption);
    this.options.add(this.helpOption);
  }
  

  @Override
  public void gameEnd(String string) {
    
    int i = JOptionPane.showConfirmDialog(null, string, "GAME OVER", 
        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
    if (i == JOptionPane.CLOSED_OPTION || i == JOptionPane.OK_OPTION) {
      listener.quitGame();
    }
  }
}
