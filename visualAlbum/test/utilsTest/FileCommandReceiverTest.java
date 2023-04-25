package utilsTest;

import static org.junit.Assert.assertEquals;

import model.IAlbumModel;
import model.PhotoAlbumModel;
import org.junit.Before;
import org.junit.Test;
import utils.CommandInvoker;
import utils.FileCommandReceiver;
import utils.FileHandler;

import java.io.IOException;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * A JUnit test class for FileCommandReceiver.
 */
public class FileCommandReceiverTest {
  private CommandInvoker commandInvoker;
  private FileCommandReceiver commandReceiver;
  private FileHandler fileHandler;
  private IAlbumModel model;

  /**
   * Create a FileCommandReceiver for testing.
   * @throws IOException when I/O error occurs
   */
  @Before
  public void setUp() throws IOException {
    model = new PhotoAlbumModel();
    fileHandler = new FileHandler("demo_input.txt");
    commandReceiver = new FileCommandReceiver(fileHandler);
  }


  /**
   * Test readCommand method.
   */
  @Test
  public void readCommand() {
    commandInvoker = commandReceiver.readCommand(model);
    commandInvoker.executeCommand();
    assertEquals(4,model.getSnapShots().size());
    String expected = "Name: myrect\n"
        + "Type: rectangle\n"
        + "Min corner: (200.0,200.0), Width: 50.0, Height: 100.0,\n"
        + "Color: (255,0,0)\n"
        + "Name: myoval\n"
        + "Type: oval\n"
        + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0,\n"
        + "Color: (0,255,1)\n";
    String actual = model.getSnapShots().get(0).stream().map(Objects::toString).collect(
        Collectors.joining());
    assertEquals(expected, actual);
    String expected1 = "Name: myrect\n"
        + "Type: rectangle\n"
        + "Min corner: (100.0,300.0), Width: 25.0, Height: 100.0,\n"
        + "Color: (255,0,0)\n"
        + "Name: myoval\n"
        + "Type: oval\n"
        + "Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0,\n"
        + "Color: (0,255,1)\n";
    String actual1 = model.getSnapShots().get(1).stream().map(Objects::toString).collect(
        Collectors.joining());
    assertEquals(expected1, actual1);
    String expected2 = "Name: myrect\n"
        + "Type: rectangle\n"
        + "Min corner: (100.0,300.0), Width: 25.0, Height: 100.0,\n"
        + "Color: (0,0,255)\n"
        + "Name: myoval\n"
        + "Type: oval\n"
        + "Center: (500.0,400.0), X radius: 60.0, Y radius: 30.0,\n"
        + "Color: (0,255,1)\n";
    String actual2 = model.getSnapShots().get(2).stream().map(Objects::toString).collect(
        Collectors.joining());
    assertEquals(expected2, actual2);
    String expected3 = "Name: myoval\n"
        + "Type: oval\n"
        + "Center: (500.0,400.0), X radius: 60.0, Y radius: 30.0,\n"
        + "Color: (0,255,1)\n";
    String actual3 = model.getSnapShots().get(3).stream().map(Objects::toString).collect(
        Collectors.joining());
    assertEquals(expected3,actual3);
  }
}