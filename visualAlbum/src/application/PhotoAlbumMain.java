package application;

import controller.PhotoAlbumController;
import model.IAlbumModel;
import model.PhotoAlbumModel;
import view.GUIView;
import view.IView;
import view.WebView;

import java.io.IOException;

/**
 * This class provides an entry point to run the photo album application, along with
 * PhotoAlbumController, IAlbumModel and IView. The implementation is based on MVC design pattern.
 *
 */
public class PhotoAlbumMain {

  // args: command-line command , parse command args
  //instantiate model, view, controller objects

  /**
   * Create model, view, controller and launch the application.
   * @param args input arguments from command line
   * @throws IOException when an I/O error occurs
   */
  public static void main(String[] args) throws IOException {
    String input = null;
    String output = null;
    String viewType = null;

    int xMax = 1000;
    int yMax = 1000;

    for (int i = 0; i < args.length - 1; i++) {

      if (args[i].equals("-in")) {
        input = args[++i];
        continue;
      } else if (args[i].equals("-out")) {
        output = args[++i];
        continue;
      } else if (args[i].equals("-v") || args[i].equals("-view")) {
        viewType = args[++i];
        continue;
      }
      else if (isInt(args[i]) && isInt(args[i + 1])) {
        xMax = Integer.parseInt(args[i]);
        yMax = Integer.parseInt(args[++i]);
        continue;
      }
      else {
        System.err.println("Invalid command argument");
        System.exit(1);

      }
    }
    //check mandatory arguments
    if (input == null || viewType == null) {
      System.err.println("You missed mandatory arguments for input(-in) and view(-v/-view)");
      System.exit(1);
    }

    IAlbumModel model = new PhotoAlbumModel();
    if (viewType.equalsIgnoreCase("web")) {
      //check output file name when view is web
      if (output == null) {
        System.err.println("You forgot to create output file");
        System.exit(1);
      }
      IView view = new WebView(output,xMax,yMax);
      PhotoAlbumController controller = new PhotoAlbumController(input,model,view);
      controller.run();
    } else if (viewType.equalsIgnoreCase("graphical")) {
      IView view = new GUIView("CS5004 Photo Album Viewer",xMax,yMax);
      PhotoAlbumController controller = new PhotoAlbumController(input,model,view);
      view.registerActionListener(controller);
      controller.run();
    } else {
      System.err.println("Invalid view type");
      System.exit(1);
    }

  }

  /**
   * Helper method to check whether a string value is an integer or not.
   * @param str input string
   * @return true if it is an integer, otherwise false
   */
  public static boolean isInt(String str) {
    try {
      Integer.parseInt(str);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }
}
