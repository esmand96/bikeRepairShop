package se.kth.IV1350.bikerepairshop.view;

import se.kth.IV1350.bikerepairshop.model.dto.RepairOrderUpdatedDTO;
import se.kth.IV1350.bikerepairshop.model.dto.RepairTaskDTO;
import se.kth.IV1350.bikerepairshop.observer.RepairOrderObserver;

import java.time.format.DateTimeFormatter;

public class RepairOrderView implements RepairOrderObserver {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

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

