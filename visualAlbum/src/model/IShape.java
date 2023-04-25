package model;


/**
 * This interface declares all operations for shape classes.
 */
public interface IShape {

  /**
   * Get position of the shape.
   * @return a Point2D object
   */
  Point2D getPoint();

  /**
   * Get width of the shape.
   * @return width of the shape
   */
  double getWidth();

  /**
   * Get height of the shape.
   * @return height of the shape
   */
  double getHeight();

  /**
   * Get color of the shape.
   * @return color of the shape
   */
  Color getColor();

  /**
   * Set color of the shape.
   * @param color enum model.Color
   */
  void setColor(Color color);

  /**
   * Set width of the shape.
   * @param width width
   * @throws IllegalArgumentException when width is less than or equal to 0
   */
  void setWidth(double width) throws IllegalArgumentException;

  /**
   * Set height of the shape.
   * @param height height
   * @throws IllegalArgumentException when height is less than or equal to 0
   */
  void setHeight(double height) throws IllegalArgumentException;

  /**
   * Get name of the shape.
   * @return name of the shape
   */
  String getName();

  /**
   * Get type of the shape.
   * @return type of the shape
   */
  ShapeType getType();

  /**
   * Move the shape to another point.
   * @param x x coordinate of the target position
   * @param y y coordinate of the target position
   */
  void move(double x, double y);

  /**
   * Copy the current shape object.
   * @return a copy of current object
   */
  IShape copy();


}
