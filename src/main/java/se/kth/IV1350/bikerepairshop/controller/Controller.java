package se.kth.IV1350.bikerepairshop.controller;

import se.kth.IV1350.bikerepairshop.exceptions.CustomerNotFoundException;
import se.kth.IV1350.bikerepairshop.exceptions.DatabaseFailureException;
import se.kth.IV1350.bikerepairshop.model.dto.CustomerDetailsDTO;
import se.kth.IV1350.bikerepairshop.model.dto.PresentNewlyCreatedRepairOrderDTO;
import se.kth.IV1350.bikerepairshop.model.dto.PresentRepairOrderForApprovalDTO;
import se.kth.IV1350.bikerepairshop.model.dto.ReceiptDTO;
import se.kth.IV1350.bikerepairshop.model.dto.common.RepairTaskDTO;
import se.kth.IV1350.bikerepairshop.service.Service;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Single point of entry to the system from the view. Delegates all calls to the service layer.
 */
public class Controller {
    private final Service service;

    /**
     * Creates a new instance using the specified service.
     *
     * @param service The service used to perform the system operations.
     */
    public Controller(Service service) {
        this.service = service;
    }

    /**
     * Finds the customer with the specified phone number, including the customer's first
     * unhandled consultation.
     *
     * @param phoneNumber The phone number used to identify the customer.
     * @return A DTO containing the customer's details and the selected consultation.
     * @throws CustomerNotFoundException if no customer has the specified phone number.
     * @throws DatabaseFailureException if the customer registry cannot be accessed.
     */
    public CustomerDetailsDTO findCustomer(String phoneNumber) throws CustomerNotFoundException, DatabaseFailureException {
        return service.findCustomerByPhoneNumber(phoneNumber);
    }

    /**
     * Creates a new repair order for the consultation with the specified id, using the
     * specified problem description.
     *
     * @param consultationId The id of the consultation the repair order is created for.
     * @param problemDescription The customer's description of the problem.
     */
    public void enterCustomerDescription(String consultationId, String problemDescription) {
        service.createRepairOrder(consultationId, problemDescription);
    }

    /**
     * Retrieves all repair orders that have been created but not yet diagnosed.
     *
     * @return A list of DTOs representing each newly created repair order.
     */
    public List<PresentNewlyCreatedRepairOrderDTO> getAllNewlyCreatedRepairOrders() {
        return service.getAllNewlyCreatedRepairOrders();
    }

    /**
     * Adds a diagnostic report and a list of repair tasks to the repair order with the
     * specified id, and marks the order as ready for approval.
     *
     * @param repairOrderId The id of the repair order to update.
     * @param diagnosticReportDescription The technician's description of the diagnosis.
     * @param repairTasks The proposed repair tasks with their associated costs.
     * @param estimatedRepairTime The estimated time required to complete the repair.
     */
    public void enterDiagnosticReportAndRepairTasks(String repairOrderId, String diagnosticReportDescription, List<RepairTaskDTO> repairTasks, LocalDateTime estimatedRepairTime) {
        service.enterDiagnosticReportAndRepairTasks(repairOrderId, diagnosticReportDescription, repairTasks, estimatedRepairTime);
    }

    /**
     * Retrieves all repair orders that are ready to be approved by the customer.
     *
     * @return A list of DTOs representing each repair order awaiting approval.
     */
    public List<PresentRepairOrderForApprovalDTO> getAllReadyForApprovalOrders() {
        return service.getAllReadyForApprovalOrders();
    }

    /**
     * Approves the repair order with the specified id.
     *
     * @param repairOrderId The id of the repair order to approve.
     */
    public void approveRepairOrder(String repairOrderId) {
        service.approveRepairOrder(repairOrderId);
    }

    /**
     * Generates and prints a receipt for the approved repair order with the specified id.
     *
     * @param repairOrderId The id of the approved repair order.
     * @return A DTO containing the receipt data, or {@code null} if the order is not approved.
     */
    public ReceiptDTO getReceipt(String repairOrderId) {
        return service.getReceipt(repairOrderId);
    }

    public void rejectRepairOrder(String repairOrderId) {
        service.rejectRepairOrder(repairOrderId);
    }
}

