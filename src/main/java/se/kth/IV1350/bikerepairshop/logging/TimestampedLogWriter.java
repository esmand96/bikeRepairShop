package se.kth.IV1350.bikerepairshop.logging;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Adapts {@link PrintWriter} using <strong>composition</strong>. This class
 * does not extend {@code PrintWriter}; instead it holds a {@code PrintWriter} as a
 * private field and delegates to it, adding a timestamp to every printed line.
 *
 * <p>Because {@code PrintWriter} is reached only through its public interface, this
 * class is not affected by how {@code PrintWriter} is implemented internally. It also
 * exposes only the single method {@link #println(String)}, so a client cannot bypass
 * the timestamp by calling some other inherited method. This is the encapsulation and
 * interface control advantage of composition described in chapter 9.3 of the course
 * book.</p>
 */
public class TimestampedLogWriter {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final PrintWriter out;
 
    /**
     * Creates a new instance that writes to the specified stream, flushing after every
     * write.
     *
     * @param stream The stream to write log lines to.
     */
    public TimestampedLogWriter(FileWriter stream) {
        this.out = new PrintWriter(stream, true);
    }
 
    /**
     * Writes the specified line, prefixed with the current time.
     *
     * @param line The line to write.
     */
    public void println(String line) {
        String timestamp = LocalDateTime.now().format(FORMATTER);
        out.println("[" + timestamp + "] " + line);
    }
}
 