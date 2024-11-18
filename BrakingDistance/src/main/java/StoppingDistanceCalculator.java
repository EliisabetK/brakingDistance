public interface StoppingDistanceCalculator {
    double calculateStoppingDistance(double speedKmh, double reactionTime, double frictionCoefficient, double roadGrade, double brakingForce);
}
