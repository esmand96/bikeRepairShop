package se.kth.IV1350.bikerepairshop.logging;

import se.kth.IV1350.bikerepairshop.model.dto.RepairOrderUpdatedDTO;
import se.kth.IV1350.bikerepairshop.model.dto.RepairTaskDTO;
import se.kth.IV1350.bikerepairshop.observer.RepairOrderObserver;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * An observer that logs repair order updates to a file. Implements both
 * {@link RepairOrderObserver}, to be notified of changes, and {@link Logger}, since
 * its task is to log the events it observes.
 */
public class RepairOrderLogger implements Logger <RepairOrderUpdatedDTO>, RepairOrderObserver {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private PrintWriter logStream;

    /**
     * Creates a new instance. Appends to an existing log file, or creates a new one
     * if it does not exist.
     */
    public RepairOrderLogger () {
        try {
            logStream = new PrintWriter(
                    new FileWriter("src/main/resources/repairOrderLog.txt", true), true);
        } catch (IOException ioe) {
            System.out.println("CAN NOT LOG.");
            ioe.printStackTrace();
        }
    }

    /**
     * Writes the specified repair order to the log file with a timestamp.
     *
     * @param message The repair order data to write to the log file.
     */
    @Override
    public void logg(RepairOrderUpdatedDTO message) {
        StringBuilder logEntry = new StringBuilder();

        logEntry.append("========================================================\n");
        logEntry.append("Time of log entry: ")
                .append(LocalDateTime.now().format(TIME_FORMATTER)).append("\n");
        logEntry.append("--------------------------------------------------------\n");
        logEntry.append("Repair order ID : ").append(message.getRepairOrderId()).append("\n");
        logEntry.append("State           : ").append(message.getState()).append("\n");
        logEntry.append("\n");

        logEntry.append("Customer\n");
        logEntry.append("  Name          : ").append(message.getName()).append("\n");
        logEntry.append("  Email         : ").append(message.getEmail()).append("\n");
        logEntry.append("  Phone number  : ").append(message.getPhoneNumber()).append("\n");
        logEntry.append("\n");

        logEntry.append("Bike\n");
        logEntry.append("  Brand         : ").append(message.getBikeBrand()).append("\n");
        logEntry.append("  Model         : ").append(message.getBikeModel()).append("\n");
        logEntry.append("  Serial number : ").append(message.getBikeSerialNumber()).append("\n");
        logEntry.append("\n");

        logEntry.append("Problem description\n");
        logEntry.append("  ").append(message.getProblemDescription()).append("\n");
        logEntry.append("\n");

        logEntry.append("Diagnostic report\n");
        if (message.getDiagnosticReport() != null) {
            logEntry.append("  ").append(message.getDiagnosticReport()).append("\n");
        } else {
            logEntry.append("  (none)\n");
        }
        logEntry.append("\n");

        logEntry.append("Proposed repair tasks\n");
        if (message.getProposedRepairTasks() != null && !message.getProposedRepairTasks().isEmpty()) {
            for (RepairTaskDTO task : message.getProposedRepairTasks()) {
                logEntry.append("  - ").append(task).append("\n");
            }
        } else {
            logEntry.append("  (none)\n");
        }
        logEntry.append("========================================================\n");

        logStream.println(logEntry);


    }

    /**
     * Called when an observed repair order has changed. Logs the updated order to file.
     *
     * @param repairOrderUpdatedDTO The data describing the updated repair order.
     */
    @Override
    public void stateHasChanged(RepairOrderUpdatedDTO repairOrderUpdatedDTO) {
        logg(repairOrderUpdatedDTO);
    }
}