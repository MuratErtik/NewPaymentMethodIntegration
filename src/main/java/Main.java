import exceptions.PaymentException;
import payments.impl.ApplePayPayment;
import payments.impl.CreditCardPayment;
import payments.impl.PayPalPayment;
import payments.PaymentMethod;

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
                case 1 -> handleCreditCardPayment();
                case 2 -> handlePayPalPayment();
                case 3 -> handleApplePayPayment();
                case 4 -> {
                    System.out.println("\n  Thank you for using Payment App. Goodbye!");
                    running = false;
                }
                default -> System.out.println("\n  Invalid choice. Please enter a number between 1 and 4.");
            }
        }

        scanner.close();
    }



    private static void handleCreditCardPayment() {
        System.out.println("***************************************************************************");
        System.out.println("*                   CREDIT CARD PAYMENT                                 ");
        System.out.println("***************************************************************************");

        String cardNumber = readStringSafely("Card Number: ");
        if (cardNumber.isBlank()) {
            System.out.println("  Card number cannot be empty. Returning to menu.");
            return;
        }

        String cardHolder = readStringSafely("Card Holder Name: ");
        if (cardHolder.isBlank()) {
            System.out.println("  Card holder name cannot be empty. Returning to menu.");
            return;
        }

        String expiryDate = readStringSafely("Expiry Date (MM/YY): ");
        if (expiryDate.isBlank()) {
            System.out.println("  Expiry date cannot be empty. Returning to menu.");
            return;
        }

        // Ask for amount
        double amount = readDoubleSafely("Amount: ");

        if (amount < 0) return; // user failed to enter a valid amount

        // Create payment method and process
        PaymentMethod creditCard = new CreditCardPayment(cardNumber, cardHolder, expiryDate);
        executePayment(creditCard, amount);
    }

    private static void handlePayPalPayment() {
        System.out.println("***************************************************************************");
        System.out.println("*                       PAYPAL PAYMENT                                      ");
        System.out.println("***************************************************************************");

        String email = readStringSafely("PayPal Email: ");
        if (email.isBlank()) {
            System.out.println("  Email cannot be empty. Returning to menu.");
            return;
        }

        double amount = readDoubleSafely("Amount: ");
        if (amount < 0) return;

        PaymentMethod payPal = new PayPalPayment(email);
        executePayment(payPal, amount);
    }

    private static void handleApplePayPayment() {
        System.out.println("***************************************************************************");
        System.out.println("*                           APPLE PAY PAYMENT                                  ");
        System.out.println("***************************************************************************");

        String appleId = readStringSafely("Apple ID (Email): ");
        if (appleId.isBlank()) {
            System.out.println("Apple ID cannot be empty. Returning to menu.");
            return;
        }

        double amount = readDoubleSafely("Amount: ");
        if (amount < 0) return;

        PaymentMethod applePay = new ApplePayPayment(appleId);
        executePayment(applePay, amount);
    }




    private static void executePayment(PaymentMethod method, double amount) {
        try {
            method.pay(amount);
            System.out.println("***************************************************************************");
            System.out.println("  Payment completed successfully!");
            System.out.println("***************************************************************************");
        } catch (PaymentException e) {
            System.out.println("***************************************************************************");
            System.out.println("  PAYMENT FAILED: " + e.getMessage());
            System.out.println("***************************************************************************");
        }
    }



    private static int readIntSafely(String input) {
        System.out.print(input);
        try {
            int value = scanner.nextInt();
            scanner.nextLine();
            return value;
        } catch (InputMismatchException e) {
            scanner.nextLine();
            return -1;
        }
    }

    private static double readDoubleSafely(String input) {
        System.out.print(input);
        try {
            double value = scanner.nextDouble();
            scanner.nextLine();
            return value;
        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("Please enter a valid number");
            return -1;
        }
    }

    private static String readStringSafely(String input) {
        System.out.print(input);
        try {
            return scanner.nextLine().trim();
        } catch (Exception e) {
            return "";
        }
    }



    private static void printWelcome() {
        System.out.println("***************************************************************************");
        System.out.println("*            Welcome! Please choose a payment method.                    *");
        System.out.println("***************************************************************************");
    }

    private static void printMainMenu() {
        System.out.println("***************************************************************************");
        System.out.println("*  MAIN MENU ");
        System.out.println("***************************************************************************");
        System.out.println("*  1. Pay with Credit Card");
        System.out.println("*  2. Pay with PayPal");
        System.out.println("*  3. Pay with Apple Pay");
        System.out.println("*  4. Exit");
        System.out.println("***************************************************************************");
    }
}