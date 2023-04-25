package utilsTest;

import static org.junit.Assert.assertEquals;

import model.Color;
import model.IAlbumModel;
import model.PhotoAlbumModel;
import model.Point2D;
import org.junit.Before;
import org.junit.Test;
import utils.AlbumCommand;
import utils.ColorCommand;
import utils.MoveCommand;
import utils.RemoveCommand;
import utils.ResizeCommand;
import utils.ShapeCommand;
import utils.SnapShotCommand;

import java.util.Arrays;

/**
 * A JUnit test for Concrete command classes.
 */
public class AlbumCommandTest {
  private AlbumCommand colorCommand;
  private AlbumCommand moveCommand;
  private AlbumCommand removeCommand;
  private AlbumCommand resizeCommand;
  private AlbumCommand shapeCommand;
  private AlbumCommand shapeCommand1;
  private AlbumCommand snapShotCommand;
  private IAlbumModel model;

  /**
   * Create various command objects for testing.
   */
  @Before
  public void setUp() {
    model = new PhotoAlbumModel();
    shapeCommand = new ShapeCommand(model,
        Arrays.stream(new String[] {"shape", "myrect", "rectangle", "200", "200", "50", "100",
            "255", "0", "0"}).toList());
    shapeCommand1 = new ShapeCommand(model, Arrays.stream(new String[]
        {"shape", "myoval", "oval", "500", "100", "60", "30",
        "0", "255", "1"}).toList());
  }

  /**
   * Test execute method.
   */
  @Test
  public void testExecute() {
    //test ShapeCommand
    shapeCommand.execute();
    shapeCommand1.execute();
    assertEquals(2,model.getCanvas().size());
    //test SnapShotCommand
    snapShotCommand = new SnapShotCommand(model, Arrays.stream(new String[]
        {"snapShot", "After", "first", "selfie"}).toList());
    snapShotCommand.execute();
    assertEquals(1,model.getSnapShotsID().size());
    assertEquals("After first selfie",model.getDescription().get(0));
    //test MoveCommand
    moveCommand = new MoveCommand(model, Arrays.stream(new String[]
        {"move", "myrect", "300", "200"}).toList());
    moveCommand.execute();
    assertEquals(new Point2D(300,200),model.getCanvas().get(0).getPoint());
    //test ResizeCommand
    resizeCommand = new ResizeCommand(model, Arrays.stream(new String[]
        {"resize", "myrect", "25", "100"}).toList());
    resizeCommand.execute();
    assertEquals(25,model.getCanvas().get(0).getWidth(),0.01);
    assertEquals(100,model.getCanvas().get(0).getHeight(),0.01);
    //test ColorCommand
    colorCommand = new ColorCommand(model,Arrays.stream(new String[]
        {"color", "myrect", "0", "0", "255"}).toList());
    colorCommand.execute();
    assertEquals(new Color(0,0,255), model.getCanvas().get(0).getColor());
    //test RemoveCommand
    removeCommand = new RemoveCommand(model,Arrays.stream(new String[]
        {"remove", "myrect"}).toList());
    removeCommand.execute();
    assertEquals(1,model.getCanvas().size());
    assertEquals("myoval",model.getCanvas().get(0).getName());
  }
}