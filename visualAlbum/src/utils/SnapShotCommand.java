package utils;

import model.IAlbumModel;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class represents a snapshot command.
 */
public class SnapShotCommand implements AlbumCommand {
  private IAlbumModel model;
  private String description;

  /**
   * Constructor.
   * @param model photo album model instance
   * @param instruction command instruction
   */
  public SnapShotCommand(IAlbumModel model, List<String> instruction) {
    this.model = model;
    try {
      if (instruction.size() == 1) {
        description = null;
      } else {
        description = instruction.subList(1, instruction.size()).stream()
            .collect(Collectors.joining(" "));
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void execute() {
    if (description == null) {
      model.takeSnapShots();
    } else {
      model.takeSnapShots(description);
    }
  }
}

