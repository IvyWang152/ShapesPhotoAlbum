package model;

/**
 * This enum class contains types of different shapes.
 */
public enum ShapeType {
  OVAL("oval"),
  RECTANGLE("rectangle");

  String value;
  ShapeType(String value) {
    this.value = value;
  }

}
