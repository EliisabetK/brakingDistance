/***
 * Buggy calculator: MR2 (Reaction time handling) and MR4 (Speed)
 */

public class Calc4 implements StoppingDistanceCalculator {

    @Override
    public double calculateStoppingDistance(double speedKmh, double reactionTime, double frictionCoefficient, double roadGrade, double brakingForce) {
        if (Math.abs(speedKmh) < 1e-9 || Math.abs(reactionTime) < 1e-9 || Math.abs(brakingForce) < 1e-9) {
            return Double.POSITIVE_INFINITY;
        }

        double denominator = 254 * (frictionCoefficient + roadGrade);
        if (Math.abs(denominator) < 1e-9) {
            return Double.POSITIVE_INFINITY;
        }

        return ((0.278 / reactionTime) *(50*(1/speedKmh)) + Math.pow(5000*(1/speedKmh), 2) / denominator) / brakingForce;
    }
}
