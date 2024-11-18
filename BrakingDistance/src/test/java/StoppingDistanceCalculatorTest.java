import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class StoppingDistanceCalculatorTest {

    private final StoppingDistanceCalculator calculator;

    public StoppingDistanceCalculatorTest(StoppingDistanceCalculator calculator, String description) {
        this.calculator = calculator;
    }

    @Parameterized.Parameters(name = "{1}")
    public static Collection<Object[]> calculators() {
        return Arrays.asList(new Object[][]{
                {new Calc1(), "Calc1"},
                {new Calc2(), "Calc2"},
                {new Calc3(), "Calc3"},
                {new Calc4(), "Calc4"}
        });
    }

    /**
     * MR1 Test: Doubling speed
     * When speed doubles, the stopping distance should approximately quadruple.
     */
    @Test
    public void testMR5() {
        double reactionTime = 1.5;
        double frictionCoefficient = 0.7;
        double roadGrade = 0.0;
        double brakingForce = 1.0;

        double speed1 = 500;
        double stoppingDistance1 = calculator.calculateStoppingDistance(speed1, reactionTime, frictionCoefficient, roadGrade, brakingForce);

        double speed2 = 2 * speed1;
        double stoppingDistance2 = calculator.calculateStoppingDistance(speed2, reactionTime, frictionCoefficient, roadGrade, brakingForce);

        assertTrue("MR5 failed: Doubling speed did not approximately quadruple stopping distance for " + calculator.getClass().getSimpleName(),
                Math.abs(stoppingDistance2 - (4 * stoppingDistance1)) / (4 * stoppingDistance1) < 0.1);
    }

    /**
     * MR3 Test: Slope effect
     * Downhill slopes should increase stopping distance, and uphill slopes should decrease it
     */
    @Test
    public void testMR3() {
        double speed = 60;
        double reactionTime = 1.5;
        double frictionCoefficient = 0.7;
        double brakingForce = 1.0;

        double roadGradeFlat = 0.0;
        double stoppingDistanceFlat = calculator.calculateStoppingDistance(speed, reactionTime, frictionCoefficient, roadGradeFlat, brakingForce);

        double roadGradeDownhill = -0.1;
        double stoppingDistanceDownhill = calculator.calculateStoppingDistance(speed, reactionTime, frictionCoefficient, roadGradeDownhill, brakingForce);

        double roadGradeUphill = 0.1;
        double stoppingDistanceUphill = calculator.calculateStoppingDistance(speed, reactionTime, frictionCoefficient, roadGradeUphill, brakingForce);

        assertTrue("MR3 failed: Downhill slope did not increase stopping distance for " + calculator.getClass().getSimpleName(),
                stoppingDistanceDownhill > stoppingDistanceFlat);

        assertTrue("MR3 failed: Uphill slope did not decrease stopping distance for " + calculator.getClass().getSimpleName(),
                stoppingDistanceUphill < stoppingDistanceFlat);
    }

    /**
     * MR2 Test: Road surface change
     * Changing from a high-friction road to a low-friction road should increase the stopping distance.
     */
    @Test
    public void testMR1() {
        double speed = 60;
        double reactionTime = 1.5;
        double roadGrade = 0.0;
        double brakingForce = 1.0;

        double frictionCoefficientHigh = 0.7;
        double stoppingDistanceHigh = calculator.calculateStoppingDistance(speed, reactionTime, frictionCoefficientHigh, roadGrade, brakingForce);

        double frictionCoefficientLow = 0.1;
        double stoppingDistanceLow = calculator.calculateStoppingDistance(speed, reactionTime, frictionCoefficientLow, roadGrade, brakingForce);

        assertTrue("MR1 failed: Lower friction did not increase stopping distance for " + calculator.getClass().getSimpleName(),
                stoppingDistanceLow > stoppingDistanceHigh);
    }

    /**
     * MR4 Test: Reaction time
     * Faster reaction time should decrease distance
     */
    @Test
    public void testMR2() {
        double speed = 120;
        double reactionTime = 0.5;
        double reactionTime2 = 2.5;
        double roadGrade = 0.0;
        double frictionCoefficient = 0.7;
        double brakingForce = 1.0;

        double stoppingDistanceFast = calculator.calculateStoppingDistance(speed, reactionTime, frictionCoefficient, roadGrade, brakingForce);
        double stoppingDistanceSlow = calculator.calculateStoppingDistance(speed, reactionTime2, frictionCoefficient, roadGrade, brakingForce);

        assertTrue("MR2 failed: Lower reaction time did not decrease stopping distance " + calculator.getClass().getSimpleName(),
                stoppingDistanceSlow > stoppingDistanceFast);
    }

    /**
     * MR5 Test: Speed change handling
     * When increases, the braking distance should increase
     */
    @Test
    public void testMR4() {
        double speed = 60;
        double reactionTime = 1.5;
        double frictionCoefficient = 0.7;
        double roadGrade = 0.0;
        double brakingForce = 1.0;
        double speed2 = 75;

        double stoppingDistance = calculator.calculateStoppingDistance(speed, reactionTime, frictionCoefficient, roadGrade, brakingForce);
        double stoppingDistance2 = calculator.calculateStoppingDistance(speed2, reactionTime, frictionCoefficient, roadGrade, brakingForce);

        assertTrue("MR4 failed: Higher speed did not increase the braking distance for: " + calculator.getClass().getSimpleName(),
                stoppingDistance < stoppingDistance2);
    }

    @Test
    public void testMR6() {
        double speed = 60;
        double reactionTime = 1.5;
        double frictionCoefficient = 0.7;
        double roadGrade = 0.0;
        double brakingForce = 0.8;
        double brakingForce2 = 0.3;

        double stoppingDistance = calculator.calculateStoppingDistance(speed, reactionTime, frictionCoefficient, roadGrade, brakingForce);
        double stoppingDistance2 = calculator.calculateStoppingDistance(speed, reactionTime, frictionCoefficient, roadGrade, brakingForce2);

        assertTrue("MR6 failed: Smaller braking force did not decrease the distance for: " + calculator.getClass().getSimpleName(),
                stoppingDistance < stoppingDistance2);
    }
}
