package utils;

import model.IAlbumModel;
import model.IShape;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class represents a move command.
 */
public class MoveCommand implements AlbumCommand {
  private IAlbumModel model;
  private String name;
  private int x;
  private int y;

  /**
   * Constructor.
   * @param model photo album model instance
   * @param instruction command instruction
   */
  public MoveCommand(IAlbumModel model, List<String> instruction) {
    this.model = model;
    try {
      name = instruction.get(1);
      List<Integer> params = instruction.subList(2, instruction.size()).stream()
          .map(Integer::parseInt)
          .collect(Collectors.toList());
      x = params.get(0);
      y = params.get(1);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void execute() {
    IShape shape = SearchShape.search(model,name);
    model.move(shape, x, y);

  }
}
