package se.kth.IV1350.bikerepairshop.logging;
import java.io.PrintWriter;


/**
 * Logs messages to a file with a timestamp.
 * Appends to an existing log file, or creates a new one if it does not exist.
 */
public class FileLogger implements Logger<String> {
    private PrintWriter logStream;

    /**
     * Creates a new instance. Appends to an existing log file,
     * or creates a new one if it doesn't exist.
     */
    public FileLogger(PrintWriter printWriter) {
        logStream = printWriter;
    }

    /**
     * Prints the specified string to the log file with a timestamp.
     *
     * @param message The string that will be printed to the log file.
     */
    @Override
    public void logg(String message) {
        logStream.println(message);
    }
}