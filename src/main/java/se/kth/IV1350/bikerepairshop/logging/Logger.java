package se.kth.IV1350.bikerepairshop.logging;

/**
 * A generic logger that writes log messages of a specified type. The type of message
 * is decided by the implementing class.
 *
 * @param <T> The type of message this logger writes.
 */
public interface Logger <T> {

    /**
     * Writes the specified message to the log.
     *
     * @param message The message to log.
     */
    void logg (T message);
}