package utility;

import java.util.Scanner;

public class ValidationUtil {
    private static final Scanner scanner = new Scanner(System.in);

    public static double getValidatedDouble(String prompt) {
        double value;
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                value = scanner.nextDouble();
                if (value > 0) break;
                System.out.println("Please enter a positive value...");
            } else {
                System.out.println("\nInvalid input. Please enter a number...");
                scanner.next(); // Clear invalid input
            }
        }
        return value;
    }

    public static int getValidatedInt(String prompt, int min, int max) {
        int value;
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                value = scanner.nextInt();
                if (value >= min && value <= max) break;
                System.out.println("Please enter a value between " + min + " and " + max + ".");
            } else {
                System.out.println("\nInvalid input. Please enter an integer!!!");
                scanner.next(); // Clear invalid input
            }
        }
        return value;
    }
}
