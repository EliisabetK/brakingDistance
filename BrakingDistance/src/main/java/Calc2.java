/***
 * Calculator with bugs injected: MR1 (road surface)
 */

public class Calc2 implements StoppingDistanceCalculator {

    @Override
    public double calculateStoppingDistance(double speedKmh, double reactionTime, double frictionCoefficient, double roadGrade, double brakingForce) {
        // Bug for MR1: Ignore friction coefficient, always set as 0.5
        // s =  (0.278 × t × v) + v² / (254 × (0.5 + G)) / B;

        double denominator = 254 * (0.5 + roadGrade);
        if (Math.abs(denominator) < 1e-9) {
            return Double.POSITIVE_INFINITY;
        }

        return (0.278 * reactionTime * speedKmh + Math.pow(speedKmh, 2) / denominator) / brakingForce;
    }
}
