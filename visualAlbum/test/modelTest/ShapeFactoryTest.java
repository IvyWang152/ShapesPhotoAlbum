package modelTest;


import static org.junit.Assert.assertEquals;

import model.Color;
import model.IShape;
import model.Oval;
import model.Rectangle;
import model.ShapeFactory;
import org.junit.Test;

/**
 * A Junit test class for ShapeFactory.
 */
public class ShapeFactoryTest {

  /**
   * Test create method.
   */
  @Test
  public void testCreate() {
    IShape oval = ShapeFactory.create("o","oval",0,0,100,200,
        0,0,0);
    IShape expected = new Oval("o",0,0,100,200,new Color(0,0,0));
    assertEquals(expected.toString(),oval.toString());
    IShape rect = ShapeFactory.create("R","rectangle",50,100,
        60,120,0,255,255);
    IShape expected1 = new Rectangle("R",50,100,60,120,
        new Color(0,255,255));
    assertEquals(expected1.toString(),rect.toString());
    IShape unknown = ShapeFactory.create("o","circle",0,0,100,100,
        0,0,0);
    assertEquals(null,unknown);
  }

  /**
   * Test create method with invalid parameters.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCreate() {
    IShape oval = ShapeFactory.create(null,"oval",0,0,100,200,
        0,0,0);
    IShape rect = ShapeFactory.create("R",null,50,100,
        60,120,0,255,255);
    IShape rect1 = ShapeFactory.create("R","rectangle",50,100,
        60,120,0,255,256);
    IShape oval1 = ShapeFactory.create("R","OVAL",50,100,
        60,120,0,-255,-255);
  }
}