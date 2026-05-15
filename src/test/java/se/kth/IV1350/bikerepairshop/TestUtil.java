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
        DiagnosticReport diagnosticReport = new DiagnosticReport.Builder()
                .description(TASK_DESCRIPTION)
                .estimatedRepairTime(TASK_ESTIMATED_REPAIR_TIME)
                .build();
        RepairTask repairTask = new RepairTask.Builder()
                .cost(TASK_COST)
                .description(TASK_DESCRIPTION)
                .build();
        return new RepairOrder.Builder()
                .date(DATE)
                .problemDescription(ORDER_PROBLEM_DESCRIPTION)
                .state(ORDER_STATE)
                .customerDetails(customerDetails)
                .repairTasks(List.of(repairTask))
                .diagnosticReport(diagnosticReport)
                .id(ORDER_ID)
                .build();
    }

    public static CustomerDetails createCustomerDetails () {
        BikeDetails bikeDetails = new BikeDetails.Builder()
                .brand(BIKE_BRAND)
                .model(BIKE_MODEL)
                .serialNumber(BIKE_SERIAL_NUMBER)
                .build();

        return new CustomerDetails.Builder()
                .name(CUSTOMER_NAME)
                .email(CUSTOMER_EMAIL)
                .phoneNumber(CUSTOMER_PHONE)
                .bikeDetails(bikeDetails)
                .consultationId(CONSULTATION_ID)
                .build();
    }

    public static RepairOrderEntity createRepairOrderEntity (){
        // Arrange
        RepairTaskEntity repairTaskEntity = new RepairTaskEntity.Builder()
                .description(TASK_DESCRIPTION)
                .cost(TASK_COST)
                .build();
        DiagnosticReportEntity diagnosticReportEntity = new DiagnosticReportEntity.Builder()
                .description(TASK_DESCRIPTION)
                .estimatedRepairTime(TASK_ESTIMATED_REPAIR_TIME)
                .build();
        return new RepairOrderEntity.Builder()
                .date(DATE)
                .problemDescription(ORDER_PROBLEM_DESCRIPTION)
                .state(ORDER_STATE.name())
                .diagnosticReport(diagnosticReportEntity)
                .repairTasks(List.of(repairTaskEntity))
                .bikeBrand(BIKE_BRAND)
                .bikeSerialNumber(BIKE_SERIAL_NUMBER)
                .bikeModel(BIKE_MODEL)
                .consultationId(CONSULTATION_ID)
                .id(ORDER_ID)
                .name(CUSTOMER_NAME)
                .customerPhoneNumber(CUSTOMER_PHONE)
                .email(CUSTOMER_EMAIL)
                .build();
    }

    public static RepairOrderEntity createRepairOrderEntityWithId (String id){
        // Arrange
        RepairTaskEntity repairTaskEntity = new RepairTaskEntity.Builder()
                .description(TASK_DESCRIPTION)
                .cost(TASK_COST)
                .build();
        DiagnosticReportEntity diagnosticReportEntity = new DiagnosticReportEntity.Builder()
                .description(TASK_DESCRIPTION)
                .estimatedRepairTime(TASK_ESTIMATED_REPAIR_TIME)
                .build();
        return new RepairOrderEntity.Builder()
                .date(DATE)
                .problemDescription(ORDER_PROBLEM_DESCRIPTION)
                .state(ORDER_STATE.name())
                .diagnosticReport(diagnosticReportEntity)
                .repairTasks(List.of(repairTaskEntity))
                .bikeBrand(BIKE_BRAND)
                .bikeSerialNumber(BIKE_SERIAL_NUMBER)
                .bikeModel(BIKE_MODEL)
                .consultationId(CONSULTATION_ID)
                .id(id)
                .name(CUSTOMER_NAME)
                .customerPhoneNumber(CUSTOMER_PHONE)
                .email(CUSTOMER_EMAIL)
                .build();
    }

    public static RepairOrderEntity createRepairOrderEntity (String state){
        // Arrange
        RepairTaskEntity repairTaskEntity = new RepairTaskEntity.Builder()
                .description(TASK_DESCRIPTION)
                .cost(TASK_COST)
                .build();
        DiagnosticReportEntity diagnosticReportEntity = new DiagnosticReportEntity.Builder()
                .description(TASK_DESCRIPTION)
                .estimatedRepairTime(TASK_ESTIMATED_REPAIR_TIME)
                .build();
        return new RepairOrderEntity.Builder()
                .date(DATE)
                .problemDescription(ORDER_PROBLEM_DESCRIPTION)
                .state(state)
                .diagnosticReport(diagnosticReportEntity)
                .repairTasks(List.of(repairTaskEntity))
                .bikeBrand(BIKE_BRAND)
                .bikeSerialNumber(BIKE_SERIAL_NUMBER)
                .bikeModel(BIKE_MODEL)
                .consultationId(CONSULTATION_ID)
                .id(ORDER_ID)
                .name(CUSTOMER_NAME)
                .customerPhoneNumber(CUSTOMER_PHONE)
                .email(CUSTOMER_EMAIL)
                .build();
    }

}
