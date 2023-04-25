import org.junit.Assert;
import org.junit.Test;

import utility.DistanceConverter;

import static org.junit.Assert.*;

public class DistanceConverterTest {

  @Test
  public void testToKilometers() {
    Assert.assertEquals("Wrong value to KM", 0, DistanceConverter.toKilometers(0), 0.001);
    assertEquals("Wrong value to KM", 1.609, DistanceConverter.toKilometers(1), 0.001);
    assertEquals("Wrong value to KM", 1.609, DistanceConverter.toKilometers(-1), 0.001);
    assertEquals("Wrong value to KM", 32180.804, DistanceConverter.toKilometers(20000.5), 0.001);
    assertEquals("Wrong value to KM", 0.0321, DistanceConverter.toKilometers(0.02), 0.001);
  }

  @Test
  public void testToMiles() {
    assertEquals("Wrong value to Miles", 0, DistanceConverter.toMiles(0), 0.001);
    assertEquals("Wrong value to Miles", 0.621, DistanceConverter.toMiles(1), 0.001);
    assertEquals("Wrong value to Miles", 0.621, DistanceConverter.toMiles(-1), 0.001);
    assertEquals("Wrong value to Miles", 12429.937, DistanceConverter.toMiles(19999.9), 0.001);
  }

  @Test
  public void testToFathoms() {
    assertEquals("Wrong value to Fathoms", 0, DistanceConverter.toFathoms(0), 0.001);
    assertEquals("Wrong value to Fathoms", 880, DistanceConverter.toFathoms(1), 0.001);
    assertEquals("Wrong value to Fathoms", 880, DistanceConverter.toFathoms(-1), 0.001);
    assertEquals("Wrong value to Fathoms", 10560704, DistanceConverter.toFathoms(12000.8), 0.001);
  }

  @Test
  public void testToLeagues() {
    assertEquals("Wrong value to Leagues", 0, DistanceConverter.toLeagues(0), 0.001);
    assertEquals("Wrong value to Leagues", 3, DistanceConverter.toLeagues(1), 0.001);
    assertEquals("Wrong value to Leagues", 3, DistanceConverter.toLeagues(-1), 0.001);
    assertEquals("Wrong value to Leagues", 30000, DistanceConverter.toLeagues(10000), 0.001);
  }
}