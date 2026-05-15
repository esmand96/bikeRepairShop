package se.kth.IV1350.bikerepairshop.service;


import se.kth.IV1350.bikerepairshop.model.domain.*;
import se.kth.IV1350.bikerepairshop.model.dto.common.CustomerDetailsDTO;
import se.kth.IV1350.bikerepairshop.model.dto.common.PresentNewlyCreatedRepairOrderDTO;
import se.kth.IV1350.bikerepairshop.model.dto.common.PresentRepairOrderForApprovalDTO;
import se.kth.IV1350.bikerepairshop.model.dto.common.ReceiptDTO;
import se.kth.IV1350.bikerepairshop.model.dto.common.RepairTaskDTO;
import org.junit.jupiter.api.Test;
import se.kth.IV1350.bikerepairshop.model.entity.*;

import java.util.List;

import static se.kth.IV1350.bikerepairshop.TestUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapperTest {
    private static final Mapper mapper = new Mapper();

    @Test
    void repairTaskDTOToDomain_shouldMapCostAndDescription_forSingleDto() {
        RepairTaskDTO dto = RepairTaskDTO.builder()
                .description(TASK_DESCRIPTION)
                .cost(TASK_COST)
                .build();
        List<RepairTaskDTO> dtos = List.of(dto);

        List<RepairTask> result = mapper.DTO.repairTaskDTOToDomain(dtos);

        assertEquals(1, result.size());
        assertEquals(TASK_COST, result.get(0).getCost());
        assertEquals(TASK_DESCRIPTION, result.get(0).getDescription());
    }

    @Test
    void repairTaskEntityToDomain_shouldMapCostAndDescription_forSingleEntity() {
        RepairTaskEntity entity = RepairTaskEntity.builder()
                .description(TASK_DESCRIPTION)
                .cost(TASK_COST)
                .build();
        List<RepairTaskEntity> entities = List.of(entity);

        List<RepairTask> result = mapper.ENTITY.repairTaskEntityToDomain(entities);

        assertEquals(1, result.size());
        assertEquals(TASK_COST, result.get(0).getCost());
        assertEquals(TASK_DESCRIPTION, result.get(0).getDescription());
    }

    @Test
    void diagnosticReportEntityToDomain_shouldMapDescriptionAndEstimatedRepairTime_forDiagnosticReportEntity() {
        DiagnosticReportEntity entity = DiagnosticReportEntity.builder()
                .description(TASK_DESCRIPTION)
                .estimatedRepairTime(TASK_ESTIMATED_REPAIR_TIME)
                .build();

        DiagnosticReport result = mapper.ENTITY.diagnosticReportEntityToDomain(entity);

        assertEquals(TASK_DESCRIPTION, result.getDescription());
        assertEquals(TASK_ESTIMATED_REPAIR_TIME, result.getEstimatedRepairTime());
    }

    @Test
    void repairOrderEntityToDomain_shouldMapAllFields_forRepairOrderEntity() {


        RepairOrderEntity entity = createRepairOrderEntity();

        RepairOrder result = mapper.ENTITY.repairOrderEntityToDomain(entity);

        assertEquals(ORDER_ID, result.getId());
        assertEquals(DATE, result.getDate());
        assertEquals(ORDER_PROBLEM_DESCRIPTION, result.getProblemDescription());
        assertEquals(ORDER_STATE, result.getState());
        assertEquals(CUSTOMER_NAME, result.getCustomerDetails().getName());
        assertEquals(CUSTOMER_PHONE, result.getCustomerDetails().getPhoneNumber());
        assertEquals(CUSTOMER_EMAIL, result.getCustomerDetails().getEmail());
        assertEquals(1, result.getRepairTasks().size());
        assertEquals(TASK_COST, result.getRepairTasks().get(0).getCost());
        assertEquals(TASK_DESCRIPTION, result.getRepairTasks().get(0).getDescription());
        assertEquals(TASK_DESCRIPTION, result.getDiagnosticReport().getDescription());
        assertEquals(TASK_ESTIMATED_REPAIR_TIME, result.getDiagnosticReport().getEstimatedRepairTime());
    }

    @Test
    void repairOrderEntityToBikeDetails_shouldMapBrandModelAndSerialNumber_fromRepairOrderEntity() {
        RepairOrderEntity entity = createRepairOrderEntity();

        BikeDetails result = mapper.ENTITY.repairOrderEntityToBikeDetails(entity);

        assertEquals(BIKE_BRAND, result.getBrand());
        assertEquals(BIKE_MODEL, result.getModel());
        assertEquals(BIKE_SERIAL_NUMBER, result.getSerialNumber());
    }

    @Test
    void repairTaskToEntity_shouldMapDescriptionAndCost_forSingleRepairTask() {
        RepairTask repairTask = RepairTask.builder()
                .cost(TASK_COST)
                .description(TASK_DESCRIPTION)
                .build();
        List<RepairTask> repairTasks = List.of(repairTask);

        List<RepairTaskEntity> result = mapper.DOMAIN.repairTaskToEntity(repairTasks);

        assertEquals(1, result.size());
        assertEquals(TASK_DESCRIPTION, result.get(0).getDescription());
        assertEquals(TASK_COST, result.get(0).getCost());
    }

    @Test
    void diagnosticReportToEntity_shouldMapDescriptionAndEstimatedRepairTime_forDiagnosticReport() {
        DiagnosticReport diagnosticReport = DiagnosticReport.builder()
                .description(TASK_DESCRIPTION)
                .estimatedRepairTime(TASK_ESTIMATED_REPAIR_TIME)
                .build();

        DiagnosticReportEntity result = mapper.DOMAIN.diagnosticReportToEntity(diagnosticReport);

        assertEquals(TASK_DESCRIPTION, result.getDescription());
        assertEquals(TASK_ESTIMATED_REPAIR_TIME, result.getEstimatedRepairTime());
    }

    @Test
    void createPresentRepairOrderForApprovalDTO_shouldMapAllFields() {

        BikeDetails bikeDetails = BikeDetails.builder()
                .brand(BIKE_BRAND)
                .model(BIKE_MODEL)
                .serialNumber(BIKE_SERIAL_NUMBER)
                .build();
        CustomerDetails customerDetails = CustomerDetails.builder()
                .name(CUSTOMER_NAME)
                .email(CUSTOMER_EMAIL)
                .phoneNumber(CUSTOMER_PHONE)
                .bikeDetails(bikeDetails)
                .consultationId(CONSULTATION_ID)
                .build();

        RepairOrder repairOrder = createRepairOrder(customerDetails);


        double totalCost = TASK_COST;

        PresentRepairOrderForApprovalDTO result = mapper.DOMAIN.createPresentRepairOrderForApprovalDTO(repairOrder, totalCost);

        assertEquals(ORDER_ID, result.getRepairOrderId());
        assertEquals(totalCost, result.getTotalCost());
        assertEquals(CUSTOMER_PHONE, result.getCustomerPhoneNumber());
        assertEquals(BIKE_BRAND, result.getBikeBrand());
        assertEquals(BIKE_MODEL, result.getBikeModel());
        assertEquals(BIKE_SERIAL_NUMBER, result.getBikeSerialNumber());
        assertEquals(TASK_DESCRIPTION, result.getDiagnosticReport().getDescription());
        assertEquals(TASK_ESTIMATED_REPAIR_TIME, result.getDiagnosticReport().getEstimatedRepairTime());
        assertEquals(1, result.proposedRepairTasks().size());
        assertEquals(TASK_COST, result.proposedRepairTasks().get(0).getCost());
        assertEquals(TASK_DESCRIPTION, result.proposedRepairTasks().get(0).getDescription());
    }

    @Test
    void createPresentNewlyCreatedRepairOrderDTO_shouldMapAllFields() {
        CustomerDetails customerDetails = createCustomerDetails();

        RepairOrder repairOrder = createRepairOrder(customerDetails);

        PresentNewlyCreatedRepairOrderDTO result = mapper.DOMAIN.createPresentNewlyCreatedRepairOrderDTO(repairOrder);

        assertEquals(CUSTOMER_NAME, result.getName());
        assertEquals(CUSTOMER_EMAIL, result.getEmail());
        assertEquals(CUSTOMER_PHONE, result.getPhoneNumber());
        assertEquals(BIKE_BRAND, result.getBikeBrand());
        assertEquals(BIKE_MODEL, result.getBikeModel());
        assertEquals(BIKE_SERIAL_NUMBER, result.getBikeSerialNumber());
        assertEquals(ORDER_PROBLEM_DESCRIPTION, result.getProblemDescription());
        assertEquals(ORDER_STATE.name(), result.getState());
        assertEquals(ORDER_ID, result.getRepairOrderId());
    }

    @Test
    void createReceiptDTO_shouldMapAllFields() {
        CustomerDetails customerDetails = createCustomerDetails();
        RepairOrder repairOrder = createRepairOrder(customerDetails);
        double totalCost = TASK_COST;

        ReceiptDTO result = mapper.DOMAIN.createReceiptDTO(repairOrder, totalCost);

        assertEquals(CUSTOMER_NAME, result.getName());
        assertEquals(CUSTOMER_EMAIL, result.getEmail());
        assertEquals(CUSTOMER_PHONE, result.getPhoneNumber());
        assertEquals(BIKE_BRAND, result.getBikeBrand());
        assertEquals(BIKE_MODEL, result.getBikeModel());
        assertEquals(BIKE_SERIAL_NUMBER, result.getBikeSerialNumber());
        assertEquals(ORDER_PROBLEM_DESCRIPTION, result.getProblemDescription());
        assertEquals(ORDER_STATE.name(), result.getState());
        assertEquals(totalCost, result.getTotalCost());
        assertEquals(TASK_DESCRIPTION, result.getDiagnosticReport().getDescription());
        assertEquals(TASK_ESTIMATED_REPAIR_TIME, result.getDiagnosticReport().getEstimatedRepairTime());
        assertEquals(1, result.getRepairTasks().size());
        assertEquals(TASK_COST, result.getRepairTasks().get(0).getCost());
        assertEquals(TASK_DESCRIPTION, result.getRepairTasks().get(0).getDescription());
    }

    @Test
    void repairOrderToEntity_shouldMapAllFields_forRepairOrder() {
        CustomerDetails customerDetails = createCustomerDetails();
        RepairOrder repairOrder = createRepairOrder(customerDetails);

        RepairOrderEntity result = mapper.DOMAIN.repairOrderToEntity(repairOrder);

        assertEquals(ORDER_ID, result.getId());
        assertEquals(DATE, result.getDate());
        assertEquals(ORDER_PROBLEM_DESCRIPTION, result.getProblemDescription());
        assertEquals(ORDER_STATE.name(), result.getState());
        assertEquals(CUSTOMER_NAME, result.getName());
        assertEquals(CUSTOMER_EMAIL, result.getEmail());
        assertEquals(CUSTOMER_PHONE, result.getCustomerPhoneNumber());
        assertEquals(BIKE_BRAND, result.getBikeBrand());
        assertEquals(BIKE_MODEL, result.getBikeModel());
        assertEquals(BIKE_SERIAL_NUMBER, result.getBikeSerialNumber());
        assertEquals(CONSULTATION_ID, result.getConsultationId());
        assertEquals(1, result.getRepairTasks().size());
        assertEquals(TASK_COST, result.getRepairTasks().get(0).getCost());
        assertEquals(TASK_DESCRIPTION, result.getRepairTasks().get(0).getDescription());
        assertEquals(TASK_DESCRIPTION, result.getDiagnosticReport().getDescription());
        assertEquals(TASK_ESTIMATED_REPAIR_TIME, result.getDiagnosticReport().getEstimatedRepairTime());
    }

    @Test
    void customerDetailsToDTO_shouldMapAllFields() {
        CustomerDetails customerDetails = createCustomerDetails();

        CustomerDetailsDTO result = mapper.DOMAIN.customerDetailsToDTO(customerDetails);

        assertEquals(CUSTOMER_NAME, result.getName());
        assertEquals(CUSTOMER_EMAIL, result.getEmail());
        assertEquals(CUSTOMER_PHONE, result.getPhoneNumber());
        assertEquals(BIKE_BRAND, result.getBikeBrand());
        assertEquals(BIKE_MODEL, result.getBikeModel());
        assertEquals(BIKE_SERIAL_NUMBER, result.getBikeSerialNumber());
        assertEquals(CONSULTATION_ID, result.getConsultationId());
    }

    @Test
    void bikeRepairConsultationEntityToBikeDetails_shouldMapBrandModelAndSerialNumber_fromBikeRepairConsultationEntity() {
        BikeRepairConsultationEntity consultationEntity = BikeRepairConsultationEntity.builder()
                .date(DATE)
                .id(CONSULTATION_ID)
                .model(BIKE_MODEL)
                .brand(BIKE_BRAND)
                .serialNumber(BIKE_SERIAL_NUMBER)
                .build();

        BikeDetails result = mapper.ENTITY.bikeRepairConsultationEntityToBikeDetails(consultationEntity);

        assertEquals(BIKE_BRAND, result.getBrand());
        assertEquals(BIKE_MODEL, result.getModel());
        assertEquals(BIKE_SERIAL_NUMBER, result.getSerialNumber());
    }

    @Test
    void mergeCustomerDetailsEntityAndBikeConsultationEntityToCustomerDetails_shouldMapAllFields() {
        BikeRepairConsultationEntity consultationEntity = BikeRepairConsultationEntity.builder()
                .date(DATE)
                .id(CONSULTATION_ID)
                .model(BIKE_MODEL)
                .brand(BIKE_BRAND)
                .serialNumber(BIKE_SERIAL_NUMBER)
                .build();

        CustomerDetailsEntity customerDetailsEntity = CustomerDetailsEntity.builder()
                .name(CUSTOMER_NAME)
                .email(CUSTOMER_EMAIL)
                .phoneNumber(CUSTOMER_PHONE)
                .consultations(List.of(consultationEntity))
                .build();

        CustomerDetails result = mapper.ENTITY.mergeCustomerDetailsEntityAndBikeConsultationEntityToCustomerDetails(
                customerDetailsEntity, consultationEntity
        );

        assertEquals(CUSTOMER_NAME, result.getName());
        assertEquals(CUSTOMER_EMAIL, result.getEmail());
        assertEquals(CUSTOMER_PHONE, result.getPhoneNumber());
        assertEquals(BIKE_BRAND, result.getBikeDetails().getBrand());
        assertEquals(BIKE_MODEL, result.getBikeDetails().getModel());
        assertEquals(BIKE_SERIAL_NUMBER, result.getBikeDetails().getSerialNumber());
        assertEquals(CONSULTATION_ID, result.getConsultationId());
    }


}



