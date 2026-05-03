package bikerepairshop.service;

import bikerepairshop.integration.CustomerRegistryIntegration;
import bikerepairshop.integration.PrinterIntegration;
import bikerepairshop.integration.RepairOrderRegistryIntegration;
import bikerepairshop.model.domain.*;
import bikerepairshop.model.dto.*;
import bikerepairshop.model.dto.common.RepairTaskDTO;
import bikerepairshop.model.entity.*;
import bikerepairshop.util.Util;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Service {
    private final RepairOrderRegistryIntegration repairOrderRegistryIntegration;
    private final CustomerRegistryIntegration customerRegistryIntegration;
    private final PrinterIntegration printerIntegration;
    private final Mapper mapper;

    public Service(RepairOrderRegistryIntegration repairOrderRegistryIntegration, CustomerRegistryIntegration customerRegistryIntegration, PrinterIntegration printerIntegration, Mapper mapper) {
        this.repairOrderRegistryIntegration = repairOrderRegistryIntegration;
        this.customerRegistryIntegration = customerRegistryIntegration;
        this.printerIntegration = printerIntegration;
        this.mapper = mapper;
    }

    public CustomerDetailsDTO findCustomerByPhoneNumber(String phoneNumber) {
        CustomerDetailsEntity customerDetailsEntity = customerRegistryIntegration.findCustomerEntityByPhoneNumber(phoneNumber);
        BikeRepairConsultationEntity bikeRepairConsultationEntity = selectFirstUnhandledConsultation(customerDetailsEntity);
        CustomerDetails customerDetails = mapper.ENTITY.mergeCustomerDetailsEntityAndBikeConsultationEntityToCustomerDetails(customerDetailsEntity, bikeRepairConsultationEntity);
        return mapper.DOMAIN.customerDetailsToDTO(customerDetails);
    }

    public void createRepairOrder(String consultationId, String problemDescription){
        CustomerDetailsEntity customerDetailsEntity = customerRegistryIntegration.findCustomerByConsultationId(consultationId);
        BikeRepairConsultationEntity consultationEntity = selectConsultation(consultationId, customerDetailsEntity);
        CustomerDetails customerDetails = mapper.ENTITY.mergeCustomerDetailsEntityAndBikeConsultationEntityToCustomerDetails(customerDetailsEntity, consultationEntity);
        RepairOrder repairOrder = createNewRepairOrder(problemDescription, customerDetails);
        RepairOrderEntity repairOrderEntity = mapper.DOMAIN.repairOrderToEntity(repairOrder);
        repairOrderRegistryIntegration.saveRepairOrder(repairOrderEntity);
    }

    public List<PresentNewlyCreatedRepairOrderDTO> getAllNewlyCreatedRepairOrders() {
        List<RepairOrderEntity> repairOrderEntities = repairOrderRegistryIntegration.getAllNewlyCreatedRepairOrders();
        List<PresentNewlyCreatedRepairOrderDTO> newlyCreatedRepairOrderDTOS = new ArrayList<>();
        for (RepairOrderEntity repairOrderEntity : repairOrderEntities){
            RepairOrder repairOrder = mapper.ENTITY.repairOrderEntityToDomain(repairOrderEntity);
            PresentNewlyCreatedRepairOrderDTO presentNewlyCreatedRepairOrderDTO = mapper.DOMAIN.createPresentNewlyCreatedRepairOrderDTO(repairOrder);
            newlyCreatedRepairOrderDTOS.add(presentNewlyCreatedRepairOrderDTO);
        }
        return newlyCreatedRepairOrderDTOS;
    }


    public List<PresentRepairOrderForApprovalDTO> getAllReadyForApprovalOrders() {
        List<RepairOrderEntity> repairOrderEntities = repairOrderRegistryIntegration.getAllReadyForApprovalOrders();
        List<PresentRepairOrderForApprovalDTO> repairOrderForApprovalDTOs = new ArrayList<>();
        for (RepairOrderEntity repairOrderEntity : repairOrderEntities){
            RepairOrder repairOrder = mapper.ENTITY.repairOrderEntityToDomain(repairOrderEntity);
            double totalCost = calculateTotalCost(repairOrder);
            PresentRepairOrderForApprovalDTO presentRepairOrderForApprovalDTO = mapper.DOMAIN.createPresentRepairOrderForApprovalDTO(repairOrder,totalCost);
            repairOrderForApprovalDTOs.add(presentRepairOrderForApprovalDTO);
        }
        return repairOrderForApprovalDTOs;
    }

    public void enterDiagnosticReportAndRepairTasks(String repairOrderId, String diagnosticReportDescription, List<RepairTaskDTO> repairTaskDTOs, LocalDateTime estimatedRepairTime){
        RepairOrderEntity repairOrderEntity = repairOrderRegistryIntegration.getRepairOrderById(repairOrderId);
        RepairOrder repairOrder = mapper.ENTITY.repairOrderEntityToDomain(repairOrderEntity);
        DiagnosticReport diagnosticReport = new DiagnosticReport(diagnosticReportDescription, estimatedRepairTime);
        List<RepairTask> repairTasks = mapper.DTO.toDomain(repairTaskDTOs);
        repairOrder.setDiagnosticReport(diagnosticReport);
        repairOrder.setRepairTasks(repairTasks);
        updateState(repairOrder, RepairOrderState.READY_FOR_APPROVAL);
        RepairOrderEntity updatedRepairOrderEntity = mapper.DOMAIN.repairOrderToEntity(repairOrder);
        repairOrderRegistryIntegration.saveRepairOrder(updatedRepairOrderEntity);
    }

    public void approveRepairOrder(String repairOrderId) {
        RepairOrderEntity repairOrderEntity = repairOrderRegistryIntegration.getRepairOrderById(repairOrderId);
        RepairOrder repairOrder = mapper.ENTITY.repairOrderEntityToDomain(repairOrderEntity);
        updateState(repairOrder, RepairOrderState.ACCEPTED);
        RepairOrderEntity updatedRepairOrderEntity = mapper.DOMAIN.repairOrderToEntity(repairOrder);
        repairOrderRegistryIntegration.saveRepairOrder(updatedRepairOrderEntity);
    }

    public ReceiptDTO getReceipt(String repairOrderId) {
        RepairOrderEntity repairOrderEntity = repairOrderRegistryIntegration.getRepairOrderById(repairOrderId);
        RepairOrder repairOrder = mapper.ENTITY.repairOrderEntityToDomain(repairOrderEntity);
        if (repairOrder.getState() != RepairOrderState.ACCEPTED){
            return null;
        }
        double totalCost = calculateTotalCost(repairOrder);
        ReceiptDTO receiptDTO = mapper.DOMAIN.createReceiptDTO(repairOrder, totalCost);
        printerIntegration.printReceipt(receiptDTO);
        return receiptDTO;
    }

    private void updateState(RepairOrder repairOrder, RepairOrderState nextState){
        if(nextState == RepairOrderState.READY_FOR_APPROVAL){
            if(repairOrder.getState() != RepairOrderState.NEWLY_CREATED)
                return;
            else
                repairOrder.setState(nextState);
        } else if(nextState == RepairOrderState.ACCEPTED){
            if(repairOrder.getState() != RepairOrderState.READY_FOR_APPROVAL)
                return;
            else
                repairOrder.setState(nextState);
        }
    }

    private BikeRepairConsultationEntity selectFirstUnhandledConsultation (CustomerDetailsEntity customerDetailsEntity){
        List<BikeRepairConsultationEntity> consultations = customerDetailsEntity.getConsultations();
        consultations.sort(Comparator.comparing(BikeRepairConsultationEntity::getDate)); // sortera på datum och ta den äldsta först
        for (BikeRepairConsultationEntity consultation : consultations){
            if(!repairOrderRegistryIntegration.existsByConsultationId(consultation.getId())){
                return consultation;
            }
        }
        // ingen fel hantering om inga consultations finns eller om alla redan har repair orders.
        return null;
    }

    private BikeRepairConsultationEntity selectConsultation(String consultationId, CustomerDetailsEntity customerDetailsEntity){
        List <BikeRepairConsultationEntity> consultations = customerDetailsEntity.getConsultations();
        for (BikeRepairConsultationEntity consultation : consultations){
            if( consultationId.equals(consultation.getId()))
                return consultation;
        }
        return null;
    }

    private RepairOrder createNewRepairOrder(String problemDescription, CustomerDetails customerDetails){
        RepairOrderState repairOrderState = RepairOrderState.NEWLY_CREATED;
        String date = LocalDateTime.now().toString();
        return new RepairOrder(
                date,
                problemDescription,
                repairOrderState,
                customerDetails,
                null,
                null,
                Util.generateRandomId());
    }

    private double calculateTotalCost (RepairOrder repairOrder){
        double totalCost = 0.0;
        for (RepairTask repairTask : repairOrder.getRepairTasks()){
            totalCost += repairTask.getCost();
        }
         return totalCost;
    }

}
