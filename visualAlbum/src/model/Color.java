package model;

public class Color extends java.awt.Color {

  public Color(int r, int g, int b) {
    super(r,g,b);
  }

  public String toString() {

    return "(" + super.getRed() +"," + super.getGreen() +","
        + super.getBlue() + ")";
  }

}
