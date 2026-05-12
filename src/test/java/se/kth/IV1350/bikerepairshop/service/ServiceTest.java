package se.kth.IV1350.bikerepairshop.service;

import se.kth.IV1350.bikerepairshop.integration.CustomerRegistryIntegration;
import se.kth.IV1350.bikerepairshop.integration.PrinterIntegration;
import se.kth.IV1350.bikerepairshop.integration.RepairOrderRegistryIntegration;
import se.kth.IV1350.bikerepairshop.model.dto.CustomerDetailsDTO;
import se.kth.IV1350.bikerepairshop.model.dto.PresentNewlyCreatedRepairOrderDTO;
import se.kth.IV1350.bikerepairshop.model.dto.PresentRepairOrderForApprovalDTO;
import se.kth.IV1350.bikerepairshop.model.dto.ReceiptDTO;
import se.kth.IV1350.bikerepairshop.model.dto.common.RepairTaskDTO;
import se.kth.IV1350.bikerepairshop.model.entity.CustomerDetailsEntity;
import se.kth.IV1350.bikerepairshop.exceptions.CustomerNotFoundException;
import se.kth.IV1350.bikerepairshop.exceptions.DatabaseFailureException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static se.kth.IV1350.bikerepairshop.TestUtil.*;

public class ServiceTest {

    private Service service;
    private CustomerRegistryIntegration customerRegistryIntegration;

    // Reusable test data
    private RepairTaskDTO repairTask1;
    private RepairTaskDTO repairTask2;
    private List<RepairTaskDTO> repairTasks;
    private String phoneNumber;
    private String consultationId;

    private static class SilentPrinterIntegration extends PrinterIntegration {
        @Override
        public void printReceipt(ReceiptDTO receiptDTO) {
        }
    }

    @BeforeEach
    public void setUp() {
        customerRegistryIntegration = new CustomerRegistryIntegration();
        RepairOrderRegistryIntegration repairOrderRegistryIntegration = new RepairOrderRegistryIntegration();
        Mapper mapper = new Mapper();
        PrinterIntegration printerIntegration = new SilentPrinterIntegration();

        service = new Service(
                repairOrderRegistryIntegration,
                customerRegistryIntegration,
                printerIntegration,
                mapper
        );

        repairTask1 = new RepairTaskDTO(TASK_DESCRIPTION, TASK_COST);
        repairTask2 = new RepairTaskDTO(TASK_DESCRIPTION, TASK_COST);
        repairTasks = Arrays.asList(repairTask1, repairTask2);

        phoneNumber = "070123";

        CustomerDetailsEntity customer = customerRegistryIntegration.findCustomerEntityByPhoneNumber(phoneNumber);
        consultationId = customer.getConsultations().getFirst().getId();
    }



    @Test
    public void testFindCustomerByPhoneNumber_whenAllConsultationsHandled() throws CustomerNotFoundException, DatabaseFailureException {  //la till throws för d blev rött annars
        CustomerDetailsEntity customer = customerRegistryIntegration.findCustomerEntityByPhoneNumber(phoneNumber);

        for (var consultation : customer.getConsultations()) {
            service.createRepairOrder(consultation.getId(), "Test");
        }


        CustomerDetailsDTO result = service.findCustomerByPhoneNumber(phoneNumber);

        assertNull(result);
    }

    @Test
    void testFindCustomerByPhoneNumber_shouldThrowCustomerNotFoundException_whenCustomerDoesNotExist() throws DatabaseFailureException{
        String phoneNumber = "070789"; //måste va ett telefonnummer som ej finns
        CustomerNotFoundException exception = assertThrows(CustomerNotFoundException.class, () -> {service.findCustomerByPhoneNumber(phoneNumber);});
        assertEquals("Ingen kund kopplad till telefonnummer " + phoneNumber, exception.getMessage(), "error message does not match");
    }

    @Test
    public void testCreateRepairOrder() {
        service.createRepairOrder(consultationId, "Flat tire");

        List<PresentNewlyCreatedRepairOrderDTO> result = service.getAllNewlyCreatedRepairOrders();

        PresentNewlyCreatedRepairOrderDTO repairOrder = result.stream()
                .filter(o -> "Flat tire".equals(o.getProblemDescription()))
                .findFirst()
                .orElseThrow();

        assertEquals("Flat tire", repairOrder.getProblemDescription());
        assertEquals("070123", repairOrder.getPhoneNumber());
    }

    @Test
    public void testEnterDiagnosticReportAndRepairTasks() {
        service.createRepairOrder(consultationId, TASK_DESCRIPTION);
        String repairOrderId = getCreatedRepairOrderId();

        service.enterDiagnosticReportAndRepairTasks(repairOrderId, ORDER_PROBLEM_DESCRIPTION, repairTasks, LocalDateTime.of(2026, 4, 26, 14, 0));

        List<PresentRepairOrderForApprovalDTO> result = service.getAllReadyForApprovalOrders();
        PresentRepairOrderForApprovalDTO repairOrder = result.getFirst();

        assertEquals(1, result.size());
        assertEquals(repairOrderId, repairOrder.getRepairOrderId());
        assertEquals(2, repairOrder.proposedRepairTasks().size());
        assertEquals(ORDER_PROBLEM_DESCRIPTION, repairOrder.getDiagnosticReport().getDescription());
    }


    @Test
    public void testApproveRepairOrderAndGetReceipt() {
        service.createRepairOrder(consultationId, ORDER_PROBLEM_DESCRIPTION);
        String repairOrderId = getCreatedRepairOrderId();

        service.enterDiagnosticReportAndRepairTasks(repairOrderId, DIAGNOSTIC_REPORT_DESCRIPTION, repairTasks, LocalDateTime.of(2026, 4, 26, 14, 0));

        service.approveRepairOrder(repairOrderId);
        ReceiptDTO result = service.getReceipt(repairOrderId);

        assertNotNull(result);
        assertEquals(CUSTOMER_NAME, result.getName());
        assertEquals(ORDER_PROBLEM_DESCRIPTION, result.getProblemDescription());
        assertEquals(2 * TASK_COST, result.getTotalCost());
        assertEquals(2, result.getRepairTasks().size());
    }


    @Test
    public void testGetReceiptReturnsNullWhenRepairOrderIsNotApproved() {
        service.createRepairOrder(consultationId, "Flat tire");
        String repairOrderId = getCreatedRepairOrderId();

        ReceiptDTO result = service.getReceipt(repairOrderId);

        assertNull(result);
    }

    private String getCreatedRepairOrderId() {
        List<PresentNewlyCreatedRepairOrderDTO> createdOrders = service.getAllNewlyCreatedRepairOrders();
        return createdOrders.getFirst().getRepairOrderId();
    }
}