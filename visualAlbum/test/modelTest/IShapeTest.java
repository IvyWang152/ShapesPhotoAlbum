package modelTest;

import static org.junit.Assert.assertEquals;

import model.Color;
import model.IShape;
import model.Oval;
import model.Point2D;
import model.Rectangle;
import model.ShapeType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * A JUnit test class for testing Shape classes.
 */
public class IShapeTest {
  private IShape oval1;
  private IShape oval2;
  private IShape rectangle1;
  private IShape rectangle2;
  private Color color1;
  private Color color2;

  /**
   * Create various shape objects for testing.
   */
  @Before
  public void setUp() {
    color1 = new Color(0,255,255);
    color2 = new Color(0,255,0);
    oval1 = new Oval("O", 500, 100, 60, 30);
    oval2 = new Oval("O2", 45, -100.5, 12.6, 1, color1);
    rectangle1 = new Rectangle("R", 200, 200, 50, 100);
    rectangle2 = new Rectangle("R2", 0, 10.9, 70.2, 35.8, color2);
  }

  /**
   * test invalid constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBadConstructor() {
    new Oval(null, 34, 55, 30, 12);
    new Rectangle("Rec", -50, 100, 0, 30);
    new Rectangle("r", 104, -39, 500.0, 0);
    new Oval("oval", 44.8, 0, 0, 10);
    new Oval("H", 200, 150, -55, 0);
    new Oval("four", 0, 0, 0, 0);
    new Rectangle("rec2", 300, 200, -40, -100);

  }

  /**
   * test getPoint method.
   */
  @Test
  public void testGetPoint() {
    Point2D expected = new Point2D(500, 100);
    assertEquals(expected, oval1.getPoint());
    Point2D expected1 = new Point2D(45, -100.5);
    assertEquals(expected1, oval2.getPoint());
    Point2D expected2 = new Point2D(200, 200);
    assertEquals(expected2, rectangle1.getPoint());
    Point2D expected3 = new Point2D(0, 10.9);
    assertEquals(expected3, rectangle2.getPoint());
  }

  /**
   * test getWidth method.
   */
  @Test
  public void testGetWidth() {
    assertEquals(60, oval1.getWidth(), 0.001);
    assertEquals(12.6, oval2.getWidth(), 0.001);
    assertEquals(50, rectangle1.getWidth(), 0.001);
    assertEquals(70.2, rectangle2.getWidth(), 0.001);
  }

  /**
   * test getHeight method.
   */
  @Test
  public void testGetHeight() {
    assertEquals(30.0, oval1.getHeight(), 0.001);
    assertEquals(1, oval2.getHeight(), 0.001);
    assertEquals(100, rectangle1.getHeight(), 0.001);
    assertEquals(35.8, rectangle2.getHeight(), 0.001);
  }

  /**
   * test getColor method.
   */
  @Test
  public void testGetColor() {
    assertEquals("(0,0,0)", oval1.getColor().toString());
    assertEquals("(0,255,255)", oval2.getColor().toString());
    assertEquals("(0,0,0)", rectangle1.getColor().toString());
    assertEquals("(0,255,0)", rectangle2.getColor().toString());
  }

  /**
   * test setColor method.
   */
  @Test
  public void testSetColor() {
    oval1.setColor(new Color(255,0,0));
    assertEquals("(255,0,0)", oval1.getColor().toString());
    oval1.setColor(new Color(255,0,255));
    assertEquals("(255,0,255)", oval1.getColor().toString());
    rectangle2.setColor(new Color(255,255,0));
    assertEquals("(255,255,0)", rectangle2.getColor().toString());
  }

  /**
   * test setWidth method.
   */
  @Test
  public void testSetWidth() {
    oval1.setWidth(40);
    assertEquals(40, oval1.getWidth(), 0.001);
    oval2.setWidth(8);
    assertEquals(8, oval2.getWidth(), 0.001);
    rectangle1.setWidth(328.94);
    assertEquals(328.94, rectangle1.getWidth(), 0.01);
    rectangle2.setWidth(132.0);
    assertEquals(132.0, rectangle2.getWidth(), 0.001);
  }

  /**
   * test setHeight method.
   */
  @Test
  public void testSetHeight() {
    oval1.setHeight(123);
    assertEquals(123, oval1.getHeight(), 0.001);
    rectangle1.setHeight(4.5);
    assertEquals(4.5, rectangle1.getHeight(), 0.001);
  }

  /**
   * test setHeight and setWidth methods with invalid parameters.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSetInvalidHeightOrWidth() {
    oval1.setHeight(0);
    oval2.setWidth(-40);
    rectangle1.setWidth(0);
    rectangle2.setHeight(-0.5);
  }

  /**
   * test getName method.
   */
  @Test
  public void testGetName() {
    assertEquals("O", oval1.getName());
    assertEquals("O2", oval2.getName());
    assertEquals("R", rectangle1.getName());
    assertEquals("R2", rectangle2.getName());
  }

  /**
   * test getType method.
   */
  @Test
  public void testGetType() {
    Assert.assertEquals(ShapeType.OVAL, oval1.getType());
    assertEquals(ShapeType.OVAL, oval2.getType());
    assertEquals(ShapeType.RECTANGLE, rectangle1.getType());
    assertEquals(ShapeType.RECTANGLE, rectangle2.getType());
  }

  /**
   * test move method.
   */
  @Test
  public void testMove() {
    oval1.move(0, 0);
    assertEquals("(0.0,0.0)", oval1.getPoint().toString());
    rectangle1.move(-150, -100);
    assertEquals("(-150.0,-100.0)", rectangle1.getPoint().toString());
  }

  /**
   * test toString method.
   */
  @Test
  public void testToString() {
    oval1.move(0, 0);
    String expected = "Name: O" + "\n"
        + "Type: oval" + "\n"
        + "Center: " + "(0.0,0.0), "
        + "X radius: 60.0" + ", " + "Y radius: 30.0,\n"
        + "Color: " + oval1.getColor() + "\n";
    assertEquals(expected, oval1.toString());

    String expected1 = "Name: R2" + "\n"
        + "Type: rectangle" + "\n"
        + "Min corner: " + "(0.0,10.9), "
        + "Width: 70.2" + ", " + "Height: 35.8,\n"
        + "Color: " + rectangle2.getColor() + "\n";
    assertEquals(expected1, rectangle2.toString());

  }
}