package controllerTest;

import static org.junit.Assert.assertEquals;

import controller.PhotoAlbumController;
import model.IAlbumModel;
import model.PhotoAlbumModel;
import org.junit.Before;
import org.junit.Test;
import view.IView;
import view.WebView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * A JUnit test for PhotoAlbumController class.
 */
public class PhotoAlbumControllerTest {
  private PhotoAlbumController controller;
  private IAlbumModel model;
  private IView webView;

  /**
   * Create model, view and controller instances for testing.
   * @throws IOException when an I/O error occurs
   */
  @Before
  public void setUp() throws IOException {
    model = new PhotoAlbumModel();
    webView = new WebView("out.html",1000,1200);
    controller = new PhotoAlbumController("demo_input.txt",model,webView);
  }


  /**
   * Test run method.
   */
  @Test
  public void run() throws IOException {
    String result = Files.readString(Path.of("out.html"));
    assertEquals("",result);
    assertEquals(0,model.getCanvas().size());
    assertEquals(0,model.getSnapShots().size());
    controller.run();
    //Calling run() method in controller updates model from command file and sends data to the view

    assertEquals(4,model.getSnapShots().size());
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
    String result1 = Files.readString(Path.of("out.html"));
    assertEquals(expected.toString(),result1);
  }
}