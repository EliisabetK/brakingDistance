/***
 * Calculator with bugs injected: MR1 (doubling speed) and MR3 (slope)
 */

public class Calc1 implements StoppingDistanceCalculator {

    @Override
    public double calculateStoppingDistance(double speedKmh, double reactionTime, double frictionCoefficient, double roadGrade) {
        // Bug for MR1: Scale braking distance linearly with speed instead of quadratically
        // Bug for MR3: Subtract slope instead of adding it
        // s = (0.278 * t * v) + v² / (254 * (f + G))
        return 0.278 * reactionTime * speedKmh + (speedKmh) / (254 * (frictionCoefficient - roadGrade));
    }
}

