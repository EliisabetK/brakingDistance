/***
 * Calculator with bugs injected: MR5 (doubling speed), MR3 (slope), MR6 (braking force)
 */

public class Calc1 implements StoppingDistanceCalculator {

    @Override
    public double calculateStoppingDistance(double speedKmh, double reactionTime, double frictionCoefficient, double roadGrade, double brakingForce) {
        // correct formula: s = ((0.278 * t * v) + v² / (254 * (f + G))) / B
        double denominator = 254 * (frictionCoefficient - roadGrade);

        if (Math.abs(denominator) < 1e-9) {
            return Double.POSITIVE_INFINITY;
        }
        return (0.278 * reactionTime * speedKmh + (speedKmh) / denominator) * brakingForce;
    }
}
