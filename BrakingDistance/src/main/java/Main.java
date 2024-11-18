import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueRunning = true;

        while (continueRunning) {
            System.out.println("Select calculator:");
            System.out.println("1. Calc1");
            System.out.println("2. Calc2");
            System.out.println("3. Calc3");
            System.out.println("4. Calc4");
            System.out.print("Enter choice (1-4): ");
            int calculatorChoice = scanner.nextInt();

            StoppingDistanceCalculator calculator;

            switch (calculatorChoice) {
                case 1:
                    calculator = new Calc1();
                    break;
                case 2:
                    calculator = new Calc2();
                    break;
                case 3:
                    calculator = new Calc3();
                    break;
                case 4:
                    calculator = new Calc4();
                    break;
                default:
                    System.out.println("Invalid choice, defaulting to Calc1.");
                    calculator = new Calc1();
            }

            System.out.print("Enter speed (km/h): ");
            double speedKmh = scanner.nextDouble();

            System.out.print("Enter perception-reaction time (seconds): ");
            double reactionTime = scanner.nextDouble();

            System.out.print("Enter road grade (decimal, e.g., -0.05 for a 5% downhill slope): ");
            double roadGrade = scanner.nextDouble();

            System.out.println("Choose road condition:");
            System.out.println("1. Dry Road (friction coefficient ~0.7)");
            System.out.println("2. Wet Road (friction coefficient between 0.3 and 0.4)");
            System.out.println("3. Snowy Road (friction coefficient ~0.2)");
            System.out.println("4. Icy Road (friction coefficient ~0.1)");
            System.out.println("5. Gravel Road (friction coefficient ~0.5)");
            System.out.print("Select option (1-5): ");
            int roadCondition = scanner.nextInt();
            double frictionCoefficient = getFrictionCoefficient(roadCondition);

            System.out.print("Enter braking force (0 is not braking, 1 is pressing the brake to the floor): ");
            double brakingForce = scanner.nextDouble();

            double stoppingDistance = calculator.calculateStoppingDistance(speedKmh, reactionTime, frictionCoefficient, roadGrade, brakingForce);

            System.out.printf("The stopping distance is: %.2f meters%n", stoppingDistance);

            System.out.println("Do you want to calculate again? (yes/no)");
            String choice = scanner.next().toLowerCase();
            if (!choice.equals("yes")) {
                continueRunning = false;
            }
        }

        System.out.println("Goodbye!");
        scanner.close();
    }

    private static double getFrictionCoefficient(int condition) {
        switch (condition) {
            case 1:
                return 0.7; // Dry road
            case 2:
                return 0.35; // Wet road
            case 3:
                return 0.2; // Snowy road
            case 4:
                return 0.1; // Icy road
            case 5:
                return 0.5; // Gravel road
            default:
                System.out.println("Invalid choice, assuming Dry Road.");
                return 0.7;
        }
    }
}
