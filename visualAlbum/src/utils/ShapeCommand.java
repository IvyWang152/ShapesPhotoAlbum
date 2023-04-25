package utils;

import model.IAlbumModel;
import model.IShape;
import model.ShapeFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class presents a shape command.
 */
public class ShapeCommand implements AlbumCommand {
  private IAlbumModel model;
  private String name;
  private String shapeType;
  private int x;
  private int y;
  private int width;
  private int height;
  private int r;
  private int g;
  private int b;

  /**
   * Constructor.
   * @param model photo album model instance
   * @param instruction command instruction
   */
  public ShapeCommand(IAlbumModel model, List<String> instruction) {
    this.model = model;
    try {
      name = instruction.get(1);
      shapeType = instruction.get(2);
      List<Integer> params = instruction.subList(3, instruction.size()).stream()
          .map(Integer::parseInt)
          .collect(Collectors.toList());
      x = params.get(0);
      y = params.get(1);
      width = params.get(2);
      height = params.get(3);
      r = params.get(4);
      g = params.get(5);
      b = params.get(6);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void execute() {
    IShape shape = ShapeFactory.create(name,shapeType,x,y,width,height,r,g,b);
    model.add(shape);
  }
}
