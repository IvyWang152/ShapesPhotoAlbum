package model;

public class ShapeFactory {
  public static IShape create(String name, String shapeType, double x, double y, double width,
                      double height, int r, int g, int b) {
    if (shapeType == null) {
       throw new IllegalArgumentException("Shape type cannot be null");
    }
    if (r<0 || r > 255 || g < 0 || g > 255 || b <0 || b > 255) {
      throw new IllegalArgumentException("Color RGB values should keep between 0 to 255");
    }
    Color color = new Color(r,g,b);
    if (shapeType.equalsIgnoreCase(ShapeType.OVAL.value)) {
      return new Oval(name,x,y,width,height,color);
    }
    else if (shapeType.equalsIgnoreCase(ShapeType.RECTANGLE.value)) {
      return new Rectangle(name,x,y,width,height,color);
    }
    return null;

  }
}
