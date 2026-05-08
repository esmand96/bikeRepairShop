package se.kth.IV1350.bikerepairshop.exceptions;

/**
 * Thrown when the database cannot be accessed or is unavailable. This is an unchecked
 * exception because infrastructure failures cannot be recovered from at the point where
 * they occur, and it would be impractical to declare this exception on every method.
 */
public class DatabaseFailureException extends RuntimeException {

    /**
     * Creates a new instance with a descriptive message about the error condition.
     *
     * @param message A message describing which registry or database service is unavailable.
     */
    public DatabaseFailureException(String message) {
        super(message);
    }
}