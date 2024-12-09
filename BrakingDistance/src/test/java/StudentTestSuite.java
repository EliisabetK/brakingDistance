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
        double frictionCoefficient = 0.7;
        double c = 0.3;

        double stoppingDistance1 = calculator.calculateStoppingDistance(speed, reactionTime, frictionCoefficient, roadGrade, brakingForce);
        double stoppingDistance2 = calculator.calculateStoppingDistance(speed, reactionTime, frictionCoefficient + c, roadGrade, brakingForce);

        assertTrue("MR1 failed: Lower friction did not increase stopping distance for " + calculator.getClass().getSimpleName(),
                stoppingDistance1 > stoppingDistance2);
    }

    // Add more tests here:

}
