public class Calc4 implements StoppingDistanceCalculator {

    @Override
    public double calculateStoppingDistance(double speedKmh, double reactionTime, double frictionCoefficient, double roadGrade, double brakingForce) {
        validateInputs(speedKmh, reactionTime, frictionCoefficient, roadGrade, brakingForce);
        double denominator = 254 * (frictionCoefficient + roadGrade);
        if (Math.abs(denominator) < 1e-9 || Math.abs(brakingForce) < 1e-9) {
            return Double.POSITIVE_INFINITY;
        }
        return (0.278 / reactionTime * speedKmh + speedKmh / denominator) / brakingForce;
    }
}
