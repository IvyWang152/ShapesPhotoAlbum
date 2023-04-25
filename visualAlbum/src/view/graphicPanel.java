package view;

import model.IAlbumModel;
import model.IShape;
import model.ShapeType;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class represents a graphic panel to display snapshots.
 */
public class graphicPanel extends JPanel {
  private IAlbumModel model;
  private List<LocalDateTime> IDList;
  private JPanel IDPanel;
  private JLabel label;

  /**
   * Constructor.
   * @param model photo album model instance
   * @param IDPanel a panel to add ID and description
   */
  public graphicPanel(IAlbumModel model, JPanel IDPanel) {
    this.model = model;
    this.IDList = model.getSnapShotsID();

    this.IDPanel = IDPanel;
    label = new JLabel();
    label.setFont(new Font("Verdana", Font.ITALIC, 12));
    IDPanel.add(label);

    this.setBorder(BorderFactory.createLineBorder(Color.orange,3));
    this.setBackground(Color.CYAN);

  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(800,800);
  }

  @Override
  public void paintComponent(Graphics g) {
    // display single snapShot according to current index
    Graphics2D g2D = (Graphics2D) g;
    int index = model.getIndex();
    List<IShape> snapShot = model.getSnapShots().get(index);
    if (model.getDescription().get(index) != "") {
      label.setText("<html>" + IDList.get(index).toString() + "<br>"
          + model.getDescription().get(index) );
    } else {
      label.setText(IDList.get(index).toString());
    }

    for (IShape shape : snapShot) {
      if (shape.getType() == ShapeType.OVAL) {
        g2D.setPaint(shape.getColor());
        g2D.fillOval((int) shape.getPoint().getX(), (int) shape.getPoint().getY(),
            (int) shape.getWidth(), (int) shape.getHeight());
      }
      if (shape.getType() == ShapeType.RECTANGLE) {
        g2D.setPaint(shape.getColor());
        g2D.fillRect((int) shape.getPoint().getX(), (int) shape.getPoint().getY(),
            (int) shape.getWidth(), (int) shape.getHeight());
      }
    }
  }

}
