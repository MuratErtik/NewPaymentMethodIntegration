import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        printWelcome();

        boolean running = true;

        while (running) {
            printMainMenu();
            int choice = readIntSafely("Enter your choice: ");

            switch (choice) {
                case 1 -> System.out.println("1");
                case 2 -> System.out.println("2");
                case 3 -> System.out.println("3");
                case 4 -> {
                    System.out.println("\n  Thank you for using Payment App. Goodbye!");
                    running = false;
                }
                default -> System.out.println("\n  Invalid choice. Please enter a number between 1 and 4");
            }
        }

        scanner.close();

    }




    private static void printWelcome() {

        System.out.println("***************************************************************************");
        System.out.println("*                Welcome! Please choose a payment method.                 *");
        System.out.println("***************************************************************************");
    }

    private static void printMainMenu() {
        System.out.println("***************************************************************************");
        System.out.println("*  MAIN MENU");
        System.out.println("***************************************************************************");

        System.out.println("*  1. Pay with Credit Card");
        System.out.println("*  2. Pay with PayPal");
        System.out.println("*  3. Pay with Apple Pay");
        System.out.println("*  4. Exit");
        System.out.println("***************************************************************************");

    }

    private static int readIntSafely(String choice) {
        System.out.print("your choice: "+choice);
        try {
            int value = scanner.nextInt();
            scanner.nextLine(); // clear the newline character
            return value;
        } catch (InputMismatchException e) {
            scanner.nextLine(); // clear bad input
            return -1;
        }
    }
}
