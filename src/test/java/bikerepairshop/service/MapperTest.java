package bikerepairshop.service;

import bikerepairshop.model.domain.*;
import bikerepairshop.model.entity.RepairOrderEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapperTest {

    private Mapper mapper;

    // Reusable test data
    private BikeDetails bikeDetails;
    private CustomerDetails customerDetails;
    private RepairTask repairTask1;
    private RepairTask repairTask2;
    private DiagnosticReport diagnosticReport;
    private RepairOrder repairOrder;
    RepairOrderEntity repairOrderEntity;
    @BeforeEach
    public void setUp() {
        mapper = new Mapper();

        bikeDetails = new BikeDetails("Monark", "SN1", "E-Karin");
        customerDetails = new CustomerDetails(
                "Ali", "ali@kth.se", "0700000000", bikeDetails, "consult-1");

        repairTask1 = new RepairTask(500.0, "Replace tire");
        repairTask2 = new RepairTask(150.0, "Adjust brakes");

        diagnosticReport = new DiagnosticReport(
                "Puncture confirmed",
                LocalDateTime.of(2026, 4, 26, 14, 0));

        repairOrder = new RepairOrder(
                "2026-04-25T10:00",
                "Flat tire",
                RepairOrderState.READY_FOR_APPROVAL,
                customerDetails,
                Arrays.asList(repairTask1, repairTask2),
                diagnosticReport,
                "order-1");

        repairOrderEntity =  new RepairOrderEntity(
                "2026-04-25",
                "Flat tire",
                "READY_FOR_APPROVAL",
                null, null,
                "0700000000",
                "Monark",
                "SN1",
                "consult-1",
                "order-1",
                "E-Karin",
                "Ali",
                "ali@kth.se");
    }

    @Test
    public void testRepairOrderEntityToBikeDetails() {
        BikeDetails result = mapper.ENTITY.toBikeDetails(repairOrderEntity);
        assertEquals(repairOrderEntity.getBikeBrand(), result.getBrand());
        assertEquals(repairOrderEntity.getBikeSerialNumber(), result.getSerialNumber());
        assertEquals(repairOrderEntity.getBikeModel(), result.getModel());
    }


    @Test
    public void testRepairOrderEntityToDomain() {
        RepairOrder result = mapper.ENTITY.toDomain(repairOrderEntity);
        assertEquals(repairOrderEntity.getBikeBrand(), result.getCustomerDetails().getBikeDetails().getBrand());
        assertEquals(repairOrderEntity.getBikeSerialNumber(),  result.getCustomerDetails().getBikeDetails().getSerialNumber());
        assertEquals(repairOrderEntity.getBikeModel(),  result.getCustomerDetails().getBikeDetails().getModel());
        assertEquals(repairOrderEntity.getEmail(), result.getCustomerDetails().getEmail());
        assertEquals(repairOrderEntity.getName(),  result.getCustomerDetails().getName());
        assertEquals(repairOrderEntity.getCustomerPhoneNumber(),  result.getCustomerDetails().getPhoneNumber());
    }
}
