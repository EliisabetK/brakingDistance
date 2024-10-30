/***
 * Calculator with bugs injected: MR2 (road surface) and MR5 (low speed handling)
 */

public class Calc2 implements StoppingDistanceCalculator {

    @Override
    public double calculateStoppingDistance(double speedKmh, double reactionTime, double frictionCoefficient, double roadGrade) {
        // Bug for MR2: Ignore friction coefficient
        // Bug for MR5: Added a non-zero minimum distance
        int minimumDistance = 5;
        return 0.278 * reactionTime * speedKmh + Math.pow(speedKmh, 2) / (254 * (roadGrade + 0.5)) + minimumDistance;
    }
}
