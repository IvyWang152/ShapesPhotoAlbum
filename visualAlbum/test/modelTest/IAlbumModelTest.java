package modelTest;

import static org.junit.Assert.assertEquals;

import model.Color;
import model.IAlbumModel;
import model.IShape;
import model.Oval;
import model.PhotoAlbumModel;
import model.Rectangle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * A JUnit test class for testing PhotoAlbum model.
 */
public class IAlbumModelTest {
  private IAlbumModel albumModel;
  private IShape oval;
  private IShape rectangle;
  private model.Color color1;
  private model.Color color2;
  //private IShape rectangle1;

  /**
   * Create an album model object and several shapes for testing.
   */
  @Before
  public void setUp() {
    color1 = new model.Color(255,255,0);
    color2 = new model.Color(255,0,255);
    albumModel = new PhotoAlbumModel();
    oval = new Oval("O", 500, 100, 60.0, 30.0, color1);
    rectangle = new Rectangle("R", 200.0, 200.0, 50.0, 100.0, color2);
    //rectangle1 = new Rectangle("R2",-50.0,-30.0,120,255);
  }


  /**
   * test add method and getCanvas method.
   */
  @Test
  public void testAddAndGetCanvas() {
    albumModel.add(oval);
    String actual = albumModel.getCanvas().get(0).toString();
    String expected = "Name: O\n"
        + "Type: oval\n"
        + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0,\n"
        + "Color: (255,255,0)\n";
    assertEquals(expected, actual);
    albumModel.add(rectangle);
    String expected1 = "Name: O\n"
        + "Type: oval\n"
        + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0,\n"
        + "Color: (255,255,0)\n"
        + "Name: R\n"
        + "Type: rectangle\n"
        + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0,\n"
        + "Color: (255,0,255)\n";
    String actual1 = albumModel.getCanvas().stream().map(Objects::toString)
        .collect(Collectors.joining());
    assertEquals(expected1, actual1);
  }

  private IAlbumModel setAlbum() {
    albumModel.add(oval);
    albumModel.add(rectangle);
    return albumModel;
  }

  /**
   * test move and getCanvas method.
   */
  @Test
  public void testMove() {
    setAlbum();
    albumModel.move(oval, 0.0, 0.0);
    assertEquals("(0.0,0.0)", oval.getPoint().toString());
    assertEquals("(200.0,200.0)", rectangle.getPoint().toString());
    albumModel.move(rectangle, 100.0, 300);
    assertEquals("(100.0,300.0)", rectangle.getPoint().toString());
    String expected = "Name: O\n"
        + "Type: oval\n"
        + "Center: (0.0,0.0), X radius: 60.0, Y radius: 30.0,\n"
        + "Color: (255,255,0)\n"
        + "Name: R\n"
        + "Type: rectangle\n"
        + "Min corner: (100.0,300.0), Width: 50.0, Height: 100.0,\n"
        + "Color: (255,0,255)\n";
    String actual = albumModel.getCanvas().stream().map(Objects::toString)
        .collect(Collectors.joining());
    assertEquals(expected, actual);
  }


  /**
   * test changeColor and getCanvas method.
   */
  @Test
  public void testChangeColor() {
    setAlbum();
    albumModel.changeColor(oval, new model.Color(255,0,0));
    assertEquals("(255,0,0)", oval.getColor().toString());
    albumModel.changeColor(rectangle, new model.Color(0,255,0));
    assertEquals("(0,255,0)", rectangle.getColor().toString());
    String expected = "Name: O\n"
        + "Type: oval\n"
        + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0,\n"
        + "Color: (255,0,0)\n"
        + "Name: R\n"
        + "Type: rectangle\n"
        + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0,\n"
        + "Color: (0,255,0)\n";

    String actual = albumModel.getCanvas().stream().map(Objects::toString)
        .collect(Collectors.joining());
    assertEquals(expected, actual);
  }

  /**
   * test resizeWidth method.
   */
  @Test
  public void testResizeWidth() {
    setAlbum();
    albumModel.resizeWidth(rectangle, 25);
    assertEquals(25.0, rectangle.getWidth(), 0.001);
    albumModel.resizeWidth(oval, 100.5);
    assertEquals(100.5, oval.getWidth(), 0.001);
    String expected = "Name: O\n"
        + "Type: oval\n"
        + "Center: (500.0,100.0), X radius: 100.5, Y radius: 30.0,\n"
        + "Color: (255,255,0)\n"
        + "Name: R\n"
        + "Type: rectangle\n"
        + "Min corner: (200.0,200.0), Width: 25.0, Height: 100.0,\n"
        + "Color: (255,0,255)\n";
    String actual = albumModel.getCanvas().stream().map(Objects::toString)
        .collect(Collectors.joining());
    assertEquals(expected, actual);
  }

  /**
   * test resizeHeight method.
   */
  @Test
  public void testResizeHeight() {
    setAlbum();
    albumModel.resizeHeight(oval, 5.9);
    assertEquals(5.9, oval.getHeight(), 0.001);
    albumModel.resizeHeight(rectangle, 86.15);
    assertEquals(86.15, rectangle.getHeight(), 0.001);
    String expected = "Name: O\n"
        + "Type: oval\n"
        + "Center: (500.0,100.0), X radius: 60.0, Y radius: 5.9,\n"
        + "Color: (255,255,0)\n"
        + "Name: R\n"
        + "Type: rectangle\n"
        + "Min corner: (200.0,200.0), Width: 50.0, Height: 86.15,\n"
        + "Color: (255,0,255)\n";
    String actual = albumModel.getCanvas().stream().map(Objects::toString)
        .collect(Collectors.joining());
    assertEquals(expected, actual);
  }

  /**
   * test move, changeColor, resizeHeight, resizeWidth methods
   * when the targeted shape is not on canvas.
   */
  @Test
  public void testShapeNotOnCanvas() {
    setAlbum();
    IShape rectangle2 = new Rectangle("R2",-50.0,-30.0,120,255);
    albumModel.move(rectangle2, 0.0,1.0);
    assertEquals("(-50.0,-30.0)",rectangle2.getPoint().toString());
    albumModel.changeColor(rectangle2,new model.Color(0,255,0));
    Assert.assertEquals(new Color(0,0,0).toString(),rectangle2.getColor().toString());
    albumModel.resizeHeight(rectangle2,15);
    assertEquals(255.0,rectangle2.getHeight(),0.001);
    albumModel.resizeWidth(rectangle2,12.0);
    assertEquals(120.0,rectangle2.getWidth(),0.001);
    String expected = "Name: O\n"
        + "Type: oval\n"
        + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0,\n"
        + "Color: (255,255,0)\n"
        + "Name: R\n"
        + "Type: rectangle\n"
        + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0,\n"
        + "Color: (255,0,255)\n";
    String actual = albumModel.getCanvas().stream().map(Objects::toString)
        .collect(Collectors.joining());
    assertEquals(expected, actual);
  }

  /**
   * test resizeWidth and resizeHeight methods when parameters are invalid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidResizing() {
    setAlbum();
    albumModel.resizeWidth(oval, 0);
    albumModel.resizeWidth(rectangle,-48.3);
    albumModel.resizeHeight(oval, -0.05);
    albumModel.resizeHeight(rectangle,0);
  }



  /**
   * test getSnapShotsID method after calling takeSnapShots method.
   */
  @Test
  public void testGetSnapShotsID() {
    // I trust LocalDateClass for creating time at a state, so
    // here I only test if the snapShotID exists after I called takeSnapShots method.
    setAlbum();
    assertEquals(0, albumModel.getSnapShotsID().size());
    albumModel.takeSnapShots();
    assertEquals(1, albumModel.getSnapShotsID().size());
    albumModel.move(oval, 0.0, 0.0);
    albumModel.takeSnapShots();
    assertEquals(2, albumModel.getSnapShotsID().size());
    albumModel.takeSnapShots();
    assertEquals(3, albumModel.getSnapShotsID().size());
  }

  /**
   * test getSnapShots method.
   */
  @Test
  public void testGetSnapShots() {
    setAlbum();
    albumModel.takeSnapShots();
    assertEquals(1,albumModel.getSnapShots().size());
    String expected1 = "Name: O\n"
        + "Type: oval\n"
        + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0,\n"
        + "Color: (255,255,0)\n"
        + "Name: R\n"
        + "Type: rectangle\n"
        + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0,\n"
        + "Color: (255,0,255)\n";
    String actual = albumModel.getSnapShots().get(0).stream().map(Objects::toString).collect(
        Collectors.joining());
    assertEquals(expected1, actual);
    albumModel.move(oval,0,-51.0);
    albumModel.changeColor(rectangle,new model.Color(255,255,255));
    albumModel.takeSnapShots();
    String expected2 = expected1 + "Name: O\n"
        + "Type: oval\n"
        + "Center: (0.0,-51.0), X radius: 60.0, Y radius: 30.0,\n"
        + "Color: (255,255,0)\n"
        + "Name: R\n"
        + "Type: rectangle\n"
        + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0,\n"
        + "Color: (255,255,255)\n";
    String actual1 = actual + albumModel.getSnapShots().get(1).stream().map(Objects::toString)
        .collect(Collectors.joining());
    assertEquals(expected2, actual1);

  }

  /**
   * test printSnapShots method after calling takeSnapShots method.
   */
  @Test
  public void testPrintSnapShots() {
    setAlbum();
    // test when there is no snapshots
    assertEquals("Printing Snapshots \n", albumModel.printSnapShots());
    albumModel.takeSnapShots("After first selfie");
    LocalDateTime time = albumModel.getSnapShotsID().get(0);
    DateTimeFormatter timeStampFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    String expected1 = "Printing Snapshots \n"
        + "Snapshot ID: " + time + "\n"
        + "Timestamp: " + timeStampFormat.format(time) + "\n"
        + "Description: After first selfie\n"
        + albumModel.getCanvas().stream().map(Objects::toString).collect(Collectors.joining());
    assertEquals(expected1, albumModel.printSnapShots());

    // moving and resizing and changing color of rectangle
    albumModel.move(rectangle, 100.0, 300.0);
    albumModel.resizeWidth(rectangle, 25.0);
    albumModel.changeColor(rectangle, new model.Color(0,255,0));
    albumModel.takeSnapShots("2nd selfie");
    LocalDateTime time1 = albumModel.getSnapShotsID().get(1);
    String expected2 = expected1
        + "Snapshot ID: " + time1 + "\n"
        + "Timestamp: " + timeStampFormat.format(time1) + "\n"
        + "Description: 2nd selfie\n"
        + albumModel.getCanvas().stream().map(Objects::toString).collect(Collectors.joining());
    assertEquals(expected2, albumModel.printSnapShots());

    //moving oval
    albumModel.move(oval, 100.0, 300.0);
    albumModel.takeSnapShots();
    LocalDateTime time2 = albumModel.getSnapShotsID().get(2);
    String expected3 = expected2
        + "Snapshot ID: " + time2 + "\n"
        + "Timestamp: " + timeStampFormat.format(time2) + "\n"
        + "Description: \n"
        + albumModel.getCanvas().stream().map(Objects::toString).collect(Collectors.joining());
    assertEquals(expected3, albumModel.printSnapShots());

    //removing rectangle
    albumModel.remove(rectangle);
    albumModel.takeSnapShots("Selfie after removing the rectangle from the picture");
    LocalDateTime time3 = albumModel.getSnapShotsID().get(3);
    String expected4 = expected3
        + "Snapshot ID: " + time3 + "\n"
        + "Timestamp: " + timeStampFormat.format(time3) + "\n"
        + "Description: Selfie after removing the rectangle from the picture\n"
        + albumModel.getCanvas().stream().map(Objects::toString).collect(Collectors.joining());
    assertEquals(expected4, albumModel.printSnapShots());
  }

  /**
   * test reset method.
   */
  @Test
  public void testReset() {
    setAlbum();
    albumModel.reset();
    assertEquals(0, albumModel.getCanvas().size());
    assertEquals(0, albumModel.getSnapShotsID().size());
    assertEquals(0,albumModel.getSnapShots().size());
  }

  /**
   * Test getIndex and updateIndex method.
   */
  @Test
  public void testIndex() {
    setAlbum();
    albumModel.takeSnapShots();
    albumModel.changeColor(oval,new Color(100,100,100));
    albumModel.takeSnapShots();
    albumModel.remove(rectangle);
    albumModel.takeSnapShots();
    //default value of index
    assertEquals(0,albumModel.getIndex());
    albumModel.updateIndex(1);
    assertEquals(1,albumModel.getIndex());
    albumModel.updateIndex(2);
    assertEquals(2,albumModel.getIndex());
    albumModel.updateIndex(0);
    assertEquals(0,albumModel.getIndex());
    //invalid update
    albumModel.updateIndex(-1);
    assertEquals(0,albumModel.getIndex());
    //invalid update
    albumModel.updateIndex(3);
    assertEquals(0,albumModel.getIndex());
  }

  /**
   * Test getDescription method.
   */
  @Test
  public void testGetDescription() {
    setAlbum();
    albumModel.takeSnapShots("This is the first selfie");
    albumModel.changeColor(oval,new Color(100,100,100));
    albumModel.takeSnapShots("Woo! second photo");
    albumModel.remove(rectangle);
    albumModel.takeSnapShots();
    List<String> expected = new ArrayList<>();
    expected.add("This is the first selfie");
    expected.add("Woo! second photo");
    expected.add("");
    assertEquals(expected,albumModel.getDescription());

  }

}