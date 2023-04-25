package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents a file handler. It can parse input file.
 */
public class FileHandler {
  private String fileName;
  private List<List<String>> out = new ArrayList<>();

  /**
   * Constructor.
   * @param fileName name of the command file
   */
  public FileHandler(String fileName) {
    this.fileName = fileName;
  }

  /**
   * Parse command file.
   * @return a nested list of string
   */
  public List<List<String>> parseFile() {
    try {
      FileReader in = new FileReader(fileName);
      BufferedReader myReader = new BufferedReader(in);

      String line = myReader.readLine();
      while (line != null) {
        if (!line.contains("#") && !line.isEmpty()) {
          List<String> command = Arrays.stream(line.trim().split("\\s+")).toList();
          out.add(command);
          System.out.println(command);
        }
        line = myReader.readLine();
      }
      myReader.close();
      //return out;
    } catch (IOException e) {
      System.err.println("There no such file called " + fileName);
      System.exit(1);
      //throw new RuntimeException(e);
    }
    return out;

  }


}