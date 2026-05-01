package bikerepairshop.service;

import bikerepairshop.model.domain.*;
import bikerepairshop.model.dto.*;
import bikerepairshop.model.entity.DiagnosticReportEntity;
import bikerepairshop.model.entity.RepairOrderEntity;
import bikerepairshop.model.entity.RepairTaskEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Mapper {
    public Mapper() {
    }
    public CustomerDetailsDTO mapToCustomerDetailsDTO(CustomerDetails customerDetails){
        return new CustomerDetailsDTO(customerDetails);
    }

    public RepairOrderEntity mapToRepairOrderEntity(RepairOrder repairOrder) {
        CustomerDetails customerDetails = repairOrder.getCustomerDetails();
        String date = repairOrder.getDate();
        String problemDescription = repairOrder.getProblemDescription();

        DiagnosticReport diagnosticReport = repairOrder.getDiagnosticReport();
        DiagnosticReportEntity diagnosticReportEntity = mapToDiagnosticReportEntity(diagnosticReport);

        List<RepairTask> repairTasks = repairOrder.getRepairTasks();

        List <RepairTaskEntity> repairTaskEntities = mapToRepairTaskEntity(repairTasks);


        String consultationId = customerDetails.getConsultationId();
        String state = repairOrder.getState().name();
        String phoneNumber = customerDetails.getPhoneNumber();
        BikeDetails bikeDetails = customerDetails.getBikeDetails();


        return new RepairOrderEntity(date,problemDescription, state, diagnosticReportEntity,repairTaskEntities,phoneNumber,
                bikeDetails.getBrand(), bikeDetails.getSerialNumber(), consultationId, repairOrder.getId(), bikeDetails.getModel());
    }
    public PresentRepairOrderForApprovalDTO mapToPresentRepairOrderForApprovalDTO(RepairOrder repairOrder, double totalCost) {
        DiagnosticReportDTO diagnosticReportDTO = new DiagnosticReportDTO(repairOrder.getDiagnosticReport().getDescription(),repairOrder.getDiagnosticReport().getEstimatedRepairTime());
        List <RepairTaskDTO> repairTaskDTOs = new ArrayList<>();
        for (RepairTask repairTask : repairOrder.getRepairTasks()){
            RepairTaskDTO repairTaskDTO = new RepairTaskDTO(repairTask.getDescription(), repairTask.getCost());
            repairTaskDTOs.add(repairTaskDTO);
        }
        return new PresentRepairOrderForApprovalDTO(diagnosticReportDTO, totalCost, repairTaskDTOs, repairOrder.getId(),
                repairOrder.getCustomerDetails().getPhoneNumber(), repairOrder.getCustomerDetails().getBikeDetails().getModel(),
                repairOrder.getCustomerDetails().getBikeDetails().getSerialNumber(), repairOrder.getCustomerDetails().getBikeDetails().getBrand());
    }

    public PresentNewlyCreatedRepairOrderDTO mapToPresentNewlyCreatedRepairOrderDTO(RepairOrder repairOrder){
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

    private DiagnosticReportEntity mapToDiagnosticReportEntity(DiagnosticReport diagnosticReport){
        if(diagnosticReport == null)
            return null;

        String description = diagnosticReport.getDescription();
        LocalDateTime estimatedTime = diagnosticReport.getEstimatedRepairTime();
        return new DiagnosticReportEntity(description, estimatedTime);
    }

    List <RepairTaskEntity> mapToRepairTaskEntity(List<RepairTask> repairTasks){
        if(repairTasks == null)
            return null;
        List <RepairTaskEntity> repairTaskEntities = new ArrayList<>();
        for(RepairTask repairTask : repairTasks){
            RepairTaskEntity repairTaskEntity = new RepairTaskEntity(repairTask.getDescription(), repairTask.getCost());
            repairTaskEntities.add(repairTaskEntity);
        }
        return repairTaskEntities;
    }


}
