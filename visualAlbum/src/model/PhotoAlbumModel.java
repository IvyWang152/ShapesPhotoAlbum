package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represent a photo album model. It has all operations declared
 * in model.IAlbumModel interface.
 */
public class PhotoAlbumModel implements IAlbumModel {
  //private Map<String, IShape> canvas;
  private List<IShape> canvas;
  private Map<LocalDateTime, List<IShape>> snapShots;
  private Map<LocalDateTime, String> snapShotsDescription;
  private int index;

  /**
   * Constructor of photo album model.
   */
  public PhotoAlbumModel() {
    this.canvas = new ArrayList<>();
    this.snapShots = new LinkedHashMap<>();
    this.snapShotsDescription = new LinkedHashMap<>();
    this.index = 0; //default album page number
  }

  @Override
  public void add(IShape shape) {
    //add shape to the album canvas
    this.canvas.add(shape);
  }

  @Override
  public void move(IShape shape, double x, double y) {
    // the album only moves a shape that exists on the album canvas
    if (this.canvas.contains(shape)) {
      shape.move(x,y);
    }

  }

  @Override
  public void remove(IShape shape) {
    // the album only removes a shape that exists on the album canvas
    if (this.canvas.contains(shape)) {
      canvas.remove(shape);
    }
  }

  @Override
  public void changeColor(IShape shape, Color color) {
    // the album only changes color of a shape that exists on the album canvas
    if (this.canvas.contains(shape)) {
      shape.setColor(color);
    }
  }

  @Override
  public void resizeWidth(IShape shape, double width) {
    // the album only resizes a shape that exists on the album canvas
    if (this.canvas.contains(shape)) {
      if (width <= 0) {
        throw new IllegalArgumentException("Width should be greater than 0");
      }
      shape.setWidth(width);
    }
  }

  @Override
  public void resizeHeight(IShape shape, double height) {
    // the album only resizes a shape that exists on the album canvas
    if (this.canvas.contains(shape)) {
      if (height <= 0) {
        throw new IllegalArgumentException("Width should be greater than 0");
      }
      shape.setHeight(height);
    }
  }

  @Override
  public int getIndex() {
    return this.index;
  }

  @Override
  public void updateIndex(int updated) {
    if (updated >= 0 && updated < getSnapShotsID().size()) {
      this.index = updated;
    }
  }

  @Override
  public List<LocalDateTime> getSnapShotsID() {
    return snapShots.keySet().stream().toList();
  }


  @Override
  public void takeSnapShots() {
    LocalDateTime ID = LocalDateTime.now();
    List<IShape> copyCanvas = new ArrayList<>();
    for (IShape shape: canvas) {
      copyCanvas.add(shape.copy());
    }
    this.snapShots.put(ID,copyCanvas);
    snapShotsDescription.put(ID,"");
  }

  @Override
  public void takeSnapShots(String description) {
    LocalDateTime ID = LocalDateTime.now();
    List<IShape> copyCanvas = new ArrayList<>();
    for (IShape shape: canvas) {
      copyCanvas.add(shape.copy());
    }
    this.snapShots.put(ID,copyCanvas);
    snapShotsDescription.put(ID,description);
  }

  @Override
  public List<List<IShape>> getSnapShots() {
    return this.snapShots.values().stream().toList();
  }


  @Override
  public String printSnapShots() {
    String result = "Printing Snapshots \n";
    DateTimeFormatter timeStampFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    for (Map.Entry<LocalDateTime,List<IShape>> entry: snapShots.entrySet()) {
      result = result + "Snapshot ID: " + entry.getKey() + "\n"
          + "Timestamp: " + timeStampFormat.format(entry.getKey()) + "\n"
          + "Description: " + snapShotsDescription.get(entry.getKey()) + "\n";
      for (IShape shape: snapShots.get(entry.getKey())) {
        result = result + shape.toString();
      }
    }
    return result;
  }

  @Override
  public void reset() {
    snapShots.clear();
    snapShotsDescription.clear();
    canvas.clear();
  }

  @Override
  public List<IShape> getCanvas() {
    return this.canvas;
  }

  @Override
  public List<String> getDescription() {
    return snapShotsDescription.values().stream().toList();
  }


}
