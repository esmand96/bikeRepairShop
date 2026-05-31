package se.kth.IV1350.bikerepairshop.logging;

import se.kth.IV1350.bikerepairshop.model.dto.RepairOrderUpdatedDTO;
import se.kth.IV1350.bikerepairshop.model.dto.RepairTaskDTO;
import se.kth.IV1350.bikerepairshop.observer.AbstractRepairOrderObserver;
import se.kth.IV1350.bikerepairshop.observer.RepairOrderObserver;



/**
 * An observer that logs repair order updates to a file. Implements {@link Logger}, since
 * its task is to log the events it observes.
 */
public class RepairOrderLogger extends AbstractRepairOrderObserver implements Logger <RepairOrderUpdatedDTO> {
    private TimestampedLogWriter logger;

    /**
     * Creates a new instance. Appends to an existing log file, or creates a new one
     * if it does not exist.
     */
    public RepairOrderLogger (TimestampedLogWriter timestampedLogWriter) {
        logger = timestampedLogWriter;
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
            logEntry.append(" description ").append(message.getDiagnosticReport().getDescription()).append("\n");
            logEntry.append(" description ").append(message.getDiagnosticReport().getEstimatedRepairTime()).append("\n");

        } else {
            logEntry.append("  (none)\n");
        }
        logEntry.append("\n");

        logEntry.append("Proposed repair tasks\n");
        if (message.getProposedRepairTasks() != null && !message.getProposedRepairTasks().isEmpty()) {
            for (RepairTaskDTO task : message.getProposedRepairTasks()) {
                logEntry.append(" - Task : ").append(task.getDescription()).append(" | ");
                logEntry.append(" Cost : ").append(task.getCost()).append("\n");
            }
        } else {
            logEntry.append("  (none)\n");
        }
        logEntry.append("========================================================\n");

        logger.println(logEntry.toString());


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

    @Override
    protected void doHandleStateChange(RepairOrderUpdatedDTO repairOrderUpdatedDTO) {
        logg(repairOrderUpdatedDTO);
    }

    @Override
    protected void handleErrors(Exception e) {
        System.out.println("Could not log repair order state change.");
    }
}
