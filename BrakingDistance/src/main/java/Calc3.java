/***
 * Correct calculator, no bugs injected.
 * formula from here: https://www.omnicalculator.com/physics/stopping-distance
 */

public class Calc3 implements StoppingDistanceCalculator {

    @Override
    public double calculateStoppingDistance(double speedKmh, double reactionTime, double frictionCoefficient, double roadGrade) {
        // stopping distance formula: s = (0.278 * t * v) + v² / (254 * (f + G))
        return  0.278 * reactionTime * speedKmh + Math.pow(speedKmh, 2) / (254 * (frictionCoefficient + roadGrade));
    }
}
