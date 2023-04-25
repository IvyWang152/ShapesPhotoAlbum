import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import vehicles.IVehicle;
import vehicles.Car;

// I intended for students to CLAMP the values if they input to accelerate was out-of-band, but
// wasn't specific so some people are throwing exceptions. We are counting either approach as correct. The JUnit test
// will fail, but the rubric has a manual adjustment so no one is penalized for using exception handling rather than clamping.
public class CarTest {
  private IVehicle car;
  private IVehicle sportsCar;
  private IVehicle hooptie;


  @Before
  public void setUp() throws Exception {
    car = new Car(120);
    sportsCar = new Car(140);
    hooptie = new Car(75);
  }

  @Test
  public void testSpeedAndAcceleration() {
    assertTrue(Double.compare(0 ,car.getCurrentSpeed()) == 0);
    car.accelerate(150);
    assertTrue(Double.compare(120 ,car.getCurrentSpeed()) == 0);
    car.accelerate(-119.9);
    assertEquals("Wrong value after decelerate",0.1 ,car.getCurrentSpeed(), 0.001);
    car.accelerate(-119.9);
    assertTrue(Double.compare(0 ,car.getCurrentSpeed()) == 0);
    hooptie.accelerate(-0.01);
    assertTrue(Double.compare(0 ,hooptie.getCurrentSpeed()) == 0);
    sportsCar.accelerate(140.01);
    assertEquals("Wrong value after acceleration",140.0 ,sportsCar.getCurrentSpeed(), 0.001);
  }


  @Test
  public void testPark() {
    car.accelerate(80);
    car.park();
    assertTrue(Double.compare(0 ,car.getCurrentSpeed()) == 0);

    hooptie.park();
    assertTrue(Double.compare(0 ,hooptie.getCurrentSpeed()) == 0);

  }
}