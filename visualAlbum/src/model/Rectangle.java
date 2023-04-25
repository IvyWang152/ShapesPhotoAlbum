package model;


/**
 * This class represents a rectangle.
 */
public class Rectangle extends AbstractShape {
  private double width;
  private double height;
  private Color color;
  private String name;

  /**
   * Constructor of the rectangle.
   * @param name name of the rectangle
   * @param x x coordinate
   * @param y y coordinate
   * @param width width of the rectangle
   * @param height height of the rectangle
   */
  public Rectangle(String name, double x, double y, double width, double height) {
    super(name, x, y,width,height);
  }

  /**
   * Another constructor.
   * @param name name of the rectangle
   * @param x x coordinate
   * @param y y coordinate
   * @param width width of the rectangle
   * @param height height of the rectangle
   * @param color color of the rectangle
   */
  public Rectangle(String name, double x, double y, double width, double height, Color color) {
    super(name, x, y,width,height,color);
  }

  @Override
  public ShapeType getType() {
    return ShapeType.RECTANGLE;
  }

  @Override
  public IShape copy() {
    return new Rectangle(super.getName(),super.getPoint().getX(),super.getPoint().getY(),
        super.getWidth(),super.getHeight(),super.getColor());
  }

  @Override
  public String toString() {
    StringBuilder description = new StringBuilder();
    description.append("Name: " + super.getName() + "\n");
    description.append("Type: " + getType().value + "\n");
    description.append("Min corner: " + super.getPoint().toString() + ", ");
    description.append("Width: " + super.getWidth() + ", " + "Height: "
        + super.getHeight() + ",\n");
    description.append("Color: " + super.getColor().toString() + "\n");

    return String.valueOf(description);
  }

}
