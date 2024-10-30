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
                {new Calc3(), "Calc3"}
        });
    }

    /**
     * MR1 Test: Doubling speed
     * When speed doubles, the stopping distance should approximately quadruple.
     */
    @Test
    public void testApproximateDoublingSpeed() {
        double reactionTime = 1.5;
        double frictionCoefficient = 0.7;
        double roadGrade = 0.0;

        double speed1 = 500; // The speed has to be high, otherwise the linear component makes the result not quadrupled
        double stoppingDistance1 = calculator.calculateStoppingDistance(speed1, reactionTime, frictionCoefficient, roadGrade);

        double speed2 = 2 * speed1;
        double stoppingDistance2 = calculator.calculateStoppingDistance(speed2, reactionTime, frictionCoefficient, roadGrade);

        assertTrue("MR1 failed: Doubling speed did not approximately quadruple stopping distance for " + calculator.getClass().getSimpleName(),
                Math.abs(stoppingDistance2 - (4 * stoppingDistance1)) / (4 * stoppingDistance1) < 0.1); // Within 10% margin
    }

    /**
     * MR3 Test: Slope effect
     * Downhill slopes should increase stopping distance, and uphill slopes should decrease it
     */
    @Test
    public void testSlopeEffect() {
        double speed = 60;
        double reactionTime = 1.5;
        double frictionCoefficient = 0.7;

        double roadGradeFlat = 0.0;
        double stoppingDistanceFlat = calculator.calculateStoppingDistance(speed, reactionTime, frictionCoefficient, roadGradeFlat);

        double roadGradeDownhill = -0.1;
        double stoppingDistanceDownhill = calculator.calculateStoppingDistance(speed, reactionTime, frictionCoefficient, roadGradeDownhill);

        double roadGradeUphill = 0.1;
        double stoppingDistanceUphill = calculator.calculateStoppingDistance(speed, reactionTime, frictionCoefficient, roadGradeUphill);

        assertTrue("MR3 failed: Downhill slope did not increase stopping distance for " + calculator.getClass().getSimpleName(),
                stoppingDistanceDownhill > stoppingDistanceFlat);

        assertTrue("MR3 failed: Uphill slope did not decrease stopping distance for " + calculator.getClass().getSimpleName(),
                stoppingDistanceUphill < stoppingDistanceFlat);
    }

    /**
     * MR4 Test: Road surface change
     * Changing from a high-friction road to a low-friction road should increase the stopping distance.
     */
    @Test
    public void testRoadSurfaceChange() {
        double speed = 60;
        double reactionTime = 1.5;
        double roadGrade = 0.0;

        double frictionCoefficientHigh = 0.7;
        double stoppingDistanceHigh = calculator.calculateStoppingDistance(speed, reactionTime, frictionCoefficientHigh, roadGrade);

        double frictionCoefficientLow = 0.1;
        double stoppingDistanceLow = calculator.calculateStoppingDistance(speed, reactionTime, frictionCoefficientLow, roadGrade);

        assertTrue("MR4 failed: Lower friction did not increase stopping distance for " + calculator.getClass().getSimpleName(),
                stoppingDistanceLow > stoppingDistanceHigh);
    }

    /**
     * MR5 Test: Low-speed handling
     * When speed is near zero, the stopping distance should also approach zero.
     */
    @Test
    public void testLowSpeedHandling() {
        double speed = 0.1; // Very low speed (close to zero)
        double reactionTime = 1.5;
        double frictionCoefficient = 0.7;
        double roadGrade = 0.0;

        double stoppingDistance = calculator.calculateStoppingDistance(speed, reactionTime, frictionCoefficient, roadGrade);

        assertTrue("MR5 failed: Low speed did not result in near-zero stopping distance for " + calculator.getClass().getSimpleName(),
                stoppingDistance < 1.0); // Expecting stopping distance to be close to zero
    }
}
