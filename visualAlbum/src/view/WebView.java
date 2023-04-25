package view;

import model.IAlbumModel;
import model.IShape;
import model.ShapeType;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * This class implements a web view of the shape photo album.
 */
public class WebView implements IView {
  private IAlbumModel model;
  private List<LocalDateTime> IDList;
  private FileWriter out;
  private int xMax;
  private int yMax;

  /**
   * Constructor.
   * @param outputFilePath path name of the output html file
   * @throws IOException when path name is not right
   */
  public WebView(String outputFilePath, int xMax, int yMax) throws IOException {
    if (outputFilePath == null) {
      throw new IllegalArgumentException("The output is not specified");
    }
    this.xMax = xMax;
    this.yMax = yMax;
    this.out = new FileWriter(outputFilePath);
  }

  /**
   * Display web view of the photo album.
   * @param model photo album model instance
   */
  @Override
  public void display(IAlbumModel model) {
    this.model = model;
    generateHtmlCode(this.model);
    try {
      out.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }


  /**
   * Generate html code for whole photo album.
   * @param model photo album model instance
   */
  public void generateHtmlCode(IAlbumModel model) {
    try {
      out.write("<!DOCTYPE html>\n<html>\n<head>\n<title>cs5004 Shapes Photo Album</title>\n"
          + "<style>\n"
          + ".photo {\n"
          + "  background-color: cyan;\n"
          + "  color: black;\n"
          + "  border: 10px solid black;\n"
          + "  margin: 30px;\n"
          + "  padding: 30px;\n"
          + "  width: " + xMax + "px;\n"
          + "  height: " + yMax + "px;\n"
          + "}\n"
          + "</style>"
          + "</head>\n<body>\n");
      for (int index = 0; index < model.getSnapShotsID().size(); index++) {
        out.write("<div class=\"photo\">\n");
        out.write(String.format("<h2>%s</h2>\n", model.getSnapShotsID().get(index)));
        if (model.getDescription().get(index) != "") {
          out.write(String.format("<p>Description: %s</p>\n", model.getDescription().get(index)));
        }
        model.updateIndex(index);
        out.write(groupSnapShot(model));
        out.write("</div>\n");
      }
      out.write("</body>\n");
      out.write("</html>\n");


    } catch (Exception e) {
      e.printStackTrace();
    }


  }


  /**
   * Generate html code for single snapShot at current index.
   * @param model photoAlbum model instance
   * @return a string format of html code
   */
  public String groupSnapShot(IAlbumModel model) {
    SVGComponentDrawer drawer = new SVGComponentDrawer();
    List<IShape> snapShot = model.getSnapShots().get(model.getIndex());
    StringBuilder result = new StringBuilder();
    //result.append("<svg width=\"1000\" height=\"1000\">\n");
    result.append("<svg width=" + xMax + " height=" + yMax + ">\n");
    //result.append("<svg>\n");
    result.append("<g>\n");
    for (IShape shape : snapShot) {
      if (shape.getType() == ShapeType.OVAL) {
        drawer.addOval((int) shape.getPoint().getX(), (int) shape.getPoint().getY(),
            (int) shape.getWidth(),
            (int) shape.getHeight(), shape.getColor().getRed(),
            shape.getColor().getGreen(), shape.getColor().getBlue());
      }
      if (shape.getType() == ShapeType.RECTANGLE) {
        drawer.addRect((int) shape.getPoint().getX(), (int) shape.getPoint().getY(),
            (int) shape.getWidth(),
            (int) shape.getHeight(), shape.getColor().getRed(),
            shape.getColor().getGreen(), shape.getColor().getBlue());
      }
    }
    result.append(drawer.getSVGComponent());
    result.append("</g>\n");
    result.append("</svg>\n");
    return result.toString();
  }

}
