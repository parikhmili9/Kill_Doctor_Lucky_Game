package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * This class extends and implements JPanel
 * for the representation of welcome message
 * before starting the game.
 *
 * @author shreyas.terdalkar
 */
public class WelcomePanel extends JPanel {

  /**
   * The serial version UID.
   */
  private static final long serialVersionUID = -6274486306009575927L;
  private JLabel message1;
  private JLabel message2;
  private JLabel credits1;
  private JLabel credits2;
  private JLabel credits3;
  private GridBagConstraints constraints;

  /**
   * This method constructs the welcome message dispaly panel.
   */
  public WelcomePanel() {

    this.setBackground(Color.cyan);
    this.setLayout(new GridBagLayout());
    this.setBounds(500, 200, 400, 400);

    this.message1 = new JLabel("Welcome to", SwingConstants.CENTER);
    this.message1.setHorizontalTextPosition(JLabel.CENTER);
    this.message1.setFont(new Font("Times New Roman", Font.BOLD, 60));
    this.message1.setForeground(Color.BLUE);
    
    this.message2 = new JLabel("Kill Doctor Lucky Board Game", SwingConstants.CENTER);
    this.message2.setHorizontalTextPosition(JLabel.CENTER);
    this.message2.setFont(new Font("Times New Roman", Font.BOLD, 60));
    this.message2.setForeground(Color.BLUE);

    this.credits1 = new JLabel("Created by", SwingConstants.CENTER);
    this.credits1.setHorizontalTextPosition(JLabel.CENTER);
    this.credits1.setFont(new Font("Times New Roman", Font.ITALIC, 50));
    this.credits1.setForeground(Color.MAGENTA);
    
    this.credits2 = new JLabel("Mili Parikh", SwingConstants.CENTER);
    this.credits2.setHorizontalTextPosition(JLabel.CENTER);
    this.credits2.setFont(new Font("Times New Roman", Font.ITALIC, 50));
    this.credits2.setForeground(Color.RED);
    
    this.credits3 = new JLabel("Shreyas Terdalkar", SwingConstants.CENTER);
    this.credits3.setHorizontalTextPosition(JLabel.CENTER);
    this.credits3.setFont(new Font("Times New Roman", Font.ITALIC, 50));
    this.credits3.setForeground(Color.RED);

    this.constraints = new GridBagConstraints();
    this.constraints.gridwidth = GridBagConstraints.REMAINDER;
    
    this.add(this.message1, this.constraints);
    this.add(this.message2, this.constraints);
    this.add(this.credits1, this.constraints);
    this.add(this.credits2, this.constraints);
    this.add(this.credits3, this.constraints);
  }
}
