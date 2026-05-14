package se.kth.IV1350.bikerepairshop.logging;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileLogger implements Logger <String> {
    private PrintWriter logStream;
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Creates a new instance. Appends to an existing log file,
     * or creates a new one if it doesn't exist.
     */
    public FileLogger() {
        try {
            logStream = new PrintWriter(
                    new FileWriter("src/main/resources/log.txt", true), true);
        } catch (IOException ioe) {
            System.out.println("CAN NOT LOG.");
            ioe.printStackTrace();
        }
    }

    /**
     * Prints the specified string to the log file with a timestamp.
     *
     * @param message The string that will be printed to the log file.
     */
    @Override
    public void logg(String message) {
        String timestamp = LocalDateTime.now().format(FORMATTER);
        logStream.println("[" + timestamp + "] " + message);
    }
}
