package se.kth.IV1350.bikerepairshop.view;

import se.kth.IV1350.bikerepairshop.model.dto.RepairOrderUpdatedDTO;
import se.kth.IV1350.bikerepairshop.model.dto.RepairTaskDTO;
import se.kth.IV1350.bikerepairshop.observer.RepairOrderObserver;

import java.time.format.DateTimeFormatter;

/**
 * An observer that presents repair order updates to the user by printing them to
 * {@code System.out}. Represents an external view, such as a display or application,
 * that is notified whenever a repair order changes.
 */
public class RepairOrderView implements RepairOrderObserver {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Called when an observed repair order has changed. Prints the updated order,
     * including diagnosis and proposed repair tasks if the order is ready for approval.
     *
     * @param repairOrderUpdatedDTO The data describing the updated repair order.
     */
    @Override
    public void stateHasChanged(RepairOrderUpdatedDTO repairOrderUpdatedDTO) {
        System.out.println(" ===================== REPAIR ORDER STATE CHANGED =====================");

        System.out.println("  ORDER ID: " + repairOrderUpdatedDTO.getRepairOrderId());
        System.out.println("  ----------------------------------------------------------------------");
        System.out.printf("  %-16s %s%n", "Kund:", repairOrderUpdatedDTO.getName() + " (" + repairOrderUpdatedDTO.getPhoneNumber() + ")");
        System.out.printf("  %-16s %s%n", "E-post:", repairOrderUpdatedDTO.getEmail());
        System.out.printf("  %-16s %s %s (%s)%n", "Cykel:", repairOrderUpdatedDTO.getBikeBrand(), repairOrderUpdatedDTO.getBikeModel(), repairOrderUpdatedDTO.getBikeSerialNumber());
        System.out.printf("  %-16s %s%n", "Beskrivning:", repairOrderUpdatedDTO.getProblemDescription());
        System.out.printf("  %-16s %s%n", "Status:", repairOrderUpdatedDTO.getState());

        if("READY_FOR_APPROVAL".equals(repairOrderUpdatedDTO.getState())){
            System.out.printf("  %-18s %s%n", "Diagnos:", repairOrderUpdatedDTO.getDiagnosticReport().getDescription());
            System.out.printf("  %-18s %s%n", "Beräknas klar:", repairOrderUpdatedDTO.getDiagnosticReport().getEstimatedRepairTime().format(DATE_TIME_FORMATTER));
            System.out.println(" Repair Tasks:");
            if (repairOrderUpdatedDTO.getProposedRepairTasks() != null) {
                for (RepairTaskDTO task : repairOrderUpdatedDTO.getProposedRepairTasks()) {
                    System.out.printf("    - %-31s Pris: %6.2f SEK%n", task.getDescription(), task.getCost());
                }
            }

        }
        System.out.println("========================================================================");



    }
}

