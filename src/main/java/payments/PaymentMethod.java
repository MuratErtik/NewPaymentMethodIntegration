package payments;

import exceptions.PaymentException;

public interface PaymentMethod {

    //This interface defines what every payment method must do.

    void pay(double amount) throws PaymentException;

    String getMethodName();

}
