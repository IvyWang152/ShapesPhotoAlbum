package view;

import java.time.LocalDateTime;
import java.util.List;

/**
 * This class represents a SVG component drawer. It can draw rectangle and oval in html format.
 */
public class SVGComponentDrawer {
  private StringBuilder out;
  private List<LocalDateTime> IDList;

  /**
   * Constructor.
   */
  public SVGComponentDrawer() {
    out = new StringBuilder();
  }

  /**
   * Append html code for draw a rectangle.
   * @param x x coordinate
   * @param y y coordinate
   * @param width width of the rectangle
   * @param height height of the rectangle
   * @param r red value of its color
   * @param g green value of its color
   * @param b blue value of its color
   */
  public void addRect(int x, int y, int width, int height, int r, int g, int b) {
    out.append(String.format("<rect x=\"%d\" y=\"%d\" width=\"%d\" height=\"%d\" "
        + "fill=\"rgb(%d,%d,%d)\" />\n", x, y, width, height, r, g, b));

  }

  /**
   * Append html code for drawing an oval.
   * @param x x coordinate
   * @param y y coordinate
   * @param width x radius of the oval
   * @param height y radius of the oval
   * @param r red value of its color
   * @param g green value of its color
   * @param b blue value of its color
   */
  public void addOval(int x, int y, int width, int height, int r, int g, int b) {
    out.append(String.format("<ellipse cx=\"%d\" cy=\"%d\" rx=\"%d\" ry=\"%d\" "
        + "fill=\"rgb(%d,%d,%d)\" />\n", x, y, width, height, r, g, b));
  }

  /**
   * Get the string format of shapes' html code.
   * @return a string format of shapes' html code
   */
  public String getSVGComponent() {
    return out.toString();
  }

}
