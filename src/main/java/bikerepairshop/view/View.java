package bikerepairshop.view;

import bikerepairshop.controller.Controller;
import bikerepairshop.model.dto.CustomerDetailsDTO;
import bikerepairshop.model.dto.PresentNewlyCreatedRepairOrderDTO;
import bikerepairshop.model.dto.PresentRepairOrderForApprovalDTO;
import bikerepairshop.model.dto.RepairTaskDTO;
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
       for (PresentNewlyCreatedRepairOrderDTO presentNewlyCreatedRepairOrderDTO : newlyCreatedRepairOrderDTOS){
           System.out.println(presentNewlyCreatedRepairOrderDTO.getRepairOrderId());
       }
       technicianEntersDiagnosticReportAndRepairTasks(newlyCreatedRepairOrderDTOS.getFirst().getRepairOrderId());
    }

    public void technicianEntersDiagnosticReportAndRepairTasks(String repairOrderId){
        String diagnosticReportDescription = "Broken wheel";
        List <RepairTaskDTO> repairTasks = new ArrayList<>();
        RepairTaskDTO repairTask1 = new RepairTaskDTO("fix broken wheel", 75.84);
        RepairTaskDTO repairTask2 = new RepairTaskDTO("fix flat tire", 5.9);
        repairTasks.add(repairTask1);
        repairTasks.add(repairTask2);
        LocalDateTime estimatedRepairTime = LocalDateTime.now().plusDays(3);
        controller.enterDiagnosticReportAndRepairTasks(repairOrderId, diagnosticReportDescription, repairTasks, estimatedRepairTime);
        receptionistGetAllReadyForApprovalOrders();
    }

    public void receptionistGetAllReadyForApprovalOrders(){
        List<PresentRepairOrderForApprovalDTO> repairOrderForApprovalDTOS = controller.getAllReadyForApprovalOrders();
        System.out.println("ALL READY FOR APPROVAL : ");
        for(PresentRepairOrderForApprovalDTO repairOrderForApprovalDTO : repairOrderForApprovalDTOS){
            System.out.println(repairOrderForApprovalDTO.getRepairOrderId());
        }

    }

}
