# Payment Application

A simple Java console application that demonstrates how to add a new payment method to an existing system using SOLID design principles.

---

## About the Project

This project simulates a basic payment screen. The goal is to show how new payment methods can be added to the system **without changing the existing code**.



---

## SOLID Principles Applied

### S — Single Responsibility
Each class has one clear job.
- `Main` handles only user interaction
- `CreditCardPayment` handles only credit card logic
- `PaymentException` only represents a payment error

### O — Open/Closed Principle
The system is **open for extension, closed for modification**.

`PayPalPayment` and `ApplePayPayment` were added by creating new classes only. No existing class was changed. To add another payment method in the future (e.g. Stripe, Crypto), just create a new class that implements `PaymentMethod`.


### L — Liskov Substitution
All payment classes implement `PaymentMethod`, so they can be used interchangeably without breaking the program.

### I — Interface Segregation
The `PaymentMethod` interface is small and focused. It only contains the methods that every payment class actually needs.

### D — Dependency Inversion
`Main` depends on the `PaymentMethod` interface, not on specific classes like `CreditCardPayment`. This makes it easy to swap or add payment methods.

---

## Java Reflection (MainWithReflection.java)

The project includes a second main class that demonstrates how Java Reflection works.

**Standard approach:**
```java
PaymentMethod creditCard = new CreditCardPayment(cardNumber, cardHolder, expiryDate);
```

**Reflection approach:**
```java
Class<?> clazz = Class.forName("payments.impl.CreditCardPayment");
Constructor<?> constructor = clazz.getConstructor(String.class, String.class, String.class);
PaymentMethod creditCard = (PaymentMethod) constructor.newInstance(cardNumber, cardHolder, expiryDate);
```

The key difference is that with reflection, the class name is just a `String`. Java does not know which class will be used until the program actually runs. This is called **runtime binding**.

In this project, reflection is used for learning purposes. In real-world applications it is commonly found in frameworks like Spring and Hibernate, where objects are created dynamically based on configuration.

---

## How to Run

**Requirements:** Java 17 or higher

```bash
# Compile
javac *.java

# Run
java Main
```

---

## Exception Handling

| Situation | What happens |
|---|---|
| Amount is zero or negative | `PaymentException` is thrown |
| User types letters instead of a number | Caught with `InputMismatchException`, asks again |
| Empty card number or email | Shows an error, returns to menu |
