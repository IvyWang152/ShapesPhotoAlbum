package utils;

import model.IAlbumModel;
import model.IShape;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class represents a resize command.
 */
public class ResizeCommand implements AlbumCommand {
  private IAlbumModel model;
  private String name;
  private int width;
  private int height;

  /**
   * Constructor.
   * @param model photo album model instance
   * @param instruction command instruction
   */
  public ResizeCommand(IAlbumModel model, List<String> instruction) {
    this.model = model;
    try {
      name = instruction.get(1);
      List<Integer> params = instruction.subList(2, instruction.size()).stream()
          .map(Integer::parseInt)
          .collect(Collectors.toList());
      width = params.get(0);
      height = params.get(1);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void execute() {
    IShape shape = SearchShape.search(model,name);
    model.resizeWidth(shape,width);
    model.resizeHeight(shape,height);
  }
}

