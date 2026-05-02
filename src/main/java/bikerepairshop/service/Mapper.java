package bikerepairshop.service;

import bikerepairshop.model.domain.*;
import bikerepairshop.model.dto.*;
import bikerepairshop.model.entity.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Mapper {
    final Domain DOMAIN = new Mapper.Domain();
    final Entity ENTITY = new Mapper.Entity();
    final Dto DTO = new Mapper.Dto();
     public Mapper() {
    }
    static class Dto{
        List<RepairTask> toDomain(List<RepairTaskDTO> repairTaskDTOS){
            List<RepairTask> repairTasks = new ArrayList<>();
            for (RepairTaskDTO repairTaskDTO : repairTaskDTOS){
                RepairTask repairTask = new RepairTask(repairTaskDTO.getCost(),repairTaskDTO.getDescription());
                repairTasks.add(repairTask);
            }
            return repairTasks;
        }
    }
    static class Entity{

        List<RepairTask> toDomain ( List<RepairTaskEntity> repairTaskEntities) {
            List<RepairTask> repairTasks = new ArrayList<>();
            if(repairTaskEntities == null)
                return repairTasks;

            for(RepairTaskEntity repairTaskEntity : repairTaskEntities){
                RepairTask repairTask = new RepairTask(repairTaskEntity.getCost(), repairTaskEntity.getDescription());
                repairTasks.add(repairTask);
            }
            return repairTasks;
        }

        DiagnosticReport toDomain (DiagnosticReportEntity diagnosticReportEntity) {
            if(diagnosticReportEntity == null)
                return null;

            return new DiagnosticReport(diagnosticReportEntity.getDescription(), diagnosticReportEntity.getEstimatedRepairTime());
        }

        RepairOrder toDomain (RepairOrderEntity repairOrderEntity) {
            RepairOrderState repairOrderState = RepairOrderState.valueOf(repairOrderEntity.getState());
            CustomerDetails customerDetails = toCustomerDetails(repairOrderEntity);
            String date = repairOrderEntity.getDate();
            List<RepairTask> repairTasks = toDomain(repairOrderEntity.getRepairTasks());
            DiagnosticReport diagnosticReport = toDomain(repairOrderEntity.getDiagnosticReport());

            return new RepairOrder(
                    date,
                    repairOrderEntity.getProblemDescription(),
                    repairOrderState,
                    customerDetails,
                    repairTasks,
                    diagnosticReport,
                    repairOrderEntity.getId()
            );
        }

        BikeDetails toBikeDetails(RepairOrderEntity repairOrderEntity){
            String serialNumber = repairOrderEntity.getBikeSerialNumber();
            String brand = repairOrderEntity.getBikeBrand();
            String model = repairOrderEntity.getBikeModel();
            return new BikeDetails(brand, model,  serialNumber);
        }

        BikeDetails toBikeDetails(BikeRepairConsultationEntity consultationEntity){
            String serialNumber = consultationEntity.getSerialNumber();
            String brand = consultationEntity.getBrand();
            String model = consultationEntity.getModel();
            return new BikeDetails(brand, serialNumber, model);
        }

        CustomerDetails toCustomerDetails(RepairOrderEntity repairOrderEntity){
            String phoneNumber = repairOrderEntity.getCustomerPhoneNumber();
            BikeDetails bikeDetails = toBikeDetails(repairOrderEntity);
            String name = repairOrderEntity.getName();
            String email = repairOrderEntity.getEmail();
            return new CustomerDetails(name,email, phoneNumber,bikeDetails, repairOrderEntity.getConsultationId());
        }

        CustomerDetails toCustomerDetails(CustomerDetailsEntity customerDetailsEntity, BikeRepairConsultationEntity consultationEntity ){
            String email = customerDetailsEntity.getEmail();
            String phoneNumber = customerDetailsEntity.getPhoneNumber();
            String name = customerDetailsEntity.getName();
            BikeDetails bikeDetails = toBikeDetails(consultationEntity);
            String consultationId = consultationEntity.getId();
            return new CustomerDetails(name,email, phoneNumber, bikeDetails, consultationId);
        }

    }

    static class Domain{

        CustomerDetailsDTO toDTO(CustomerDetails customerDetails){
            return new CustomerDetailsDTO(customerDetails);
        }

        List<RepairTaskDTO> toDTO(List<RepairTask> repairTasks){
            List <RepairTaskDTO> repairTaskDTOs = new ArrayList<>();
            for (RepairTask repairTask :repairTasks){
                RepairTaskDTO repairTaskDTO = new RepairTaskDTO(repairTask.getDescription(), repairTask.getCost());
                repairTaskDTOs.add(repairTaskDTO);
            }
            return repairTaskDTOs;
        }

        RepairOrderEntity toEntity(RepairOrder repairOrder){
            CustomerDetails customerDetails = repairOrder.getCustomerDetails();
            String date = repairOrder.getDate();
            String problemDescription = repairOrder.getProblemDescription();

            DiagnosticReport diagnosticReport = repairOrder.getDiagnosticReport();
            DiagnosticReportEntity diagnosticReportEntity = toEntity(diagnosticReport);

            List<RepairTask> repairTasks = repairOrder.getRepairTasks();

            List <RepairTaskEntity> repairTaskEntities = toEntity(repairTasks);


            String consultationId = customerDetails.getConsultationId();
            String state = repairOrder.getState().name();
            String phoneNumber = customerDetails.getPhoneNumber();
            BikeDetails bikeDetails = customerDetails.getBikeDetails();


            return new RepairOrderEntity(date,problemDescription, state, diagnosticReportEntity,repairTaskEntities,phoneNumber,
                    bikeDetails.getBrand(), bikeDetails.getSerialNumber(), consultationId, repairOrder.getId(), bikeDetails.getModel(), customerDetails.getName(), customerDetails.getEmail());
        }

        List <RepairTaskEntity> toEntity(List<RepairTask> repairTasks){
            if(repairTasks == null)
                return null;
            List <RepairTaskEntity> repairTaskEntities = new ArrayList<>();
            for(RepairTask repairTask : repairTasks){
                RepairTaskEntity repairTaskEntity = new RepairTaskEntity(repairTask.getDescription(), repairTask.getCost());
                repairTaskEntities.add(repairTaskEntity);
            }
            return repairTaskEntities;
        }

        DiagnosticReportEntity toEntity(DiagnosticReport diagnosticReport){
            if(diagnosticReport == null)
                return null;

            String description = diagnosticReport.getDescription();
            LocalDateTime estimatedTime = diagnosticReport.getEstimatedRepairTime();
            return new DiagnosticReportEntity(description, estimatedTime);
        }

        PresentRepairOrderForApprovalDTO toPresentRepairOrderForApprovalDTO(RepairOrder repairOrder, double totalCost) {
            DiagnosticReportDTO diagnosticReportDTO = new DiagnosticReportDTO(repairOrder.getDiagnosticReport().getDescription(),repairOrder.getDiagnosticReport().getEstimatedRepairTime());
            List <RepairTaskDTO> repairTaskDTOs = toDTO(repairOrder.getRepairTasks());

            return new PresentRepairOrderForApprovalDTO(diagnosticReportDTO, totalCost, repairTaskDTOs, repairOrder.getId(),
                    repairOrder.getCustomerDetails().getPhoneNumber(), repairOrder.getCustomerDetails().getBikeDetails().getModel(),
                    repairOrder.getCustomerDetails().getBikeDetails().getSerialNumber(), repairOrder.getCustomerDetails().getBikeDetails().getBrand());
        }


        PresentNewlyCreatedRepairOrderDTO toPresentNewlyCreatedRepairOrderDTO(RepairOrder repairOrder){
            String name = repairOrder.getCustomerDetails().getName();
            String email = repairOrder.getCustomerDetails().getEmail();
            String phoneNumber = repairOrder.getCustomerDetails().getPhoneNumber();
            String bikeBrand = repairOrder.getCustomerDetails().getBikeDetails().getBrand();
            String bikeModel = repairOrder.getCustomerDetails().getBikeDetails().getModel();
            String bikeSerialNumber = repairOrder.getCustomerDetails().getBikeDetails().getSerialNumber();
            String problemDescription = repairOrder.getProblemDescription();
            String state = repairOrder.getState().name();
            String repairOrderId = repairOrder.getId();
            return new PresentNewlyCreatedRepairOrderDTO(name, email, phoneNumber,
                    bikeBrand, bikeModel, bikeSerialNumber, problemDescription, state, repairOrderId);
        }

        ReceiptDTO toReceiptDTO(RepairOrder repairOrder, double totalCost) {
            String name = repairOrder.getCustomerDetails().getName();
            String email = repairOrder.getCustomerDetails().getEmail();
            String phoneNumber = repairOrder.getCustomerDetails().getPhoneNumber();
            String bikeBrand = repairOrder.getCustomerDetails().getBikeDetails().getBrand();
            String bikeModel = repairOrder.getCustomerDetails().getBikeDetails().getModel();
            String bikeSerialNumber = repairOrder.getCustomerDetails().getBikeDetails().getSerialNumber();
            String problemDescription = repairOrder.getProblemDescription();
            String state = repairOrder.getState().name();
            List <RepairTaskDTO> repairTaskDTOs = toDTO(repairOrder.getRepairTasks());

            DiagnosticReportDTO diagnosticReportDTO = new DiagnosticReportDTO(repairOrder.getDiagnosticReport().getDescription(),repairOrder.getDiagnosticReport().getEstimatedRepairTime());
            return new ReceiptDTO(name, email, phoneNumber, bikeBrand, bikeModel, bikeSerialNumber, problemDescription, state, repairTaskDTOs, diagnosticReportDTO, totalCost);
        }
    }

}
