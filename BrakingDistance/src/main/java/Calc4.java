/***
 * Buggy calculator: MR4. Reaction time handling and MR3. Slope
 */

public class Calc4 implements StoppingDistanceCalculator {

    @Override
    public double calculateStoppingDistance(double speedKmh, double reactionTime, double frictionCoefficient, double roadGrade) {
        // correct stopping distance formula: s = (0.278 * t * v) + v² / (254 * (f + G))
        return  0.278 / reactionTime * speedKmh + Math.pow(speedKmh, 2) / (254 * (frictionCoefficient + 0));
    }
}
