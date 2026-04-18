package payments.impl;

import exceptions.PaymentException;
import payments.PaymentMethod;

public class ApplePayPayment implements PaymentMethod {



    private String appleId; //apple mail is okey though



    public ApplePayPayment(String appleId) {

        this.appleId = appleId;

    }

    @Override
    public void pay(double amount) throws PaymentException {

        // Check for invalid amount
        if (amount <= 0) {
            throw new PaymentException("Amount must be greater than 0");
        }

        // Show payment details
        System.out.println("Requesting Face ID / Touch ID authentication...");
        System.out.println("Authentication successful.");
        System.out.println("Processing Apple Pay payment...");
        System.out.println("Apple ID  : " + appleId);
        System.out.println("Amount     : " + amount);
        System.out.println("Status    : SUCCESS");
    }

    @Override
    public String getMethodName() {
        return "Apple Pay";
    }


}
