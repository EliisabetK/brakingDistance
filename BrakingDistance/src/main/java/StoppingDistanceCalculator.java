public interface StoppingDistanceCalculator {

    double calculateStoppingDistance(double speedKmh, double reactionTime, double frictionCoefficient, double roadGrade, double brakingForce);

    default void validateInputs(double speedKmh, double reactionTime, double frictionCoefficient, double roadGrade, double brakingForce) {
        if (speedKmh < 0) {
            throw new IllegalArgumentException("Speed must be a non-negative value.");
        }
        if (reactionTime < 0) {
            throw new IllegalArgumentException("Reaction time must be a non-negative value.");
        }
        if (frictionCoefficient < 0 || frictionCoefficient > 1) {
            throw new IllegalArgumentException("Friction coefficient must be between 0 and 1.");
        }
        if (roadGrade < -1 || roadGrade > 1) {
            throw new IllegalArgumentException("Road grade must be between -1.0 and 1.0.");
        }
        if (brakingForce < 0 || brakingForce > 1) {
            throw new IllegalArgumentException("Braking force must be between 0 and 1.");
        }
    }
}
