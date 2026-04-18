package payments.impl;

import exceptions.PaymentException;
import payments.PaymentMethod;

public class PayPalPayment implements PaymentMethod {

    private final String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void pay(double amount) throws PaymentException {


        // Check for invalid amount
        if (amount <= 0) {
            throw new PaymentException("Amount must be greater than 0");
        }

        // Show payment details
        System.out.println("Connecting to PayPal...");
        System.out.println("Processing PayPal payment...");
        System.out.println("PayPal Amount : " + amount);
        System.out.println("A receipt will be sent to: " + email);
    }

    @Override
    public String getMethodName() {
        return "PayPal";
    }




}
