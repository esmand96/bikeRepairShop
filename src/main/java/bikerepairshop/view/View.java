package bikerepairshop.view;

import bikerepairshop.controller.Controller;
import bikerepairshop.model.dto.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class View {
    private final Controller controller;
    public View(Controller controller) {
        this.controller = controller;
    }

    public void askForPhoneNumber(){
        System.out.println("Enter phone number: ");
        Scanner scanner = new Scanner(System.in);
        String phoneNumber =  scanner.nextLine();
        CustomerDetailsDTO customerDetailsDTO = controller.findCustomer(phoneNumber);
        String consultationId = customerDetailsDTO.getConsultationId();
        enterDescription(consultationId);
    }

    public void enterDescription(String consultationId){
        System.out.println("Enter problem description: ");
        Scanner scanner = new Scanner(System.in);
        String description =  scanner.nextLine();
        controller.enterCustomerDescription(consultationId, description);
        technicianChooseNewlyCreatedRepairOrders();
    }

    public void technicianChooseNewlyCreatedRepairOrders(){
       List<PresentNewlyCreatedRepairOrderDTO> newlyCreatedRepairOrderDTOS = controller.getAllNewlyCreatedRepairOrders();
        System.out.println("ALL NEWLY CREATED : ");
        for (PresentNewlyCreatedRepairOrderDTO presentNewlyCreatedRepairOrderDTO : newlyCreatedRepairOrderDTOS){
           System.out.println(presentNewlyCreatedRepairOrderDTO.getRepairOrderId());
       }
       technicianEntersDiagnosticReportAndRepairTasks(newlyCreatedRepairOrderDTOS.getFirst().getRepairOrderId());
    }

    public void technicianEntersDiagnosticReportAndRepairTasks(String repairOrderId){
        System.out.println("ENTERING DIAGNOSTICREPORT AND REPAIR TASK FOR: " + repairOrderId);
        String diagnosticReportDescription = "Broken wheel";
        System.out.println("Diagnostic Report: " + diagnosticReportDescription);

        List <RepairTaskDTO> repairTasks = new ArrayList<>();
        RepairTaskDTO repairTask1 = new RepairTaskDTO("fix broken wheel", 75.84);
        RepairTaskDTO repairTask2 = new RepairTaskDTO("fix flat tire", 5.9);
        repairTasks.add(repairTask1);
        repairTasks.add(repairTask2);
        System.out.println("Repair Tasks Added:");
        for (RepairTaskDTO task : repairTasks) {
            System.out.println("  - " + task.getDescription() + " | Cost: " + task.getCost() + " SEK");
        }

        LocalDateTime estimatedRepairTime = LocalDateTime.now().plusDays(3);
        System.out.println("Estimated Repair Date: " + estimatedRepairTime);

        controller.enterDiagnosticReportAndRepairTasks(repairOrderId, diagnosticReportDescription, repairTasks, estimatedRepairTime);
        receptionistGetAllReadyForApprovalOrders();
    }

    public void receptionistGetAllReadyForApprovalOrders(){
        List<PresentRepairOrderForApprovalDTO> repairOrderForApprovalDTOS = controller.getAllReadyForApprovalOrders();
        System.out.println("RECEPTIONIST GETS ALL READY FOR APPROVAL : ");
        for(PresentRepairOrderForApprovalDTO repairOrderForApprovalDTO : repairOrderForApprovalDTOS){
            System.out.println(repairOrderForApprovalDTO.getRepairOrderId());
        }
        approveRepairOrder(repairOrderForApprovalDTOS.getFirst().getRepairOrderId());
    }

    public void approveRepairOrder (String repairOrderId){
        controller.approveRepairOrder(repairOrderId);
        printReceipt(repairOrderId);
    }
    public void printReceipt(String repairOrderId) {
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
