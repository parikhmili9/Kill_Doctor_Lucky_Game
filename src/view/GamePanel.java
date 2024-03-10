package view;

import controller.AddGamePlayer;
import controller.CommandInterface;
import controller.ControllerInterface;
import controller.Kill;
import controller.Look;
import controller.MovePet;
import controller.Pick;
import controller.Poke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Objects;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * This class creates panel for the actual game
 * by adding its graphical representation and all
 * other features.
 *
 * @author shreyas.terdalkar
 */
public class GamePanel extends JPanel {

  /**
   * The serial version UID.
   */
  private static final long serialVersionUID = -2698823330524672214L;
  private ControllerInterface listener;
  private CommandInterface cmd;
  private JPanel infoPanel;
  private GameBoard gameBoard;
  private JPanel statusPanel;
  private JPanel turnInfoPanel;
  private JPanel buttonPanel;
  private JPanel targetInfoPanel;
  private JButton addPlayerButton;
  private JButton addComputerButton;
  private JProgressBar targetHealthBar;
  private JTextArea statusText;
  private JTextArea targetText;
  private JTextArea turnInfoText;
  private JScrollPane scroller;

  /**
   * This method constructs the game panel by adding
   * features to it.
   *
   * @param listener the controller of the game
   */
  public GamePanel(ControllerInterface listener) {
    super();
    if (listener == null) {
      throw new IllegalArgumentException("listener cannot be null");
    }
    this.listener = listener;

    createPanel();
  }


  /**
   * This method sets action listeners for the game panel.
   * 
   */
  public void setListener() {
    
    //Listener for Add Human Player
    addPlayerButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String playerName = JOptionPane.showInputDialog("Player's name: ");
        String playerSpace = JOptionPane.showInputDialog("Player's space: ");
        try {
          if (playerName == null) {
            throw new IllegalArgumentException("Player's name cannot be null");
          }
          if (playerSpace == null) {
            throw new IllegalArgumentException("Player's space cannot be null");
          } else {
            cmd = new AddGamePlayer(playerName, playerSpace, true);
            listener.setCommand(cmd);
          }
        } catch (IllegalArgumentException iae) {
          showErrorPopup(iae.getMessage());
        }
      }
    });

    //Listener for Add computer player
    addComputerButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String playerName = JOptionPane.showInputDialog("Player's name: ");
        String playerSpace = JOptionPane.showInputDialog("Player's space: ");
        try {
          if (playerName == null) {
            throw new IllegalArgumentException("Player's name cannot be null");
          }
          if (playerSpace == null) {
            throw new IllegalArgumentException("Player's space cannot be null");
          } else {
            cmd = new AddGamePlayer(playerName, playerSpace, false);
            listener.setCommand(cmd);
          }
        } catch (IllegalArgumentException iae) {
          showErrorPopup(iae.getMessage());
        }
      }
    });
    
    //if (listener.getTurnOwner() != null) {
      this.addKeyListener(new KeyAdapter() {
        @Override
        public void keyReleased(KeyEvent e) {
          super.keyPressed(e);
          int count = 0;
          int keyCode = e.getKeyCode() + count;

          //Key L - for look around command
          if (keyCode == KeyEvent.VK_L) {
            System.out.println("L press");
            count++;
            try {
              Look command = new Look();
              listener.setCommand(command);
              String lookInfo = command.getLookInfo();
              JOptionPane.showMessageDialog(null, lookInfo, "Looking Around",
                  JOptionPane.INFORMATION_MESSAGE);
            } catch (IllegalArgumentException iae) {
              showErrorPopup(iae.getMessage());
            }
          }

          //Key P - for pick an item command
          if (keyCode == KeyEvent.VK_P) {
            count++;
            String currRoomName = listener.getTurnOwnerRoomName();
            List<String> currRoomWeapons = listener.getListOfRoomWeapons(currRoomName);
            StringBuilder showDialog = new StringBuilder();
            showDialog.append("These are the weapons in the room available to pick up :\n");
            if (currRoomWeapons.size() == 0) {
              JOptionPane.showMessageDialog(null,
                  "No items available here!", "Picking weapons",
                  JOptionPane.INFORMATION_MESSAGE);
              showDialog.append("No items available here!");
            } else {
              for (int i = 0; i < currRoomWeapons.size(); i++) {
                showDialog.append(currRoomWeapons.get(i)).append("\n");
              }
              showDialog.append("\n");
              showDialog.append("In the next input box, enter the weapon name that you want ")
                  .append("to pick up.");
              JOptionPane.showMessageDialog(null, showDialog, "Picking weapons",
                  JOptionPane.INFORMATION_MESSAGE);
              String weaponName = JOptionPane.showInputDialog("Enter the weapon name that you "
                  + "want to pick: ");
              try {
                cmd = new Pick(weaponName);
                listener.setCommand(cmd);
              } catch (IllegalArgumentException iae) {
                showErrorPopup(iae.getMessage());
              }
            }
          }

          //Key A - for attack command
          if (keyCode == KeyEvent.VK_A) {
            count++;
            String currPlayerName = listener.getConTurnOwnerName();
            List<String> listWeaponsOfCurrPlayer = listener
                .getListOfConPlayerWeapons(currPlayerName);
            StringBuilder showDialog = new StringBuilder();
            showDialog.append("These are the weapons available to launch the attack :\n");
            showDialog.append("Poke").append("\n");
            if (listWeaponsOfCurrPlayer == null) {
              showDialog.append("\n");
              showDialog.append("No items available! You can poke the target's eye.");
            } else if (listWeaponsOfCurrPlayer.size() == 0) {
              showDialog.append("\n");
              showDialog.append("\n");
              showDialog.append("No items available! You can poke the target's eye.");
            } else {
              for (int i = 0; i < listWeaponsOfCurrPlayer.size(); i++) {
                showDialog.append(listWeaponsOfCurrPlayer.get(i)).append("\n");
              }
            }
            showDialog.append("\n");
            showDialog.append("In the next input box, enter the weapon name that you want "
                + "to use to attack.");
            JOptionPane.showMessageDialog(null, showDialog, "Attacking Target",
                JOptionPane.INFORMATION_MESSAGE);
            String weaponName = JOptionPane.showInputDialog("Enter the weapon name that you want "
                + "to use to attack: ");
            try {
              if (Objects.equals(weaponName, "Poke")) {
                cmd = new Poke();
              } else if (Objects.equals(weaponName, "poke")) {
                cmd = new Poke();
              } else {
                cmd = new Kill(weaponName);
              }
              listener.setCommand(cmd);
            } catch (IllegalArgumentException iae) {
              showErrorPopup(iae.getMessage());
            }
          }

          //Key M - to move the pet
          if (keyCode == KeyEvent.VK_M) {
            count++;
            List<String> roomsList = listener.getListOfAllRooms();
            StringBuilder showDialog = new StringBuilder();
            showDialog.append("These are the rooms you can choose from :").append("\n");
            for (int i = 0; i < roomsList.size(); i++) {
              showDialog.append(roomsList.get(i)).append("\n");
            }
            showDialog.append("\n");
            showDialog.append("In the next input box, enter the room name where you want "
                + "to move the pet.");
            JOptionPane.showMessageDialog(null, showDialog, "Move Pet",
                JOptionPane.INFORMATION_MESSAGE);
            String roomName = JOptionPane.showInputDialog("Enter the room name where you want "
                + "to move the pet: ");
            try {
              cmd = new MovePet(roomName);
              listener.setCommand(cmd);
            } catch (IllegalArgumentException iae) {
              showErrorPopup(iae.getMessage());
            }
          }
        }
      });
      this.setFocusable(true);
    //}
  }

  /**
   * This method handles display of all the errors in the game panel.
   *
   * @param errorMessage the input error message string
   */
  public void showErrorPopup(String errorMessage) {
    if (errorMessage == null) {
      JOptionPane.showMessageDialog(null, "No input given!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
    } else {
      JOptionPane.showMessageDialog(null, errorMessage, "ERROR", JOptionPane.ERROR_MESSAGE);
    }
  }

  /**
   * This method refreshes the game panel by deleting
   * and adding all its components.
   */
  public void refresh() {
    setInfo();
    //setListener();
    gameBoard.refresh();
    this.requestFocus();
    //this.updateUI();
    //repaint();
    //revalidate();
  }
  
  //original
//  /**
//   * This method refreshes the game panel by deleting
//   * and adding all its components.
//   */
//  public void refresh() {
//    System.out.println("game panel refresh");
//    this.removeAll();
//    createPanel();
//    validate();
//    repaint();
//  }

  /**
   * This helper method creates the components
   * of this game panel.
   */
  private void createPanel() {

    System.out.println("create panel");
    this.setLayout(new BorderLayout());

    infoPanel = new JPanel();
    infoPanel.setPreferredSize(new Dimension(100, 100));
    this.add(BorderLayout.NORTH, infoPanel);

    gameBoard = new GameBoard(listener);
    gameBoard.setBackground(Color.WHITE);
    gameBoard.setPreferredSize(new Dimension(930, 1116));
    scroller = new JScrollPane(gameBoard);
    scroller.setSize(900, 600);
    this.add(scroller, BorderLayout.CENTER);

    statusPanel = new JPanel();
    statusPanel.setPreferredSize(new Dimension(100, 100));
    statusPanel.setBackground(Color.WHITE);
    this.add(BorderLayout.SOUTH, statusPanel);

    infoPanel.setLayout(new BorderLayout());

    turnInfoPanel = new JPanel();
    turnInfoPanel.setPreferredSize(new Dimension(300, 100));
    turnInfoPanel.setBackground(Color.WHITE);
    infoPanel.add(BorderLayout.EAST, turnInfoPanel);

    buttonPanel = new JPanel();
    buttonPanel.setBackground(Color.WHITE);
    infoPanel.add(BorderLayout.CENTER, buttonPanel);

    targetInfoPanel = new JPanel();
    targetInfoPanel.setPreferredSize(new Dimension(300, 100));
    targetInfoPanel.setBackground(Color.WHITE);
    infoPanel.add(BorderLayout.WEST, targetInfoPanel);

    buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    addPlayerButton = new JButton("Add Human Player");
    buttonPanel.add(addPlayerButton);
    addComputerButton = new JButton("Add Computer Player");
    buttonPanel.add(addComputerButton);

    statusText = new JTextArea();
    statusText.setEditable(false);
    statusPanel.add(statusText);
    

    this.targetText = new JTextArea();
    this.targetText.setEditable(false);
    this.targetHealthBar = new JProgressBar();
    this.targetHealthBar.setStringPainted(true);
    this.targetInfoPanel.add(this.targetHealthBar);
    this.targetInfoPanel.add(this.targetText);
    

    this.turnInfoText = new JTextArea();
    this.turnInfoText.setEditable(false);
    this.turnInfoPanel.add(this.turnInfoText);
    
    setInfo();

    setListener();
    this.setFocusable(true);
  }


  private void setInfo() {
    
    StringBuilder statusViewMessage = new StringBuilder();
    statusViewMessage.append(listener.getStatus());
    statusText.setText(statusViewMessage.toString());

    StringBuilder targetMessage = new StringBuilder();
    targetMessage.append(listener.getConTargetStatus());
    this.targetText.setText(targetMessage.toString());
    this.targetHealthBar.setValue(this.listener.getConTargetHealth() * 2);
    
    StringBuilder turnInfoMessage = new StringBuilder();
    turnInfoMessage.append("Turns Left : ").append(listener.getConTurnCount())
      .append("\n").append(listener.getConTurnOwnerName()).append("'s Turn : ").append("\n");
    this.turnInfoText.setText(turnInfoMessage.toString());
    
  }
}

