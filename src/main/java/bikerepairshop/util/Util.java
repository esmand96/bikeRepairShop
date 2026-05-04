package bikerepairshop.util;

import java.util.UUID;

/**
 * Provides shared utility methods used across the application.
 */
public final class Util {

    /**
     * Generates a random unique id.
     *
     * @return A randomly generated unique id as a string.
     */
    public static String generateRandomId() {
        return UUID.randomUUID().toString();
    }
}