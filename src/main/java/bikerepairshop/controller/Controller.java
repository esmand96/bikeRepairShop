package bikerepairshop.controller;

import bikerepairshop.model.dto.CustomerDetailsDTO;
import bikerepairshop.model.dto.PresentNewlyCreatedRepairOrderDTO;
import bikerepairshop.model.dto.PresentRepairOrderForApprovalDTO;
import bikerepairshop.model.dto.RepairTaskDTO;
import bikerepairshop.service.Service;
import java.time.LocalDateTime;
import java.util.List;

public class Controller {
    private final Service service;
    public Controller(Service service) {
        this.service = service;
    }

    public CustomerDetailsDTO findCustomer (String phoneNumber){
        return service.findCustomerByPhoneNumber(phoneNumber);
    }
        ///man kan skapa en dTO som parameter.
    public void enterCustomerDescription(String consultationId, String problemDescription){
        service.createRepairOrder(consultationId, problemDescription);
    }

    public List<PresentNewlyCreatedRepairOrderDTO> getAllNewlyCreatedRepairOrders() {
        return service.getAllNewlyCreatedRepairOrders();
    }

    public void enterDiagnosticReportAndRepairTasks(String repairOrderId, String diagnosticReportDescription, List<RepairTaskDTO> repairTasks, LocalDateTime estimatedRepairTime) {
        service.enterDiagnosticReportAndRepairTasks(repairOrderId, diagnosticReportDescription, repairTasks, estimatedRepairTime);
    }

    public List <PresentRepairOrderForApprovalDTO> getAllReadyForApprovalOrders() {
        return service.getAllReadyForApprovalOrders();
    }
}
