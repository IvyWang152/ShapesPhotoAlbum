package utils;

import model.IAlbumModel;
import model.IShape;

/**
 * This is a helper class to search shape from photo album model.
 */
public class SearchShape {
  /**
   * Search shape from the photo album model object according to its name.
   * @param model photo album model instance
   * @param name name of the shape
   * @return a shape object
   */
  public static IShape search(IAlbumModel model, String name) {
    IShape expected = null;
    for (IShape shape: model.getCanvas()) {
      if (shape.getName().equals(name)) {
        expected = shape;
      }
    }
    return expected;
  }
}
