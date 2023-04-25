package viewTest;

import static org.junit.Assert.assertEquals;

import model.IAlbumModel;
import model.PhotoAlbumModel;
import org.junit.Before;
import org.junit.Test;
import utils.FileCommandReceiver;
import utils.FileHandler;
import view.WebView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * A JUnit test for WebView class.
 */
public class WebViewTest {
  private IAlbumModel model;
  private WebView webView;
  private FileHandler handler;
  private FileCommandReceiver receiver;

  /**
   * Create model, view, FileHandler, FileCommandReceiver objects to set up the webView for testing.
   * @throws IOException when an I/O error occurs
   */
  @Before
  public void setUp() throws IOException {
    model = new PhotoAlbumModel();
    handler = new FileHandler("demo_input.txt");
    receiver = new FileCommandReceiver(handler);
    receiver.readCommand(model).executeCommand();
    webView = new WebView("myWeb.html", 1000, 1200);
  }


  /**
   * Test groupSnapShot method.
   */
  @Test
  public void testGroupSnapShot() {
    String result = webView.groupSnapShot(model);
    String expected = "<svg width=1000 height=1200>\n"
        + "<g>\n"
        + "<rect x=\"200\" y=\"200\" width=\"50\" height=\"100\" fill=\"rgb(255,0,0)\" />\n"
        + "<ellipse cx=\"500\" cy=\"100\" rx=\"60\" ry=\"30\" fill=\"rgb(0,255,1)\" />\n"
        + "</g>\n"
        + "</svg>\n";
    assertEquals(expected, result);

    model.updateIndex(1);
    String result1 = webView.groupSnapShot(model);
    String expected1 = "<svg width=1000 height=1200>\n"
        + "<g>\n"
        + "<rect x=\"100\" y=\"300\" width=\"25\" height=\"100\" fill=\"rgb(255,0,0)\" />\n"
        + "<ellipse cx=\"500\" cy=\"100\" rx=\"60\" ry=\"30\" fill=\"rgb(0,255,1)\" />\n"
        + "</g>\n"
        + "</svg>\n";
    assertEquals(expected1, result1);

    model.updateIndex(2);
    String result2 = webView.groupSnapShot(model);
    String expected2 = "<svg width=1000 height=1200>\n"
        + "<g>\n"
        + "<rect x=\"100\" y=\"300\" width=\"25\" height=\"100\" fill=\"rgb(0,0,255)\" />\n"
        + "<ellipse cx=\"500\" cy=\"400\" rx=\"60\" ry=\"30\" fill=\"rgb(0,255,1)\" />\n"
        + "</g>\n"
        + "</svg>\n";
    assertEquals(expected2, result2);

    model.updateIndex(3);
    String result3 = webView.groupSnapShot(model);
    String expected3 = "<svg width=1000 height=1200>\n"
        + "<g>\n"
        + "<ellipse cx=\"500\" cy=\"400\" rx=\"60\" ry=\"30\" fill=\"rgb(0,255,1)\" />\n"
        + "</g>\n"
        + "</svg>\n";
    assertEquals(expected3, result3);
  }

  /**
   * Test generateHtmlCode method and display method.
   * @throws IOException when an I/O error occur
   */
  @Test
  public void testGenerateHtmlCodeAndDisplay() throws IOException {
    webView.display(model);
    StringBuilder expected = new StringBuilder();
    expected.append("<!DOCTYPE html>\n<html>\n<head>\n<title>cs5004 Shapes Photo Album</title>\n"
        + "<style>\n"
        + ".photo {\n"
        + "  background-color: cyan;\n"
        + "  color: black;\n"
        + "  border: 10px solid black;\n"
        + "  margin: 30px;\n"
        + "  padding: 30px;\n"
        + "  width: 1000px;\n"
        + "  height: 1200px;\n"
        + "}\n"
        + "</style>"
        + "</head>\n<body>\n");
    expected.append(String.format("<div class=\"photo\">\n<h2>%s</h2>\n",
        model.getSnapShotsID().get(0)));
    expected.append("<p>Description: After first selfie</p>\n"
        + "<svg width=1000 height=1200>\n"
        + "<g>\n"
        + "<rect x=\"200\" y=\"200\" width=\"50\" height=\"100\" fill=\"rgb(255,0,0)\" />\n"
        + "<ellipse cx=\"500\" cy=\"100\" rx=\"60\" ry=\"30\" fill=\"rgb(0,255,1)\" />\n"
        + "</g>\n"
        + "</svg>\n"
        + "</div>\n");
    expected.append(String.format("<div class=\"photo\">\n<h2>%s</h2>\n",
        model.getSnapShotsID().get(1)));
    expected.append("<p>Description: 2nd selfie</p>\n"
        + "<svg width=1000 height=1200>\n"
        + "<g>\n"
        + "<rect x=\"100\" y=\"300\" width=\"25\" height=\"100\" fill=\"rgb(255,0,0)\" />\n"
        + "<ellipse cx=\"500\" cy=\"100\" rx=\"60\" ry=\"30\" fill=\"rgb(0,255,1)\" />\n"
        + "</g>\n"
        + "</svg>\n"
        + "</div>\n");
    expected.append(String.format("<div class=\"photo\">\n<h2>%s</h2>\n",
        model.getSnapShotsID().get(2)));
    expected.append("<svg width=1000 height=1200>\n"
        + "<g>\n"
        + "<rect x=\"100\" y=\"300\" width=\"25\" height=\"100\" fill=\"rgb(0,0,255)\" />\n"
        + "<ellipse cx=\"500\" cy=\"400\" rx=\"60\" ry=\"30\" fill=\"rgb(0,255,1)\" />\n"
        + "</g>\n"
        + "</svg>\n"
        + "</div>\n");
    expected.append(String.format("<div class=\"photo\">\n<h2>%s</h2>\n",
        model.getSnapShotsID().get(3)));
    expected.append("<p>Description: Selfie after removing the rectangle from the picture</p>\n"
        + "<svg width=1000 height=1200>\n"
        + "<g>\n"
        + "<ellipse cx=\"500\" cy=\"400\" rx=\"60\" ry=\"30\" fill=\"rgb(0,255,1)\" />\n"
        + "</g>\n"
        + "</svg>\n"
        + "</div>\n"
        + "</body>\n"
        + "</html>\n");
    String result = Files.readString(Path.of("myWeb.html"));
    assertEquals(expected.toString(), result);

  }
}
