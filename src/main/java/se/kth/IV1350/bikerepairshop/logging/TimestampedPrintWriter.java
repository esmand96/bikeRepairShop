package se.kth.IV1350.bikerepairshop.logging;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Adapts {@link PrintWriter} using <strong>inheritance</strong>. This class
 * extends {@code PrintWriter} and overrides {@link #println(String)} so that every
 * printed line is prefixed with a timestamp.
 *
 * <p>Since this class inherits {@code PrintWriter}, it also inherits the entire public
 * interface of {@code PrintWriter}. Only {@link #println(String)} adds a timestamp;
 * all other inherited methods (for example {@code println(int)}, {@code print} and
 * {@code write}) keep their original behaviour and are therefore <em>not</em> stamped.
 * This is the loss of control over the public interface described in chapter 9.3 of
 * the course book.</p>
 */
public class TimestampedPrintWriter extends PrintWriter {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Creates a new instance that writes to the specified stream, flushing after every
     * write.
     *
     * @param out The stream to write log lines to.
     */
    public TimestampedPrintWriter(FileWriter out) {
        super(out, true);
    }

    /**
     * Writes the specified line, prefixed with the current time.
     *
     * @param line The line to write.
     */
    @Override
    public void println(String line) {
        String timestamp = LocalDateTime.now().format(FORMATTER);
        super.println("[" + timestamp + "] " + line);
    }
}
