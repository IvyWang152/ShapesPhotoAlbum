import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import vehicles.IFlyingVehicle;
import vehicles.IVehicle;
import vehicles.Plane;
public class PlaneTest {

  IFlyingVehicle learjet;
  IVehicle vehicle;

  @Before
  public void setUp() throws Exception {
    learjet = new Plane(350);
    vehicle = learjet;
  }

  @Test
  public void takeOff() {
  }

  @Test
  public void testSpeedAndAcceleration() {
    assertTrue(Double.compare(0 ,vehicle.getCurrentSpeed()) == 0);
    vehicle.accelerate(450); // vehicle IS the Plane
    assertTrue(Double.compare(350 ,vehicle.getCurrentSpeed()) == 0);
    vehicle.accelerate(-349.9);
    assertEquals("Wrong value after plane decelerate",0.1 ,vehicle.getCurrentSpeed(), 0.001);
    vehicle.accelerate(-119.9);
    assertTrue(Double.compare(0 ,vehicle.getCurrentSpeed()) == 0);
  }
  @Test
  public void inTheAirAndPark() {
    assertFalse(learjet.inTheAir());
    learjet.accelerate(100);
    learjet.takeOff();
    assertFalse(learjet.inTheAir());
    learjet.accelerate(0.01);
    learjet.takeOff();
    assertTrue(learjet.inTheAir());
    // vehicle points to the learjet

    vehicle.park(); // should ignore this

    assertEquals("Wrong value after plane takes off",100.01 ,vehicle.getCurrentSpeed(), 0.001);
    learjet.land();
    assertEquals("Wrong value after plane lands",10.0 ,vehicle.getCurrentSpeed(), 0.001);
    learjet.park();
    assertTrue(Double.compare(0 ,learjet.getCurrentSpeed()) == 0);
  }

  // NB: Spec wasn't clear on what happens if we are in the air and decelerate to 0 mph. That would be another test to consider
  // writing to "breaking the code" by testing various state-changes :-)
  // But, this was an exam, not a homework :-)

  // Also realized I intended for students to CLAMP the values if they input to accelerate was out-of-band, but
  // wasn't specific so some people are throwing exceptions. We are counting either approach as correct. The JUnit test
  // will fail, but the rubric has a manual adjustment so no one is penalized for using exception handling rather than clamping.
}