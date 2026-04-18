package payments.impl;

import exceptions.PaymentException;
import payments.PaymentMethod;

public class CreditCardPayment implements PaymentMethod {

    private String cardNumber;

    private String cardHolderName;

    private String expiryDate;




    public CreditCardPayment(String cardNumber, String cardHolderName, String expiryDate) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expiryDate = expiryDate;

    }


    @Override
    public void pay(double amount) throws PaymentException {

        // Check if the amount is valid
        if (amount <= 0) {
            throw new PaymentException("Amount must be greater than 0");
        }

        // Show payment details
        System.out.println("Processing Credit Card payment...");
        System.out.println("Card Holder : " + cardHolderName);
        System.out.println("Card Number : " + cardNumber);
        System.out.println("Expiry Date : " + expiryDate);
        System.out.println("Amount : " + amount);
        System.out.println("Status : SUCCESS");
    }

    @Override
    public String getMethodName() {
        return "Credit Card";
    }

}
