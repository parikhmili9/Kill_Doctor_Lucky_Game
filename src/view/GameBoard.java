package view;

import controller.CommandInterface;
import controller.ControllerInterface;
import controller.Move;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * This class implements the graphical representation of the game world.
 *
 * @author shreyas.terdalkar
 *
 */
public class GameBoard extends JPanel {

  /**
   * The serial version UID.
   */
  private static final long serialVersionUID = 1L;
  private CommandInterface command;
  private BufferedImage worldImage;
  private Graphics2D graphics;
  private ControllerInterface listener;
  private Map<Integer, Map<String, List<String>>> worldDetailsMap;
  
  /**
   * This method constructs the game board by creating its
   * components.
   *
   * @param listener the controller of the game
   */
  public GameBoard(ControllerInterface listener) {
    super();
    
    if (listener == null) {
      throw new IllegalArgumentException("listener cannot be null");
    }
    this.listener = listener;
    createGameBoard();

    this.setRoomListener(listener);
  }
  
  private void setRoomListener(ControllerInterface listener) {
    
    this.addMouseListener(new MouseAdapter() {

      @Override
      public void mouseClicked(MouseEvent m) {
        int clickX = m.getX();
        int clickY = m.getY();
        
        for (int i = 0; i < listener.getConWorldDimensions().get(2); i++) {
          if (worldDetailsMap.get(i) != null) {
            if (i != listener.getTurnOwner().getLocation()) {
              String stringX1 = worldDetailsMap.get(i).get("RoomCoordinates").get(0);
              String stringY1 = worldDetailsMap.get(i).get("RoomCoordinates").get(1);
              String stringX2 = worldDetailsMap.get(i).get("RoomCoordinates").get(2);
              String stringY2 = worldDetailsMap.get(i).get("RoomCoordinates").get(3);
              int x1;
              int y1;
              int x2;
              int y2;
              try {
                x1 = Integer.parseInt(stringX1) * 30;
                y1 = Integer.parseInt(stringY1) * 30;
                x2 = Integer.parseInt(stringX2) * 30;
                y2 = Integer.parseInt(stringY2) * 30;
              } catch (NumberFormatException nfe) {
                throw new IllegalArgumentException("Numbers not in integer format");
              }
              
              if (x1 < clickY && clickY < x2 
                  && y1 < clickX && clickX < y2) {
                String roomName = worldDetailsMap.get(i).get("RoomName").get(0);
                String info = "Are you sure, you want to move to the " + roomName + "?";
                int response = JOptionPane.showConfirmDialog(null, info, "Move to this room", 
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (response == JOptionPane.CLOSED_OPTION || response == JOptionPane.OK_OPTION) {
                  command = new Move(roomName);
                  listener.setCommand(command);
                }
              }
            }
            
          }
        }
        
      }
    });
    
  }

  private void setPlayerListener(int playerDimensionX1, int playerDimensionY1, 
      int playerDimensionX2, int playerDimensionY2, String eachPlayer) {
    
    this.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent m) {
        int clickX = m.getX();
        int clickY = m.getY();
        if (playerDimensionX1 < clickY 
            && clickY < playerDimensionX2 
            && playerDimensionY1 < clickX 
            && clickX < playerDimensionY2) {
          String playerInfo = listener.getPlayerInfo(eachPlayer);
          JOptionPane.showMessageDialog(null, playerInfo, "Player Information", 
              JOptionPane.INFORMATION_MESSAGE);
        }
        
      }
    });
    
  }



  @Override
  public void paintComponent(Graphics g) {
    g.drawImage(this.worldImage, 0, 0, null);
  }

  /**
   * This method is used to refresh the game board.
   */
  public void refresh() {
    createGameBoard();
    //this.removeAll();
    //this.updateUI();
    this.revalidate();
    this.repaint();
    
  }
  
  private void createGameBoard() {
    this.worldDetailsMap = listener.getConRoomDetails();
    this.worldImage = listener.getConWorldImage();
    this.graphics = (Graphics2D) this.worldImage.getGraphics();
    
    for (int i = 0; i < this.listener.getConWorldDimensions().get(2); i++) {
      if (worldDetailsMap.get(i) != null) {
        String stringX1 = worldDetailsMap.get(i).get("RoomCoordinates").get(0);
        String stringY1 = worldDetailsMap.get(i).get("RoomCoordinates").get(1);
        int x1;
        int y1;
        try {
          x1 = Integer.parseInt(stringX1);
          y1 = Integer.parseInt(stringY1);
        } catch (NumberFormatException nfe) {
          throw new IllegalArgumentException("Numbers not in integer format");
        }
        
        if (worldDetailsMap.get(i).get("RoomTarget") != null) {
          
          File inputFile = new File("././res/Images/doctor.png");
          BufferedImage inputThisImage;
          try {
            inputThisImage = ImageIO.read(inputFile);
          } catch (IOException e) {
            throw new IllegalArgumentException("File not found");
          }
          BufferedImage outputThisImage = new BufferedImage(28,
              28, inputThisImage.getType());
          Graphics2D g2d = outputThisImage.createGraphics();
          g2d.drawImage(inputThisImage, 0, 0, 28, 28, null);
          g2d.dispose();
          this.graphics.drawImage(outputThisImage, (y1 * 30) + 150, (x1 * 30) + 50, null);
        }
        
        if (worldDetailsMap.get(i).get("RoomPlayers") != null) {
          int count = 0;
          for (int j = 0; j < worldDetailsMap.get(i).get("RoomPlayers").size(); j++) {
            
            String eachPlayer;
            eachPlayer = worldDetailsMap.get(i).get("RoomPlayers").get(j);
            String filepath = "././res/Images/player" + j + ".png";
            File inputFile = new File(filepath);
            //File inputFile = new File("././res/player0.png");
            BufferedImage inputImage;
            try {
              inputImage = ImageIO.read(inputFile);
            } catch (IOException e) {
              throw new IllegalArgumentException("File not found");
            }
            BufferedImage outputImage = new BufferedImage(28,
                28, inputImage.getType());
            Graphics2D g2d = outputImage.createGraphics();
            g2d.drawImage(inputImage, 0, 0, 28, 28, null);
            g2d.dispose();
            this.graphics.drawImage(outputImage, (y1 * 30) + 5 + ((count % 5) * 30), 
                (x1 * 30) + 20 + ((count / 5) * 30), null);
            int playerDimensionX1 = (x1 * 30) + 20 + ((count / 5) * 30);
            int playerDimensionX2 = playerDimensionX1 + 28;
            int playerDimensionY1 = (y1 * 30) + 5 + ((count % 5) * 30);
            int playerDimensionY2 = playerDimensionY1 + 28;
            this.setPlayerListener(playerDimensionX1, playerDimensionY1, 
                playerDimensionX2, playerDimensionY2, eachPlayer);
            count++;
          }
        }
      }
    }
  }
}
