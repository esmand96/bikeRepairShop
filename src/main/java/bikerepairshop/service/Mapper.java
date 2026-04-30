package bikerepairshop.service;

import bikerepairshop.model.domain.*;
import bikerepairshop.model.dto.CustomerDetailsDTO;
import bikerepairshop.model.dto.PresentNewlyCreatedRepairOrderDTO;
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
        CustomerDetailsDTO customerDetailsDTO = new CustomerDetailsDTO(customerDetails);
        return customerDetailsDTO;
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

        RepairOrderEntity repairOrderEntity =
                new RepairOrderEntity(date,problemDescription, state, diagnosticReportEntity,repairTaskEntities,phoneNumber,
                        bikeDetails.getBrand(), bikeDetails.getSerialNumber(), consultationId, repairOrder.getId(), bikeDetails.getModel());


        return repairOrderEntity;
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
        PresentNewlyCreatedRepairOrderDTO newlyCreatedRepairOrderDTO = new PresentNewlyCreatedRepairOrderDTO(name, email, phoneNumber,
                bikeBrand, bikeModel, bikeSerialNumber, problemDescription, state, repairOrderId);
        return newlyCreatedRepairOrderDTO;
    }

    private DiagnosticReportEntity mapToDiagnosticReportEntity(DiagnosticReport diagnosticReport){
        if(diagnosticReport == null)
            return null;

        String description = diagnosticReport.getDescription();
        LocalDateTime estimatedTime = diagnosticReport.getEstimatedRepairTime();
        DiagnosticReportEntity diagnosticReportEntity = new DiagnosticReportEntity(description, estimatedTime);
        return diagnosticReportEntity;
    }

    private List <RepairTaskEntity> mapToRepairTaskEntity(List <RepairTask> repairTasks){
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
