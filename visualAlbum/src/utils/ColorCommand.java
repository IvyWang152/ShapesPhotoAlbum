package utils;

import model.Color;
import model.IAlbumModel;
import model.IShape;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class represents a color command.
 */
public class ColorCommand implements AlbumCommand {
  private IAlbumModel model;
  private String name;
  private int r;
  private int g;
  private int b;

  /**
   * Constructor.
   * @param model photo album model instance
   * @param instruction command instruction
   */
  public ColorCommand(IAlbumModel model, List<String> instruction) {
    this.model = model;
    try {
      name = instruction.get(1);
      List<Integer> params = instruction.subList(2, instruction.size()).stream()
          .map(Integer::parseInt)
          .collect(Collectors.toList());
      r = params.get(0);
      g = params.get(1);
      b = params.get(2);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void execute() {
    IShape shape = SearchShape.search(model,name);
    model.changeColor(shape, new Color(r,g,b));
  }
}

