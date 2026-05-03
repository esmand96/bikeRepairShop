package bikerepairshop.service;

import bikerepairshop.integration.CustomerRegistryIntegration;
import bikerepairshop.integration.PrinterIntegration;
import bikerepairshop.integration.RepairOrderRegistryIntegration;
import bikerepairshop.model.dto.CustomerDetailsDTO;
import bikerepairshop.model.dto.PresentNewlyCreatedRepairOrderDTO;
import bikerepairshop.model.dto.PresentRepairOrderForApprovalDTO;
import bikerepairshop.model.dto.ReceiptDTO;
import bikerepairshop.model.dto.common.RepairTaskDTO;
import bikerepairshop.model.entity.CustomerDetailsEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ServiceTest {

    private Service service;
    private CustomerRegistryIntegration customerRegistryIntegration;

    // Reusable test data
    private RepairTaskDTO repairTask1;
    private RepairTaskDTO repairTask2;
    private List<RepairTaskDTO> repairTasks;
    private String phoneNumber;
    private String consultationId;

    // ej skriver ut output
    private static class SilentPrinterIntegration extends PrinterIntegration {
        @Override
        public void printReceipt(ReceiptDTO receiptDTO) {
        }
    }
    // testar att en kund kan hittas via telefonnummer och att första obehandlade konsultationen returneras

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

        repairTask1 = new RepairTaskDTO("Replace tire", 500.0);
        repairTask2 = new RepairTaskDTO("Adjust brakes", 150.0);
        repairTasks = Arrays.asList(repairTask1, repairTask2);

        phoneNumber = "070123";

        CustomerDetailsEntity customer = customerRegistryIntegration.findCustomerEntityByPhoneNumber(phoneNumber);
        consultationId = customer.getConsultations().getFirst().getId();
    }



    @Test
    public void testFindCustomerByPhoneNumber_whenAllConsultationsHandled() {
        CustomerDetailsEntity customer = customerRegistryIntegration.findCustomerEntityByPhoneNumber(phoneNumber);

        for (var consultation : customer.getConsultations()) {
            service.createRepairOrder(consultation.getId(), "Test");
        }


        CustomerDetailsDTO result = service.findCustomerByPhoneNumber(phoneNumber);

        assertNull(result);
    }

    // testar att en repairorder är skapad och listas bland newly created orders
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

    // testar att inskriven diagreport och repairtasks gör repairorder ready for approval
    @Test
    public void testEnterDiagnosticReportAndRepairTasks() {
        service.createRepairOrder(consultationId, "Flat tire");
        String repairOrderId = getCreatedRepairOrderId();

        service.enterDiagnosticReportAndRepairTasks(repairOrderId, "Puncture confirmed", repairTasks, LocalDateTime.of(2026, 4, 26, 14, 0));

        List<PresentRepairOrderForApprovalDTO> result = service.getAllReadyForApprovalOrders();
        PresentRepairOrderForApprovalDTO repairOrder = result.getFirst();

        assertEquals(1, result.size());
        assertEquals(repairOrderId, repairOrder.getRepairOrderId());
        assertEquals(2, repairOrder.proposedRepairTasks().size());
        assertEquals("Puncture confirmed", repairOrder.getDiagnosticReport().getDescription());
    }

    // testar att receipt går att hämta efter att repairorder blivit accepterad
    @Test
    public void testApproveRepairOrderAndGetReceipt() {
        service.createRepairOrder(consultationId, "Flat tire");
        String repairOrderId = getCreatedRepairOrderId();

        service.enterDiagnosticReportAndRepairTasks(repairOrderId, "Puncture confirmed", repairTasks, LocalDateTime.of(2026, 4, 26, 14, 0));

        service.approveRepairOrder(repairOrderId);
        ReceiptDTO result = service.getReceipt(repairOrderId);

        assertNotNull(result);
        assertEquals("Esmeralda", result.getName());
        assertEquals("Flat tire", result.getProblemDescription());
        assertEquals(650.0, result.getTotalCost());
        assertEquals(2, result.getRepairTasks().size());
    }

    // testar att inget kvitto returneras om order ej blivit approved
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