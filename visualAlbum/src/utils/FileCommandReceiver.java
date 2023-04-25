package utils;

import model.IAlbumModel;

import java.io.IOException;
import java.util.List;

/**
 * This class represents a command file receiver. It receives a FileHandler object
 * and can read commands from the FileHandler and update the model according to the commands.
 */
public class FileCommandReceiver {
  private List<List<String>> reader;
  private CommandInvoker commandInvoker;

  /**
   * Constructor.
   * @param fileHandler a fileHandler object
   */
  public FileCommandReceiver(FileHandler fileHandler) throws IOException {
    this.reader = fileHandler.parseFile();
    this.commandInvoker = new CommandInvoker();

  }

  /**
   * Read command and update the photo album model.
   * @param model photo album model instance
   * @return a CommandInvoker object contains a list of commands to be executed
   */
  public CommandInvoker readCommand(IAlbumModel model) {
    try {
      for (List<String> instruction : reader) {
        String command = instruction.get(0);
        if (command.equalsIgnoreCase("shape")) {
          ShapeCommand shapeCommand = new ShapeCommand(model, instruction);
          commandInvoker.acceptCommand(shapeCommand);
        }
        if (command.equalsIgnoreCase("snapshot")) {
          SnapShotCommand snapShotCommand = new SnapShotCommand(model, instruction);
          commandInvoker.acceptCommand(snapShotCommand);
        }
        if (command.equalsIgnoreCase("move")) {
          MoveCommand moveCommand = new MoveCommand(model, instruction);
          commandInvoker.acceptCommand(moveCommand);
        }
        if (command.equalsIgnoreCase("color")) {
          ColorCommand colorCommand = new ColorCommand(model, instruction);
          commandInvoker.acceptCommand(colorCommand);
        }
        if (command.equalsIgnoreCase("resize")) {
          ResizeCommand resizeCommand = new ResizeCommand(model, instruction);
          commandInvoker.acceptCommand(resizeCommand);
        }
        if (command.equalsIgnoreCase("remove")) {
          RemoveCommand removeCommand = new RemoveCommand(model,instruction);
          commandInvoker.acceptCommand(removeCommand);
        }
      }
      return commandInvoker;

      //handler.executeCommand();

    } catch (Exception e) {
      throw new RuntimeException();
    }
  }
}
