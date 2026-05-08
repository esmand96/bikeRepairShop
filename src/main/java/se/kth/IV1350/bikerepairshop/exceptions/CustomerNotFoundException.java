package se.kth.IV1350.bikerepairshop.exceptions;

/**
 * Thrown when a customer with a specified phone number is not found in the customer registry.
 * This is a checked exception because the absence of a customer is an expected alternative
 * flow that the application can recover from.
 */
public class CustomerNotFoundException extends Exception {

    /**
     * Creates a new instance with a descriptive message about the error condition.
     *
     * @param message A message describing why the customer was not found, typically
     *                including the phone number that was searched for.
     */
    public CustomerNotFoundException(String message) {
        super(message);
    }
}