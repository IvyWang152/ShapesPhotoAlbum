package utilsTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import utils.FileHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A Junit test for FileHandler class.
 */
public class FileHandlerTest {

  private FileHandler handler;

  /**
   * Test parseFile method.
   */
  @Test
  public void testParseFile() {
    handler = new FileHandler("demo_input.txt");
    List<List<String>> result = handler.parseFile();
    assertEquals(12, result.size());
    List<List<String>> expected = new ArrayList<>();
    expected.add(
        Arrays.stream(new String[] {"shape", "myrect", "rectangle", "200", "200", "50", "100",
            "255", "0", "0"}).toList());
    expected.add(
        Arrays.stream(new String[] {"shape", "myoval", "oval", "500", "100", "60", "30",
            "0", "255", "1"}).toList());
    expected.add(
        Arrays.stream(new String[] {"snapShot", "After", "first", "selfie"}).toList());
    expected.add(
        Arrays.stream(new String[] {"move", "myrect", "300", "200"}).toList());
    expected.add(
        Arrays.stream(new String[] {"resize", "myrect", "25", "100"}).toList());
    expected.add(
        Arrays.stream(new String[] {"move", "myrect", "100", "300"}).toList());
    expected.add(
        Arrays.stream(new String[] {"snapShot", "2nd", "selfie"}).toList());
    expected.add(
        Arrays.stream(new String[] {"color", "myrect", "0", "0", "255"}).toList());
    expected.add(
        Arrays.stream(new String[] {"move", "myoval", "500", "400"}).toList());
    expected.add(
        Arrays.stream(new String[] {"snapShot"}).toList());
    expected.add(
        Arrays.stream(new String[] {"remove", "myrect"}).toList());
    expected.add(
        Arrays.stream(new String[] {"snapshot", "Selfie", "after", "removing",
            "the", "rectangle", "from", "the", "picture"}).toList());
    assertEquals(expected, result);
  }

}