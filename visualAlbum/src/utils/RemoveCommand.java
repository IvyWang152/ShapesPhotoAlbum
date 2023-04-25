package utils;

import model.IAlbumModel;
import model.IShape;

import java.util.List;

/**
 * This class represents a remove command.
 */
public class RemoveCommand implements AlbumCommand {
  private IAlbumModel model;
  private String name;

  /**
   * Constructor.
   * @param model photo album model interface
   * @param instruction command instruction
   */
  public RemoveCommand(IAlbumModel model, List<String> instruction) {
    this.model = model;
    try {
      name = instruction.get(1);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void execute() {
    IShape shape = SearchShape.search(model,name);
    model.remove(shape);
  }
}
