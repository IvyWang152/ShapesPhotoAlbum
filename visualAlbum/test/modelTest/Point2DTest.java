package modelTest;


import static org.junit.Assert.assertEquals;

import model.Point2D;
import org.junit.Before;
import org.junit.Test;

/**
 * A JUnit test class for Point2D.
 */
public class Point2DTest {
  private Point2D point1;
  private Point2D point2;
  private Point2D point3;

  /**
   * Create different points for testing.
   */
  @Before
  public void setUp() {
    point1 = new Point2D(0.0, 0.0);
    point2 = new Point2D(150, -100);
    point3 = new Point2D(54, 80.5);
  }

  /**
   * test getX method.
   */
  @Test
  public void testGetX() {
    assertEquals(0.0, point1.getX(), 0.001);
    assertEquals(150.0, point2.getX(), 0.001);
    assertEquals(54.0, point3.getX(), 0.001);
  }

  /**
   * test getY method.
   */
  @Test
  public void testGetY() {
    assertEquals(0.0, point1.getY(), 0.001);
    assertEquals(-100.0, point2.getY(), 0.001);
    assertEquals(80.5, point3.getY(), 0.001);
  }

  /**
   * test toString method.
   */
  @Test
  public void testTestToString() {
    assertEquals("(0.0,0.0)", point1.toString());
    assertEquals("(150.0,-100.0)", point2.toString());
    assertEquals("(54.0,80.5)", point3.toString());
  }

  /**
   * test equals method.
   */
  @Test
  public void testTestEquals() {
    assertEquals(new Point2D(0, 0), point1);
    assertEquals(new Point2D(150, -100), point2);
    assertEquals(new Point2D(54.0, 80.5), point3);
  }
}