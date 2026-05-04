package se.kth.IV1350.bikerepairshop;

import se.kth.IV1350.bikerepairshop.model.domain.*;
import se.kth.IV1350.bikerepairshop.model.entity.DiagnosticReportEntity;
import se.kth.IV1350.bikerepairshop.model.entity.RepairOrderEntity;
import se.kth.IV1350.bikerepairshop.model.entity.RepairTaskEntity;

import java.time.LocalDateTime;
import java.util.List;

public class TestUtil {
    public static final double TASK_COST = 123.45;
    public static final String TASK_DESCRIPTION = "change breaks";
    public static final LocalDateTime TASK_ESTIMATED_REPAIR_TIME = LocalDateTime.now();
    public static final String ORDER_ID = "IDfirst123";
    public static final String DATE = "2026-05-03";
    public static final String DIAGNOSTIC_REPORT_DESCRIPTION = "Bike diagnostic";
    public static final String ORDER_PROBLEM_DESCRIPTION = "Bike makes noise";
    public static final RepairOrderState ORDER_STATE = RepairOrderState.NEWLY_CREATED;
    public static final String CUSTOMER_NAME = "Customer Customersson";
    public static final String CUSTOMER_PHONE = "07012345";
    public static final String CUSTOMER_EMAIL = "janeDoe@example.com";
    public static final String CONSULTATION_ID = "consult-1";
    public static final String BIKE_SERIAL_NUMBER = "SN-12345";
    public static final String BIKE_BRAND = "Crescent";
    public static final String BIKE_MODEL = "Elda";

    public static RepairOrder createRepairOrder(CustomerDetails customerDetails){
        DiagnosticReport diagnosticReport = new DiagnosticReport(TASK_DESCRIPTION, TASK_ESTIMATED_REPAIR_TIME);
        RepairTask repairTask = new RepairTask(TASK_COST, TASK_DESCRIPTION);
        return new RepairOrder(
                DATE,
                ORDER_PROBLEM_DESCRIPTION,
                ORDER_STATE,
                customerDetails,
                List.of(repairTask),
                diagnosticReport,
                ORDER_ID
        );
    }

    public static CustomerDetails createCustomerDetails () {
        BikeDetails bikeDetails = new BikeDetails(BIKE_BRAND, BIKE_MODEL, BIKE_SERIAL_NUMBER);

        return new CustomerDetails(CUSTOMER_NAME, CUSTOMER_EMAIL, CUSTOMER_PHONE, bikeDetails, CONSULTATION_ID);
    }

    public static RepairOrderEntity createRepairOrderEntity (){
        // Arrange
        RepairTaskEntity repairTaskEntity = new RepairTaskEntity(TASK_DESCRIPTION, TASK_COST);
        DiagnosticReportEntity diagnosticReportEntity = new DiagnosticReportEntity(TASK_DESCRIPTION, TASK_ESTIMATED_REPAIR_TIME);
        return  new RepairOrderEntity(
                DATE,
                ORDER_PROBLEM_DESCRIPTION,
                ORDER_STATE.name(),
                diagnosticReportEntity,
                List.of(repairTaskEntity),
                BIKE_BRAND,
                BIKE_SERIAL_NUMBER,
                BIKE_MODEL,
                CONSULTATION_ID,
                ORDER_ID,
                CUSTOMER_NAME,
                CUSTOMER_PHONE,
                CUSTOMER_EMAIL
        );
    }

    public static RepairOrderEntity createRepairOrderEntityWithId (String id){
        // Arrange
        RepairTaskEntity repairTaskEntity = new RepairTaskEntity(TASK_DESCRIPTION, TASK_COST);
        DiagnosticReportEntity diagnosticReportEntity = new DiagnosticReportEntity(TASK_DESCRIPTION, TASK_ESTIMATED_REPAIR_TIME);
        return  new RepairOrderEntity(
                DATE,
                ORDER_PROBLEM_DESCRIPTION,
                ORDER_STATE.name(),
                diagnosticReportEntity,
                List.of(repairTaskEntity),
                BIKE_BRAND,
                BIKE_SERIAL_NUMBER,
                BIKE_MODEL,
                CONSULTATION_ID,
                id,
                CUSTOMER_NAME,
                CUSTOMER_PHONE,
                CUSTOMER_EMAIL
        );
    }
    public static RepairOrderEntity createRepairOrderEntity (String state){
        // Arrange
        RepairTaskEntity repairTaskEntity = new RepairTaskEntity(TASK_DESCRIPTION, TASK_COST);
        DiagnosticReportEntity diagnosticReportEntity = new DiagnosticReportEntity(TASK_DESCRIPTION, TASK_ESTIMATED_REPAIR_TIME);
        return  new RepairOrderEntity(
                DATE,
                ORDER_PROBLEM_DESCRIPTION,
                state,
                diagnosticReportEntity,
                List.of(repairTaskEntity),
                BIKE_BRAND,
                BIKE_SERIAL_NUMBER,
                BIKE_MODEL,
                CONSULTATION_ID,
                ORDER_ID,
                CUSTOMER_NAME,
                CUSTOMER_PHONE,
                CUSTOMER_EMAIL
        );
    }

}
