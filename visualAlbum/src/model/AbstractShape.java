package model;

/**
 * This class represents an abstract shape with operations declared in model.IShape interface.
 */
public abstract class AbstractShape implements IShape {
  private Point2D point;
  private Color color;
  private String name;
  private double width;
  private double height;

  /**
   * Constructor of model.AbstractShape class.
   * @param name name of the shape
   * @param x x coordinate of the shape
   * @param y y coordinate of the shape
   * @param width width of the shape
   * @param height height of the shape
   */
  public AbstractShape(String name, double x, double y, double width, double height) {
    if (!checkShape(name, width,height)) {
      throw new IllegalArgumentException("Invalid name, width or height");
    }
    this.name = name;
    this.point = new Point2D(x, y);
    this.width = width;
    this.height = height;
    this.color = new Color(0,0,0); //default color
  }

  /**
   * Another constructor of model.AbstractShape class.
   * @param name name of the shape
   * @param x x coordinate of the shape
   * @param y y coordinate of the shape
   * @param width width of the shape
   * @param height height of the shape
   */
  public AbstractShape(String name, double x, double y, double width, double height,
                       Color color) {
    if (!checkShape(name, width,height)) {
      throw new IllegalArgumentException("Invalid name, width or height");
    }
    this.name = name;
    this.point = new Point2D(x, y);
    this.width = width;
    this.height = height;
    this.color = color;
  }


  /**
   * Helper function to check whether the parameters taken into the constructor are valid or not.
   * @param name name of the shape
   * @param width width of the shape
   * @param height height of the shape
   * @return true if they are valid, otherwise false
   */
  private boolean checkShape(String name, double width, double height) {
    if (name == null || name == "") {
      return false;
    }
    if (width <= 0 || height <= 0) {
      return false;
    }
    return true;
  }

  @Override
  public Point2D getPoint() {
    return point;
  }

  @Override
  public double getWidth() {
    return this.width;
  }

  @Override
  public double getHeight() {
    return this.height;
  }


  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public void setColor(Color color) {
    this.color = color;
  }

  @Override
  public void setWidth(double width) {
    if (width <= 0) {
      throw new IllegalArgumentException("Width should be greater than 0");
    }
    this.width = width;
  }

  @Override
  public void setHeight(double height) {
    if (height <= 0) {
      throw new IllegalArgumentException("Height should be greater than 0");
    }
    this.height = height;
  }

  public String getName() {
    return this.name;
  }

  @Override
  public abstract ShapeType getType();

  @Override
  public void move(double x, double y) {
    this.point = new Point2D(x,y);
  }

  @Override
  public abstract IShape copy();

}
