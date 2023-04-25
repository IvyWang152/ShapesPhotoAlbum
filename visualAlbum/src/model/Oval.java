package model;


/**
 * This class represents an model.Oval.
 * It has operations defined in its superclass model.AbstractShape.
 */
public class Oval extends AbstractShape {
  private double xRadius;
  private double yRadius;
  private Color color;
  private String name;

  /**
   * Constructor of model.Oval.
   * @param name name of the oval
   * @param x x coordinate of its 2D position
   * @param y y coordinate of its 2d position
   * @param xRadius x radius of the oval
   * @param yRadius y radius of the oval
   */
  public Oval(String name, double x, double y, double xRadius, double yRadius) {
    super(name, x, y, xRadius, yRadius);
  }

  /**
   * Another constructor of the oval.
   * @param name name of the oval
   * @param x x coordinate of its 2D position
   * @param y y coordinate of its 2d position
   * @param xRadius x radius of the oval
   * @param yRadius y radius of the oval
   * @param color color of the oval
   */
  public Oval(String name, double x, double y, double xRadius, double yRadius, Color color) {
    super(name, x, y, xRadius, yRadius,color);
  }

  @Override
  public ShapeType getType() {
    return ShapeType.OVAL;
  }

  @Override
  public IShape copy() {
    return new Oval(super.getName(),super.getPoint().getX(),super.getPoint().getY(),
        super.getWidth(),super.getHeight(),super.getColor());
  }

  @Override
  public String toString() {
    StringBuilder description = new StringBuilder();
    description.append("Name: " + super.getName() + "\n");
    description.append("Type: " + getType().value + "\n");
    description.append("Center: " + getPoint().toString() + ", ");
    description.append("X radius: " + super.getWidth() + ", " + "Y radius: "
        + super.getHeight()  + ",\n");
    description.append("Color: " + super.getColor().toString() + "\n");

    return String.valueOf(description);
  }

}
