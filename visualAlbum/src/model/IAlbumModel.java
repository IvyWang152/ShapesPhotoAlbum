package model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * This interface declares all operations for model.PhotoAlbumModel.
 */
public interface IAlbumModel {
  /**
   * Add shape to the album.
   */
  void add(IShape shape);

  /**
   * Move the shape on album canvas to a given point.
   * @param shape shape
   * @param x x coordinate of the target point
   * @param y y coordinate of the target point
   */
  void move(IShape shape, double x, double y);

  /**
   * Remove an existing shape from the album canvas.
   * @param shape targeted shape
   */
  void remove(IShape shape);

  /**
   * Change the color of a shape on the album canvas.
   * @param shape target shape
   * @param color target color
   */
  void changeColor(IShape shape, Color color);

  /**
   * Resize the width of a shape on the album canvas.
   * @param shape target shape
   * @param width target width
   * @throws IllegalArgumentException when width is less than or equal to 0
   */
  void resizeWidth(IShape shape, double width) throws IllegalArgumentException;

  /**
   * Resize the height of a shape on the album canvas.
   * @param shape target shape
   * @param height target height
   * @throws IllegalArgumentException when height id less than or equal to 0
   */
  void resizeHeight(IShape shape, double height) throws IllegalArgumentException;

  public int getIndex();

  public void updateIndex(int updated);

  /**
   * Get list of snapShots ID taken for the photo album at different time.
   * @return a list of time when snapShots were taken
   */
  List<LocalDateTime> getSnapShotsID();

  /**
   * Take snapShots of current album canvas.
   */
  void takeSnapShots();

  /**
   * Take snapShots of current photo album with description.
   * @param description description of the snapShots
   */
  void takeSnapShots(String description);

  /**
   * Get all snapShots of the photoAlbum model.
   * @return a map of snapShots containing time IDs and lists of shapes
   */
  List<List<IShape>> getSnapShots();

  /**
   * Print snapShots history.
   * @return a string representation of previous snapShots with detailed information
   */
  String printSnapShots();

  /**
   * Reset the photo album to initial state.
   */
  void reset();

  /**
   * Get shapes that are added on the album canvas.
   * @return list of shapes
   */
  List<IShape> getCanvas();

  List<String> getDescription();

}
