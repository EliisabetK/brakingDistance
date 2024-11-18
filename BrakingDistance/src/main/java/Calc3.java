/***
 * Correct calculator, no bugs injected.
 * formula from here: https://www.omnicalculator.com/physics/stopping-distance
 */

public class Calc3 implements StoppingDistanceCalculator {

    @Override
    public double calculateStoppingDistance(double speedKmh, double reactionTime, double frictionCoefficient, double roadGrade, double brakingForce) {
        double denominator = 254 * (frictionCoefficient + roadGrade);
        if (Math.abs(denominator) < 1e-9 || Math.abs(brakingForce) < 1e-9) {
            return Double.POSITIVE_INFINITY;
        }
        return (0.278 * reactionTime * speedKmh + Math.pow(speedKmh, 2) / denominator) / brakingForce;
    }
}
