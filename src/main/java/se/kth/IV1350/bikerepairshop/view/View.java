package se.kth.IV1350.bikerepairshop.view;

import se.kth.IV1350.bikerepairshop.controller.Controller;
import se.kth.IV1350.bikerepairshop.exceptions.CustomerNotFoundException;
import se.kth.IV1350.bikerepairshop.exceptions.DatabaseFailureException;
import se.kth.IV1350.bikerepairshop.model.dto.common.CustomerDetailsDTO;
import se.kth.IV1350.bikerepairshop.model.dto.common.PresentNewlyCreatedRepairOrderDTO;
import se.kth.IV1350.bikerepairshop.model.dto.common.PresentRepairOrderForApprovalDTO;
import se.kth.IV1350.bikerepairshop.model.dto.common.ReceiptDTO;
import se.kth.IV1350.bikerepairshop.model.dto.common.RepairTaskDTO;
import se.kth.IV1350.bikerepairshop.logging.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class View {
    private final Controller controller;
    private final Scanner scanner = new Scanner(System.in);
    private final Logger logger;

    public View(Controller controller, Logger logger) {
        this.controller = controller;
        this.logger = logger;
    }

    // För att simulera ett databas fel, ange 1 som phoneNumber
    public void askForPhoneNumber() {
        try {
            String phoneNumber = "070123"; //
            CustomerDetailsDTO customerDetailsDTO = controller.findCustomer(phoneNumber);
            printCustomerInfo(customerDetailsDTO);
            String consultationId = customerDetailsDTO.getConsultationId();
            enterDescription(consultationId);
        } catch (CustomerNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (DatabaseFailureException e) {
            logger.logg(e.getMessage());
            System.out.println("Systemfel: Databasen är inte tillgänglig. Försök igen senare.");
        }

    }

    private void printCustomerInfo(CustomerDetailsDTO dto) {
        System.out.println("\n========================================");
        System.out.println(" RECEPTIONIST HÄMTAR KUNDINFORMATION FÖR 070123 ");
        System.out.println("========================================");
        System.out.printf("%-18s %s%n", "Namn:", dto.getName());
        System.out.printf("%-18s %s%n", "E-post:", dto.getEmail());
        System.out.printf("%-18s %s%n", "Telefon:", dto.getPhoneNumber());
        System.out.println("----------------------------------------");
        System.out.printf("%-18s %s %s%n", "Cykel:", dto.getBikeBrand(), dto.getBikeModel());
        System.out.printf("%-18s %s%n", "Serienummer:", dto.getBikeSerialNumber());
        System.out.println("----------------------------------------");
        System.out.printf("%-18s %s%n", "Konsultations-ID:", dto.getConsultationId());
        System.out.println("========================================\n");
    }

    public void enterDescription(String consultationId) {
        String description = "Punktering på bakhjulet.";

        System.out.println("\n----------------------------------------");
        System.out.println("   RECEPTIONIST REGISTRERAR NY REPAIR ORDER   ");
        System.out.println("----------------------------------------");
        System.out.printf("%-20s %s%n", "Beskrivning:", description);
        System.out.println("----------------------------------------\n");

        controller.enterCustomerDescription(consultationId, description);

        technicianChooseNewlyCreatedRepairOrders();
    }

    public void technicianChooseNewlyCreatedRepairOrders() {
        List<PresentNewlyCreatedRepairOrderDTO> newlyCreatedRepairOrderDTOS = controller.getAllNewlyCreatedRepairOrders();

        System.out.println("\n========================================================================");
        System.out.println("            TEKNIKER  HÄMTAR ALLA NYA REPARATIONSORDRAR       ");
        System.out.println("========================================================================");
        String selectedOrderId = newlyCreatedRepairOrderDTOS.getFirst().getRepairOrderId();
        System.out.println("Väljer Order ID som visats på displayen : " + selectedOrderId + "\n");
        technicianEntersDiagnosticReportAndRepairTasks(selectedOrderId);
    }

    public void technicianEntersDiagnosticReportAndRepairTasks(String repairOrderId) {
        System.out.println("\n========================================================================");
        System.out.println("            TEKNIKER SKAPAR DIAGNOSRAPPORT OCH REPARATIONSÅTGÄRDER              ");
        System.out.println("========================================================================");
        System.out.printf("%-20s %s%n", "Order ID:", repairOrderId);

        String diagnosticReportDescription = "Brustet hjul och punktering";
        System.out.printf("%-20s %s%n", "Diagnos:", diagnosticReportDescription);
        System.out.println("------------------------------------------------------------------------");

        List<RepairTaskDTO> repairTasks = new ArrayList<>();
        RepairTaskDTO repairTask1 = RepairTaskDTO.builder()
                .description("Laga brustet hjul")
                .cost(75.84)
                .build();
        RepairTaskDTO repairTask2 = RepairTaskDTO.builder()
                .description("Laga punktering")
                .cost(5.9)
                .build();
        repairTasks.add(repairTask1);
        repairTasks.add(repairTask2);

        System.out.println("Repair Tasks:");
        for (RepairTaskDTO task : repairTasks) {
            System.out.printf("  - %-35s Pris: %6.2f SEK%n", task.getDescription(), task.getCost());
        }
        System.out.println("------------------------------------------------------------------------");

        LocalDateTime estimatedRepairTime = LocalDateTime.now().plusDays(3);

        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        System.out.printf("%-20s %s%n", "Beräknas klar:", estimatedRepairTime.format(formatter));
        System.out.println("========================================================================\n");

        controller.enterDiagnosticReportAndRepairTasks(repairOrderId, diagnosticReportDescription, repairTasks, estimatedRepairTime);

        receptionistGetAllReadyForApprovalOrders();
    }

    public void receptionistGetAllReadyForApprovalOrders() {
        List<PresentRepairOrderForApprovalDTO> repairOrderForApprovalDTOS = controller.getAllReadyForApprovalOrders();

        System.out.println("\n========================================================================");
        System.out.println("    RECEPTIONIST HÄMTAR  ORDRAR KLARA FÖR GODKÄNNANDE ");
        System.out.println("========================================================================");

        DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        for (PresentRepairOrderForApprovalDTO dto : repairOrderForApprovalDTOS) {
            System.out.println("  ORDER ID: " + dto.getRepairOrderId());
            System.out.println("  ----------------------------------------------------------------------");
            System.out.printf("  %-18s %s%n", "Telefon:", dto.getCustomerPhoneNumber());
            System.out.printf("  %-18s %s %s (%s)%n", "Cykel:", dto.getBikeBrand(), dto.getBikeModel(), dto.getBikeSerialNumber());
            System.out.printf("  %-18s %s%n", "Diagnos:", dto.getDiagnosticReport().getDescription());
            System.out.printf("  %-18s %s%n", "Beräknas klar:", dto.getDiagnosticReport().getEstimatedRepairTime().format(formatter));
            System.out.println(" Repair Tasks:");
            if (dto.proposedRepairTasks() != null) {
                for (RepairTaskDTO task : dto.proposedRepairTasks()) {
                    System.out.printf("    - %-31s Pris: %6.2f SEK%n", task.getDescription(), task.getCost());
                }
            }

            System.out.printf("  %-18s %6.2f SEK%n", "Total kostnad:", dto.getTotalCost());
            System.out.println("========================================================================");
        }

        String selectedOrderId = repairOrderForApprovalDTOS.getFirst().getRepairOrderId();
        boolean approved = getApproveOrRejected();
        if (approved)
            approveRepairOrder(selectedOrderId);
        else
            rejectRepairOrder(selectedOrderId);

    }

    private boolean getApproveOrRejected() {
        System.out.println("ÄR REPAIR ORDER GODKÄND AV KUND? VÄNLIGEN ANGE J FÖR JA OCH N FÖR NEJ ");
        String approved = scanner.nextLine();
        if (approved.toUpperCase().equals("J"))
            return true;
        else if (approved.toUpperCase().equals("N")) {
            return false;
        } else {
            System.out.println("OGILTIG INPUT! SVARA MED J/N");
            return getApproveOrRejected();
        }
    }

    public void approveRepairOrder(String repairOrderId) {
        System.out.println("\n========================================================================");
        System.out.println("                     GODKÄNNER REPARATIONSORDER                         ");
        System.out.println("========================================================================");
        controller.approveRepairOrder(repairOrderId);
        System.out.printf("  %-20s %s%n", "Godkänner Order ID:", repairOrderId);
        System.out.println("  Status uppdaterad till: GODKÄND/PÅGÅENDE");
        System.out.println("========================================================================\n");
        printReceipt(repairOrderId);
    }

    public void rejectRepairOrder(String repairOrderId) {
        System.out.println("\n========================================================================");
        System.out.println("                     NEKAR REPARATIONSORDER                         ");
        System.out.println("========================================================================");

        controller.rejectRepairOrder(repairOrderId);
        System.out.printf("  %-20s %s%n", "Nekar Order ID:", repairOrderId);
        System.out.println("  Status uppdaterad till: REJECTED");
        System.out.println("========================================================================\n");
    }

    public void printReceipt(String repairOrderId) {
        System.out.println("\n========================================================================");
        System.out.println("   SYSTEMET SKRIVER UT KVITTO FÖR " + repairOrderId);
        System.out.println("========================================================================");
        ReceiptDTO receiptDTO = controller.getReceipt(repairOrderId);
        System.out.println("CUSTOMER");
        System.out.println("  Name:         " + receiptDTO.getName());
        System.out.println("  Email:        " + receiptDTO.getEmail());
        System.out.println("  Phone:        " + receiptDTO.getPhoneNumber());

        System.out.println("----------------------------------------");
        System.out.println("BIKE");
        System.out.println("  Brand:        " + receiptDTO.getBikeBrand());
        System.out.println("  Model:        " + receiptDTO.getBikeModel());
        System.out.println("  Serial No:    " + receiptDTO.getBikeSerialNumber());

        System.out.println("----------------------------------------");
        System.out.println("PROBLEM");
        System.out.println("  " + receiptDTO.getProblemDescription());

        System.out.println("----------------------------------------");
        System.out.println("DIAGNOSTIC REPORT");
        System.out.println("  " + receiptDTO.getDiagnosticReport().getDescription());
        System.out.println("  Est. repair:  " + receiptDTO.getDiagnosticReport().getEstimatedRepairTime());

        System.out.println("----------------------------------------");
        System.out.println("REPAIR TASKS");
        for (RepairTaskDTO task : receiptDTO.getRepairTasks()) {
            System.out.printf("  %-28s %6.2f SEK%n", task.getDescription(), task.getCost());
        }

        System.out.println("----------------------------------------");
        System.out.printf("  %-28s %6.2f SEK%n", "TOTAL", receiptDTO.getTotalCost());
        System.out.println("========================================");
        System.out.println("  Status: " + receiptDTO.getState());
    }

}
