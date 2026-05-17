package se.kth.IV1350.bikerepairshop.service;

import se.kth.IV1350.bikerepairshop.model.domain.*;
import se.kth.IV1350.bikerepairshop.model.dto.*;
import se.kth.IV1350.bikerepairshop.model.entity.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Performs all conversions between the three model representations
 * ({@code domain}, {@code dto} and {@code entity}). Conversions are grouped into three
 * inner classes named after the source type of the conversion: {@code Dto},
 * {@code Entity} and {@code Domain}. The corresponding entry points are accessed via
 * the public fields {@code DTO}, {@code ENTITY} and {@code DOMAIN}.
 */
public class Mapper {
    /**
     * Entry point for conversions from domain objects.
     */
    final Domain DOMAIN = new Mapper.Domain();

    /**
     * Entry point for conversions from entity objects.
     */
    final Entity ENTITY = new Mapper.Entity();

    /**
     * Entry point for conversions from DTO objects.
     */
    final Dto DTO = new Mapper.Dto();

    /**
     * Creates a new instance.
     */
    public Mapper() {
    }

    /**
     * Holds all conversions where the source type is a DTO.
     */
    static class Dto {

        /**
         * Converts the specified list of repair task DTOs to a list of domain repair tasks.
         *
         * @param repairTaskDTOS The repair task DTOs to convert.
         * @return A list of domain repair tasks corresponding to the input.
         */
        List<RepairTask> repairTaskDTOToDomain(List<RepairTaskDTO> repairTaskDTOS) {
            List<RepairTask> repairTasks = new ArrayList<>();
            for (RepairTaskDTO repairTaskDTO : repairTaskDTOS) {
                RepairTask repairTask = RepairTask.builder()
                        .cost(repairTaskDTO.getCost())
                        .description(repairTaskDTO.getDescription())
                        .build();
                repairTasks.add(repairTask);
            }
            return repairTasks;
        }
    }

    /**
     * Holds all conversions where the source type is an entity.
     */
    static class Entity {

        /**
         * Converts the specified list of repair task entities to a list of domain repair tasks.
         *
         * @param repairTaskEntities The repair task entities to convert, or {@code null}.
         * @return A list of domain repair tasks, or an empty list if the input is {@code null}.
         */
        List<RepairTask> repairTaskEntityToDomain(List<RepairTaskEntity> repairTaskEntities) {
            List<RepairTask> repairTasks = new ArrayList<>();
            if (repairTaskEntities == null)
                return repairTasks;

            for (RepairTaskEntity repairTaskEntity : repairTaskEntities) {
                RepairTask repairTask = RepairTask.builder()
                        .cost(repairTaskEntity.getCost())
                        .description(repairTaskEntity.getDescription())
                        .build();
                repairTasks.add(repairTask);
            }
            return repairTasks;
        }

        /**
         * Converts the specified diagnostic report entity to a domain diagnostic report.
         *
         * @param diagnosticReportEntity The entity to convert, or {@code null}.
         * @return The corresponding domain diagnostic report, or {@code null} if the input is {@code null}.
         */
        DiagnosticReport diagnosticReportEntityToDomain(DiagnosticReportEntity diagnosticReportEntity) {
            if (diagnosticReportEntity == null)
                return null;

            return DiagnosticReport.builder()
                    .description(diagnosticReportEntity.getDescription())
                    .estimatedRepairTime(diagnosticReportEntity.getEstimatedRepairTime())
                    .build();
        }

        /**
         * Converts the specified repair order entity to a domain repair order, including
         * its customer details, repair tasks and diagnostic report.
         *
         * @param repairOrderEntity The repair order entity to convert.
         * @return The corresponding domain repair order.
         */
        RepairOrder repairOrderEntityToDomain(RepairOrderEntity repairOrderEntity) {
            RepairOrderState repairOrderState = RepairOrderState.valueOf(repairOrderEntity.getState());
            CustomerDetails customerDetails = repairOrderEntityToCustomerDetails(repairOrderEntity);
            String date = repairOrderEntity.getDate();
            List<RepairTask> repairTasks = repairTaskEntityToDomain(repairOrderEntity.getRepairTasks());
            DiagnosticReport diagnosticReport = diagnosticReportEntityToDomain(repairOrderEntity.getDiagnosticReport());

            return RepairOrder.builder()
                    .date(date)
                    .problemDescription(repairOrderEntity.getProblemDescription())
                    .state(repairOrderState)
                    .customerDetails(customerDetails)
                    .repairTasks(repairTasks)
                    .diagnosticReport(diagnosticReport)
                    .id(repairOrderEntity.getId())
                    .build();
        }

        /**
         * Extracts the bike data from the specified repair order entity and creates a
         * domain bike details object.
         *
         * @param repairOrderEntity The repair order entity to extract data from.
         * @return A domain bike details object with the extracted data.
         */
        BikeDetails repairOrderEntityToBikeDetails(RepairOrderEntity repairOrderEntity) {
            String serialNumber = repairOrderEntity.getBikeSerialNumber();
            String brand = repairOrderEntity.getBikeBrand();
            String model = repairOrderEntity.getBikeModel();
            return BikeDetails.builder()
                    .brand(brand)
                    .model(model)
                    .serialNumber(serialNumber)
                    .build();
        }

        /**
         * Extracts the bike data from the specified consultation entity and creates a
         * domain bike details object.
         *
         * @param consultationEntity The consultation entity to extract data from.
         * @return A domain bike details object with the extracted data.
         */
        BikeDetails bikeRepairConsultationEntityToBikeDetails(BikeRepairConsultationEntity consultationEntity) {
            String serialNumber = consultationEntity.getSerialNumber();
            String brand = consultationEntity.getBrand();
            String model = consultationEntity.getModel();
            return BikeDetails.builder()
                    .brand(brand)
                    .model(model)
                    .serialNumber(serialNumber)
                    .build();
        }

        /**
         * Extracts customer and bike data from the specified repair order entity and
         * creates a domain customer details object.
         *
         * @param repairOrderEntity The repair order entity to extract data from.
         * @return A domain customer details object with the extracted data.
         */
        CustomerDetails repairOrderEntityToCustomerDetails(RepairOrderEntity repairOrderEntity) {
            String phoneNumber = repairOrderEntity.getCustomerPhoneNumber();
            BikeDetails bikeDetails = repairOrderEntityToBikeDetails(repairOrderEntity);
            String name = repairOrderEntity.getName();
            String email = repairOrderEntity.getEmail();
            return CustomerDetails.builder()
                    .name(name)
                    .email(email)
                    .phoneNumber(phoneNumber)
                    .bikeDetails(bikeDetails)
                    .consultationId(repairOrderEntity.getConsultationId())
                    .build();
        }

        /**
         * Combines a customer entity with a consultation entity into a single domain
         * customer details object.
         *
         * @param customerDetailsEntity The customer entity, or {@code null}.
         * @param consultationEntity    The consultation entity, or {@code null}.
         * @return A domain customer details object combining the input, or {@code null}
         * if either input is {@code null}.
         */
        CustomerDetails mergeCustomerDetailsEntityAndBikeConsultationEntityToCustomerDetails(CustomerDetailsEntity customerDetailsEntity, BikeRepairConsultationEntity consultationEntity) {
            if (customerDetailsEntity == null || consultationEntity == null)
                return null;
            String email = customerDetailsEntity.getEmail();
            String phoneNumber = customerDetailsEntity.getPhoneNumber();
            String name = customerDetailsEntity.getName();
            BikeDetails bikeDetails = bikeRepairConsultationEntityToBikeDetails(consultationEntity);
            String consultationId = consultationEntity.getId();
            return CustomerDetails.builder()
                    .name(name)
                    .email(email)
                    .phoneNumber(phoneNumber)
                    .bikeDetails(bikeDetails)
                    .consultationId(consultationId)
                    .build();
        }
    }

    /**
     * Holds all conversions where the source type is a domain object.
     */
    static class Domain {

        /**
         * Converts the specified domain customer details to a customer details DTO.
         *
         * @param customerDetails The domain object to convert, or {@code null}.
         * @return The corresponding DTO, or {@code null} if the input is {@code null}.
         */
        CustomerDetailsDTO customerDetailsToDTO(CustomerDetails customerDetails) {
            if (customerDetails == null)
                return null;

            return CustomerDetailsDTO.builder()
                    .name(customerDetails.getName())
                    .email(customerDetails.getEmail())
                    .phoneNumber(customerDetails.getPhoneNumber())
                    .bikeBrand(customerDetails.getBikeDetails().getBrand())
                    .bikeModel(customerDetails.getBikeDetails().getModel())
                    .bikeSerialNumber(customerDetails.getBikeDetails().getSerialNumber())
                    .consultationId(customerDetails.getConsultationId())
                    .build();

        }
        /**
         * Converts the specified domain repair order to a repair order updated DTO,
         * used to notify observers when a repair order has changed.
         *
         * @param repairOrder The repair order to convert.
         * @return A DTO containing the data needed to notify observers about the updated order.
         */
        RepairOrderUpdatedDTO repairOrderToRepairOrderUpdatedDTO(RepairOrder repairOrder){
            String name = repairOrder.getCustomerDetails().getName();
            String email = repairOrder.getCustomerDetails().getEmail();
            String phoneNumber = repairOrder.getCustomerDetails().getPhoneNumber();
            String bikeBrand = repairOrder.getCustomerDetails().getBikeDetails().getBrand();
            String bikeModel = repairOrder.getCustomerDetails().getBikeDetails().getModel();
            String bikeSerialNumber = repairOrder.getCustomerDetails().getBikeDetails().getSerialNumber();
            DiagnosticReportDTO diagnosticReportDTO = toDiagnosticReportDTO(repairOrder.getDiagnosticReport());
            String problemDescription = repairOrder.getProblemDescription();
            String state = repairOrder.getState().name();
            String repairOrderId = repairOrder.getId();
            List<RepairTaskDTO> repairTaskDTOS = repairTaskToDTO(repairOrder.getRepairTasks());
            return RepairOrderUpdatedDTO.builder()
                    .name(name)
                    .email(email)
                    .phoneNumber(phoneNumber)
                    .bikeBrand(bikeBrand)
                    .bikeModel(bikeModel)
                    .bikeSerialNumber(bikeSerialNumber)
                    .problemDescription(problemDescription)
                    .state(state)
                    .repairOrderId(repairOrderId)
                    .diagnosticReport(diagnosticReportDTO)
                    .proposedRepairTasks(repairTaskDTOS)
                    .build();

        }

        /**
         * Converts the specified domain diagnostic report to a diagnostic report DTO.
         *
         * @param diagnosticReport The domain object to convert, or {@code null}.
         * @return The corresponding DTO, or {@code null} if the input is {@code null}.
         */
        DiagnosticReportDTO toDiagnosticReportDTO(DiagnosticReport diagnosticReport){
            if(diagnosticReport == null)
                return null;

            return DiagnosticReportDTO.builder()
                    .description(diagnosticReport.getDescription())
                    .estimatedRepairTime(diagnosticReport.getEstimatedRepairTime())
                    .build();

        }
        /**
         * Converts the specified list of domain repair tasks to a list of repair task DTOs.
         *
         * @param repairTasks The repair tasks to convert.
         * @return A list of repair task DTOs corresponding to the input.
         */
        List<RepairTaskDTO> repairTaskToDTO(List<RepairTask> repairTasks) {
            List<RepairTaskDTO> repairTaskDTOs = new ArrayList<>();
            if(repairTasks == null)
                return repairTaskDTOs;
            for (RepairTask repairTask : repairTasks) {
                RepairTaskDTO repairTaskDTO = RepairTaskDTO.builder()
                        .description(repairTask.getDescription())
                        .cost(repairTask.getCost())
                        .build();
                repairTaskDTOs.add(repairTaskDTO);
            }
            return repairTaskDTOs;
        }

        /**
         * Converts the specified domain repair order to an entity, including its
         * customer details, repair tasks and diagnostic report.
         *
         * @param repairOrder The repair order to convert.
         * @return The corresponding repair order entity.
         */
        RepairOrderEntity repairOrderToEntity(RepairOrder repairOrder) {
            CustomerDetails customerDetails = repairOrder.getCustomerDetails();
            String date = repairOrder.getDate();
            String problemDescription = repairOrder.getProblemDescription();

            DiagnosticReport diagnosticReport = repairOrder.getDiagnosticReport();
            DiagnosticReportEntity diagnosticReportEntity = diagnosticReportToEntity(diagnosticReport);

            List<RepairTask> repairTasks = repairOrder.getRepairTasks();

            List<RepairTaskEntity> repairTaskEntities = repairTaskToEntity(repairTasks);

            String consultationId = customerDetails.getConsultationId();
            String state = repairOrder.getState().name();
            String phoneNumber = customerDetails.getPhoneNumber();
            BikeDetails bikeDetails = customerDetails.getBikeDetails();

            return RepairOrderEntity.builder()
                    .date(date)
                    .problemDescription(problemDescription)
                    .state(state)
                    .diagnosticReport(diagnosticReportEntity)
                    .repairTasks(repairTaskEntities)
                    .bikeBrand(bikeDetails.getBrand())
                    .bikeSerialNumber(bikeDetails.getSerialNumber())
                    .bikeModel(bikeDetails.getModel())
                    .consultationId(consultationId)
                    .id(repairOrder.getId())
                    .name(customerDetails.getName())
                    .customerPhoneNumber(phoneNumber)
                    .email(customerDetails.getEmail())
                    .build();
        }

        /**
         * Converts the specified list of domain repair tasks to a list of repair task entities.
         *
         * @param repairTasks The repair tasks to convert, or {@code null}.
         * @return A list of repair task entities, or {@code null} if the input is {@code null}.
         */
        List<RepairTaskEntity> repairTaskToEntity(List<RepairTask> repairTasks) {
            if (repairTasks == null)
                return null;
            List<RepairTaskEntity> repairTaskEntities = new ArrayList<>();
            for (RepairTask repairTask : repairTasks) {
                RepairTaskEntity repairTaskEntity = RepairTaskEntity.builder()
                        .description(repairTask.getDescription())
                        .cost(repairTask.getCost())
                        .build();
                repairTaskEntities.add(repairTaskEntity);
            }
            return repairTaskEntities;
        }

        /**
         * Converts the specified domain diagnostic report to an entity.
         *
         * @param diagnosticReport The domain object to convert, or {@code null}.
         * @return The corresponding entity, or {@code null} if the input is {@code null}.
         */
        DiagnosticReportEntity diagnosticReportToEntity(DiagnosticReport diagnosticReport) {
            if (diagnosticReport == null)
                return null;

            String description = diagnosticReport.getDescription();
            LocalDateTime estimatedTime = diagnosticReport.getEstimatedRepairTime();
            return DiagnosticReportEntity.builder()
                    .description(description)
                    .estimatedRepairTime(estimatedTime)
                    .build();
        }

        /**
         * Creates a DTO with the data needed to present a repair order to the
         * receptionist for customer approval, including the diagnosis, the proposed
         * repair tasks, the total cost, and identifying information about the order
         * and the bike.
         *
         * @param repairOrder The repair order to present.
         * @param totalCost   The total cost of all repair tasks.
         * @return A DTO ready to be presented to the receptionist.
         */
        PresentRepairOrderForApprovalDTO createPresentRepairOrderForApprovalDTO(RepairOrder repairOrder, double totalCost) {
            DiagnosticReportDTO diagnosticReportDTO = toDiagnosticReportDTO(repairOrder.getDiagnosticReport());
            List<RepairTaskDTO> repairTaskDTOs = repairTaskToDTO(repairOrder.getRepairTasks());

            return PresentRepairOrderForApprovalDTO.builder()
                    .diagnosticReport(diagnosticReportDTO)
                    .totalCost(totalCost)
                    .proposedRepairTasks(repairTaskDTOs)
                    .repairOrderId(repairOrder.getId())
                    .customerPhoneNumber(repairOrder.getCustomerDetails().getPhoneNumber())
                    .bikeModel(repairOrder.getCustomerDetails().getBikeDetails().getModel())
                    .bikeSerialNumber(repairOrder.getCustomerDetails().getBikeDetails().getSerialNumber())
                    .bikeBrand(repairOrder.getCustomerDetails().getBikeDetails().getBrand())
                    .build();
        }

        /**
         * Creates a DTO with the data needed to present a newly created repair order
         * to the technician.
         *
         * @param repairOrder The newly created repair order.
         * @return A DTO ready to be presented to the technician.
         */
        PresentNewlyCreatedRepairOrderDTO createPresentNewlyCreatedRepairOrderDTO(RepairOrder repairOrder) {
            return PresentNewlyCreatedRepairOrderDTO.builder()
                    .name(repairOrder.getCustomerDetails().getName())
                    .email(repairOrder.getCustomerDetails().getEmail())
                    .phoneNumber(repairOrder.getCustomerDetails().getPhoneNumber())
                    .bikeBrand(repairOrder.getCustomerDetails().getBikeDetails().getBrand())
                    .bikeModel(repairOrder.getCustomerDetails().getBikeDetails().getModel())
                    .bikeSerialNumber(repairOrder.getCustomerDetails().getBikeDetails().getSerialNumber())
                    .problemDescription(repairOrder.getProblemDescription())
                    .state(repairOrder.getState().name())
                    .repairOrderId(repairOrder.getId())
                    .build();
        }

        /**
         * Creates a receipt DTO for the specified approved repair order.
         *
         * @param repairOrder The approved repair order.
         * @param totalCost   The total cost of all repair tasks.
         * @return A receipt DTO containing all data needed for the printout.
         */
        ReceiptDTO createReceiptDTO(RepairOrder repairOrder, double totalCost) {
            List<RepairTaskDTO> repairTaskDTOs = repairTaskToDTO(repairOrder.getRepairTasks());

            DiagnosticReportDTO diagnosticReportDTO = toDiagnosticReportDTO(repairOrder.getDiagnosticReport());


            return ReceiptDTO.builder()
                    .name(repairOrder.getCustomerDetails().getName())
                    .email(repairOrder.getCustomerDetails().getEmail())
                    .phoneNumber(repairOrder.getCustomerDetails().getPhoneNumber())
                    .bikeBrand(repairOrder.getCustomerDetails().getBikeDetails().getBrand())
                    .bikeModel(repairOrder.getCustomerDetails().getBikeDetails().getModel())
                    .bikeSerialNumber(repairOrder.getCustomerDetails().getBikeDetails().getSerialNumber())
                    .problemDescription(repairOrder.getProblemDescription())
                    .state(repairOrder.getState().name())
                    .repairTasks(repairTaskToDTO(repairOrder.getRepairTasks()))
                    .diagnosticReport(diagnosticReportDTO)
                    .totalCost(totalCost)
                    .build();
        }
    }
}