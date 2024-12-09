public class Calc2 implements StoppingDistanceCalculator {

    @Override
    public double calculateStoppingDistance(double speedKmh, double reactionTime, double frictionCoefficient, double roadGrade, double brakingForce) {
        validateInputs(speedKmh, reactionTime, frictionCoefficient, roadGrade, brakingForce);
        double denominator = 254 * (0.5 + roadGrade);
        if (Math.abs(denominator) < 1e-9) {
            return Double.POSITIVE_INFINITY;
        }
        return (0.278 * reactionTime * speedKmh + Math.pow(speedKmh, 2) / denominator) / brakingForce;
    }
}
