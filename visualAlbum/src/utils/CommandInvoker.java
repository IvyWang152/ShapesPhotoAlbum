package utils;

import java.util.ArrayList;
import java.util.List;

/**
 * This class encapsulates the Command and passes the request to the command object to process it.
 */
public class CommandInvoker {
  private List<AlbumCommand> handler = new ArrayList<>();

  /**
   * Accept command.
   * @param command command objects
   */
  public void acceptCommand(AlbumCommand command) {
    handler.add(command);
  }

  /**
   * Execute command.
   */
  public void executeCommand() {
    for (AlbumCommand command: handler) {
      command.execute();
    }
  }


}
