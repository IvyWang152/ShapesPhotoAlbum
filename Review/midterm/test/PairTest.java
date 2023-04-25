import pairs.Pair;
import vehicles.Car;
import vehicles.IVehicle;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.Assert.*;
class Bike {
  private double maxSpeed;
  private String name;
  private double currentSpeed;
  public Bike(double maxSpeed, String name ) {
    this.name = name;
    this.maxSpeed = maxSpeed;
    this.currentSpeed = 0;
  }
  public double getCurrentSpeed() {
    return this.currentSpeed;
  }

  public void accelerate(double delta) {
    this.currentSpeed += delta;
    // clamp it
    if(this.currentSpeed > this.maxSpeed )
      this.currentSpeed = this.maxSpeed;
    else if(this.currentSpeed <0 )
      this.currentSpeed = 0;
  }
  public void park() {
    this.currentSpeed = 0;
  }
  public boolean equals(Object o) {
    if (this == o) return true;
    if (this.getClass() != o.getClass()) return false;
    Bike other = (Bike)o;
    if(this.name.equals(other.name)
       && this.maxSpeed == other.maxSpeed
       && this.currentSpeed == other.currentSpeed )
      return true;
    return false;
  }

  public final int hashCode() {
    return Objects.hash(name, maxSpeed, currentSpeed);
  }
  public String toString() {
    return "Bike named: " + this.name
            + " max = " + this.maxSpeed
            + "curr = " + this.currentSpeed;
  }
}
public class PairTest {
  Pair<String, String> stringPair;
  Pair<String, Integer> mixedPair;
  Pair<Integer, Integer> intPair;
  Pair<String, Bike> bikePair;
  Pair<String, String> duplicate;
  Pair<String, String> nullPair;

  @org.junit.Before
  public void setUp() throws Exception {
    stringPair = new Pair<>("Hello", "World");
    mixedPair = new Pair<>("Seven", 7);
    intPair = new Pair<>(11, 7);
    bikePair = new Pair<>("Husky", new Bike(20, "Husky"));
    duplicate = new Pair<>("Hello", "World");
    nullPair = new Pair<>("null", null);
  }

  @org.junit.Test
  public void testGettersAndEquals() {
    assertEquals( "Hello", stringPair.getFirst());
    assertEquals( "World", stringPair.getSecond());
    assertEquals( "Seven", mixedPair.getFirst());
    assertEquals( new Integer(7), mixedPair.getSecond());
    assertEquals( new Integer(11), intPair.getFirst());
    assertEquals( new Integer(7), intPair.getSecond());
    assertEquals( "Husky", bikePair.getFirst());
    assertEquals("Failed object compare", new Bike(20, "Husky"), bikePair.getSecond());
    assertEquals( "null", nullPair.getFirst());
    assertNull( "Should be null", nullPair.getSecond());
    assertTrue(stringPair == stringPair);
    assertFalse(stringPair == duplicate);
    assertTrue(stringPair.equals(duplicate));
  }

  @org.junit.Test
  public void testToString() {
    // I didn't give them a String format, so just test that the method exists
    System.out.println(stringPair.toString());
  }

  @org.junit.Test
  public void testHashable() {
    Map<Pair<String, Bike>, String> map = new HashMap<>();
    map.put(bikePair, "Bikes");
    Pair<String, Bike> otherBike = new Pair<>("Husky", new Bike(20, "Husky"));
    map.put(otherBike, "Strings");
    assertEquals("hashCode() wrong or not equal", bikePair.hashCode(), otherBike.hashCode());

    Pair<String, Bike> accessIt = new Pair<>("Husky", new Bike(20, "Husky"));
    assertEquals("Strings", map.get(accessIt));

  }
}