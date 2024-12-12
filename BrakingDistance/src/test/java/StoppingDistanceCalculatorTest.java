import static java.lang.Math.abs;
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

    /**
     * MR2 Test: Reaction time change
     */
    @Test
    public void testMR2() {
        double speed = 60;
        double reactionTime = 1.5;
        double roadGrade = 0.0;
        double brakingForce = 1.0;
        double frictionCoefficient = 0.7;
        double c = 0.5;

        double stoppingDistance1 = calculator.calculateStoppingDistance(speed, reactionTime, frictionCoefficient, roadGrade, brakingForce);
        double stoppingDistance2 = calculator.calculateStoppingDistance(speed, reactionTime + c, frictionCoefficient, roadGrade, brakingForce);

        assertTrue("MR2 failed: Higher reaction time did not increase stopping distance for " + calculator.getClass().getSimpleName(),
                stoppingDistance1 < stoppingDistance2);
    }

    @Test
    public void testMR3() {
        double speed = 100;
        double reactionTime = 1.5;
        double roadGrade = 0.0;
        double brakingForce = 1.0;
        double frictionCoefficient = 0.7;
        double c = 2;

        double stoppingDistance1 = calculator.calculateStoppingDistance(speed, reactionTime, frictionCoefficient, roadGrade, brakingForce);
        double stoppingDistance2 = calculator.calculateStoppingDistance(speed*2, reactionTime, frictionCoefficient, roadGrade, brakingForce);
        System.out.println(stoppingDistance2+ " "+stoppingDistance1);
        assertTrue("MR4 failed: Doubling speed did not approximately quadruple the distance " + calculator.getClass().getSimpleName(),
                abs(stoppingDistance2/stoppingDistance1 - c*c ) < 1);
    }

    @Test
    public void testMR4() {
        double speed = 60;
        double reactionTime = 1.5;
        double roadGrade = 0.0;
        double brakingForce = 1.0;
        double frictionCoefficient = 0.7;
        double c = 0.5;

        double stoppingDistance1 = calculator.calculateStoppingDistance(speed, reactionTime, frictionCoefficient, roadGrade, brakingForce);
        double stoppingDistance2 = calculator.calculateStoppingDistance(speed, reactionTime, frictionCoefficient, roadGrade+c, brakingForce);

        double stoppingDistance3 = calculator.calculateStoppingDistance(speed, reactionTime, frictionCoefficient, roadGrade, brakingForce);
        double stoppingDistance4 = calculator.calculateStoppingDistance(speed, reactionTime, frictionCoefficient, roadGrade-c, brakingForce);

        assertTrue("MR5 failed: Higher slope time did not decrease stopping distance for " + calculator.getClass().getSimpleName(),
                stoppingDistance1 > stoppingDistance2);
        assertTrue("MR5 failed: Downhill did not increase stopping distance for " + calculator.getClass().getSimpleName(),
                stoppingDistance3 < stoppingDistance4);
    }

    @Test
    public void testMR5() {
        double speed = 60;
        double reactionTime = 1.5;
        double roadGrade = 0.0;
        double brakingForce = 1.0;
        double frictionCoefficient = 0.7;
        double c = -0.5;

        double stoppingDistance1 = calculator.calculateStoppingDistance(speed, reactionTime, frictionCoefficient, roadGrade, brakingForce);
        double stoppingDistance2 = calculator.calculateStoppingDistance(speed, reactionTime, frictionCoefficient, roadGrade, brakingForce+c);

        assertTrue("MR6 failed:Lower braking force did not increase stopping distance for " + calculator.getClass().getSimpleName(),
                stoppingDistance1 < stoppingDistance2);
    }
}
