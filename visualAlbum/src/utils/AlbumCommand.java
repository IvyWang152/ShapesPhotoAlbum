package utils;

/**
 * This interface declares operations for all concrete commands.
 */
public interface AlbumCommand {

  /**
   * Ask all concrete commands to provide their own implementations.
   */
  void execute();
}
