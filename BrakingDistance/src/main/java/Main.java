import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueRunning = true;

        while (continueRunning) {
            try {
                System.out.println("Select calculator:");
                System.out.println("1. Calc1");
                System.out.println("2. Calc2");
                System.out.println("3. Calc3");
                System.out.println("4. Calc4");

                int calculatorChoice = getValidIntInput(scanner, "Enter choice (1-4): ", 1, 4);

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
                        throw new IllegalArgumentException("Invalid calculator choice.");
                }

                double speedKmh = getValidDoubleInput(scanner, "Enter speed (km/h): ");
                double reactionTime = getValidDoubleInput(scanner, "Enter perception-reaction time (seconds): ");
                double roadGrade = getValidDoubleInput(scanner, "Enter road grade (decimal, e.g., -0.05 for a 5% downhill slope): ");

                System.out.println("Choose road condition:");
                System.out.println("1. Dry Road (friction coefficient ~0.7)");
                System.out.println("2. Wet Road (friction coefficient between 0.3 and 0.4)");
                System.out.println("3. Snowy Road (friction coefficient ~0.2)");
                System.out.println("4. Icy Road (friction coefficient ~0.1)");
                System.out.println("5. Gravel Road (friction coefficient ~0.5)");

                int roadCondition = getValidIntInput(scanner, "Select option (1-5): ", 1, 5);
                double frictionCoefficient = getFrictionCoefficient(roadCondition);
                double brakingForce = getValidDoubleInput(scanner, "Enter braking force (0 is not braking, 1 is pressing the brake to the floor): ");
                double stoppingDistance = calculator.calculateStoppingDistance(speedKmh, reactionTime, frictionCoefficient, roadGrade, brakingForce);
                System.out.printf("The stopping distance is: %.2f meters%n", stoppingDistance);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }

            System.out.print("Do you want to calculate again? (yes/no): ");
            String choice = scanner.next().trim().toLowerCase();
            if (!choice.equals("yes") && !choice.equals("y")) {
                continueRunning = false;
            }
        }

        System.out.println("Goodbye!");
        scanner.close();
    }

    private static double getFrictionCoefficient(int condition) {
        switch (condition) {
            case 1:
                return 0.7;
            case 2:
                return 0.35;
            case 3:
                return 0.2;
            case 4:
                return 0.1;
            case 5:
                return 0.5;
            default:
                throw new IllegalArgumentException("Invalid road condition.");
        }
    }

    private static int getValidIntInput(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.println("Invalid input. Please enter a number between " + min + " and " + max + ".");
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next();
            }
        }
    }

    private static double getValidDoubleInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                return scanner.nextDouble();
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next();
            }
        }
    }
}
