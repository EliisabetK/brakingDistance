import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class StudentTestSuite {

    private final StoppingDistanceCalculator calculator;

    public StudentTestSuite(StoppingDistanceCalculator calculator, String description) {
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
     * MR1 Test: Road surface change
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

    // Add more tests here:
}
