package view;

import model.IAlbumModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;



/**
 * This class implements a GUI view of the shape photo album.
 */
public class GUIView extends JFrame implements IView {
  private String title;
  private IAlbumModel model;
  private JPanel IDPanel;
  private JLabel label;
  private graphicPanel graphicPanel;
  private buttonPanel buttons = new buttonPanel();
  private int xMax = 1000;
  private int yMax = 1000;

  /**
   * Constructor.
   * @param title title of the view window
   * @param xMax width of the window
   * @param yMax height of the window
   */
  public GUIView(String title, int xMax, int yMax) {
    //super(title);
    this.title = title;
    if (xMax > 0 && yMax > 0) {
      this.xMax = xMax;
      this.yMax = yMax;
    }

    //createGUI();
  }

  /**
   * Create and show GUI view.
   * @param model photo album model instance
   */
  public void setUpGUI(IAlbumModel model) {
    this.setTitle(title);
    this.model = model;
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setPreferredSize(new Dimension(this.xMax,this.yMax));

    IDPanel = new JPanel(); //create id panel
    IDPanel.setBackground(Color.orange); //set background color of id panel

    graphicPanel = new graphicPanel(model,IDPanel); //create graphic panel to display snapshot
    JScrollPane scrollPane = new JScrollPane(graphicPanel);

    scrollPane.setBackground(Color.cyan);
    //scrollPane.setOpaque(false);
    //graphicPanel.setOpaque(false);
    //create button panel

    this.getContentPane().add(IDPanel,BorderLayout.PAGE_START);
    this.getContentPane().add(buttons,BorderLayout.PAGE_END);
    this.getContentPane().add(scrollPane,BorderLayout.CENTER);

    pack();
    this.setLocationRelativeTo(null); //appear in the middle
    this.setVisible(true);
  }

  @Override
  public void registerActionListener(ActionListener listener) {
    buttons.setActionListener(listener);
  }

  @Override
  public void display(IAlbumModel model) {
    setUpGUI(model);
  }

  @Override
  public void updateView() {
    graphicPanel.repaint();
  }
}


