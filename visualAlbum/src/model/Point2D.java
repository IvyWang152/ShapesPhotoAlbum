package model;

import java.util.Objects;

/**
 * This class represents a point in 2D. It has x coordinate and y coordinate.
 */
public class Point2D {
  private double x;
  private double y;

  /**
   * Constructor.
   * @param x x coordinate of the point
   * @param y y coordinate of the point
   */
  public Point2D(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double getX() {
    return this.x;
  }

  public double getY() {
    return this.y;
  }

  /**
   * Get a string representation of the point.
   * @return a string representation of the point
   */
  public String toString() {
    return "(" + x + "," + y + ")";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o.getClass() != this.getClass()) return false;
    Point2D point2D = (Point2D) o;
    return Double.compare(point2D.getX(), getX()) == 0
        && Double.compare(point2D.getY(), getY()) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(getX(), getY());
  }
}
