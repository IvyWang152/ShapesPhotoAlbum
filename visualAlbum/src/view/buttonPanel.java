package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class represents a button panel to display buttons.
 */
public class buttonPanel extends JPanel {
  private JFrame frame;
  private JButton previous;
  private JButton next;
  private JButton select;
  private JButton quit;

  /**
   * Constructor.
   */
  public buttonPanel() {

    previous = new JButton("<< Prev <<");
    next = new JButton(">> Next >>");
    select = new JButton("^^ Select ^^");
    quit = new JButton("xx Quit xx ");
    previous.setActionCommand("previous");
    next.setActionCommand("next");
    select.setActionCommand("select");
    quit.setActionCommand("quit");

    this.setBackground(Color.orange);
    this.setLayout(new FlowLayout(FlowLayout.CENTER));

    this.add(previous);
    this.add(select);
    this.add(next);
    this.add(quit);
    this.setVisible(true);


  }

  /**
   * Add action listener to buttons in the button panel.
   * @param listener action listener
   */
  public void setActionListener(ActionListener listener) {
    previous.addActionListener(listener);
    next.addActionListener(listener);
    select.addActionListener(listener);
    quit.addActionListener(listener);

  }

}
