package bikerepairshop.service;

import bikerepairshop.integration.CustomerRegistryIntegration;
import bikerepairshop.integration.PrinterIntegration;
import bikerepairshop.integration.RepairOrderRegistryIntegration;
import bikerepairshop.model.domain.*;
import bikerepairshop.model.dto.CustomerDetailsDTO;
import bikerepairshop.model.dto.PresentNewlyCreatedRepairOrderDTO;
import bikerepairshop.model.dto.PresentRepairOrderForApprovalDTO;
import bikerepairshop.model.dto.RepairTaskDTO;
import bikerepairshop.model.entity.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        BikeDetails bikeDetails = createBikeDetails(bikeRepairConsultationEntity);
        CustomerDetails customerDetails = createCustomerDetails(customerDetailsEntity, bikeDetails, bikeRepairConsultationEntity.getId());
        return mapper.mapToCustomerDetailsDTO(customerDetails);
    }

    public void createRepairOrder(String consultationId, String problemDescription){
        CustomerDetailsEntity customerDetailsEntity = customerRegistryIntegration.findCustomerByConsultationId(consultationId);
        BikeRepairConsultationEntity consultationEntity = selectConsultation(consultationId, customerDetailsEntity);
        BikeDetails bikeDetails = createBikeDetails(consultationEntity);
        CustomerDetails customerDetails = createCustomerDetails(customerDetailsEntity, bikeDetails, consultationId);
        RepairOrder repairOrder = createNewRepairOrder(problemDescription, customerDetails);
        RepairOrderEntity repairOrderEntity = mapper.mapToRepairOrderEntity(repairOrder);
        repairOrderRegistryIntegration.insertRepairOrder(repairOrderEntity);
    }

    public List<PresentNewlyCreatedRepairOrderDTO> getAllNewlyCreatedRepairOrders() {
        List<RepairOrderEntity> repairOrderEntities = repairOrderRegistryIntegration.getAllNewlyCreatedRepairOrders();
        List<PresentNewlyCreatedRepairOrderDTO> newlyCreatedRepairOrderDTOS = new ArrayList<>();
        for (RepairOrderEntity repairOrderEntity : repairOrderEntities){
            RepairOrder repairOrder = createRepairOrder(repairOrderEntity);
            PresentNewlyCreatedRepairOrderDTO presentNewlyCreatedRepairOrderDTO = mapper.mapToPresentNewlyCreatedRepairOrderDTO(repairOrder);
            newlyCreatedRepairOrderDTOS.add(presentNewlyCreatedRepairOrderDTO);
        }
        return newlyCreatedRepairOrderDTOS;
    }


    public List<PresentRepairOrderForApprovalDTO> getAllReadyForApprovalOrders() {
        List<RepairOrderEntity> repairOrderEntities = repairOrderRegistryIntegration.getAllReadyForApprovalOrders();
        List<PresentRepairOrderForApprovalDTO> repairOrderForApprovalDTOs = new ArrayList<>();
        for (RepairOrderEntity repairOrderEntity : repairOrderEntities){
            RepairOrder repairOrder = createRepairOrder((repairOrderEntity));
            double totalCost = calculateTotalCost(repairOrder);
            PresentRepairOrderForApprovalDTO presentRepairOrderForApprovalDTO = mapper.mapToPresentRepairOrderForApprovalDTO(repairOrder,totalCost);
            repairOrderForApprovalDTOs.add(presentRepairOrderForApprovalDTO);
        }
        return repairOrderForApprovalDTOs;
    }

    public void enterDiagnosticReportAndRepairTasks(String repairOrderId, String diagnosticReportDescription, List<RepairTaskDTO> repairTasks, LocalDateTime estimatedRepairTime){
        RepairOrderEntity repairOrderEntity = repairOrderRegistryIntegration.getRepairOrderById(repairOrderId);
        List <RepairTaskEntity> repairTaskEntities = new ArrayList<>();
        DiagnosticReportEntity diagnosticReportEntity = new DiagnosticReportEntity(diagnosticReportDescription, estimatedRepairTime);
        for (RepairTaskDTO repairTaskDTO : repairTasks){
            RepairTaskEntity repairTaskEntity = new RepairTaskEntity(repairTaskDTO.getDescription(), repairTaskDTO.getCost());
            repairTaskEntities.add(repairTaskEntity);
        }
        repairOrderEntity.setRepairTasks(repairTaskEntities);
        repairOrderEntity.setDiagnosticReport(diagnosticReportEntity);
        repairOrderEntity.setState("READY_FOR_APPROVAL");
        repairOrderRegistryIntegration.insertRepairOrder(repairOrderEntity);
    }

    ///lossas att den consultation högst upp i listan gäller. sorterar ej. (kanske fixar detta sen),
    /// BYT NAMN
    private BikeRepairConsultationEntity selectFirstUnhandledConsultation (CustomerDetailsEntity customerDetailsEntity){ ///metodens sorterar inte på första unhandeld
        List<BikeRepairConsultationEntity> consultations = customerDetailsEntity.getConsultations();
        return consultations.getFirst();
    }

    private BikeRepairConsultationEntity selectConsultation(String consultationId, CustomerDetailsEntity customerDetailsEntity){
        List <BikeRepairConsultationEntity> consultations = customerDetailsEntity.getConsultations();
        for (BikeRepairConsultationEntity consultation : consultations){
            if( consultationId.equals(consultation.getId()))
                return consultation;
        }
        return null;
    }

    private BikeDetails createBikeDetails(BikeRepairConsultationEntity consultationEntity){
        String serialNumber = consultationEntity.getSerialNumber();
        String brand = consultationEntity.getBrand();
        String model = consultationEntity.getModel();
        return new BikeDetails(brand, serialNumber, model);
    }

    private BikeDetails createBikeDetails(RepairOrderEntity repairOrderEntity){
        String serialNumber = repairOrderEntity.getBikeSerialNumber();
        String brand = repairOrderEntity.getBikeBrand();
        String model = repairOrderEntity.getBikeModel();
        return new BikeDetails(brand, serialNumber, model);
    }

    private CustomerDetails createCustomerDetails(CustomerDetailsEntity customerDetailsEntity, BikeDetails bikeDetails, String consultationId){
        String email = customerDetailsEntity.getEmail();
        String phoneNumber = customerDetailsEntity.getPhoneNumber();
        String name = customerDetailsEntity.getName();
        return new CustomerDetails(name,email, phoneNumber, bikeDetails, consultationId);
    }
    private CustomerDetails createCustomerDetails(RepairOrderEntity repairOrderEntity, BikeDetails bikeDetails){
        String phoneNumber = repairOrderEntity.getCustomerPhoneNumber();
        CustomerDetailsEntity customerDetailsEntity = customerRegistryIntegration.findCustomerEntityByPhoneNumber(phoneNumber);
        String name = customerDetailsEntity.getName();
        String email = customerDetailsEntity.getEmail();
        return new CustomerDetails(name,email, phoneNumber,bikeDetails, repairOrderEntity.getConsultationId() );
    }

    private RepairOrder createNewRepairOrder(String problemDescription, CustomerDetails customerDetails){
        RepairOrderState repairOrderState = RepairOrderState.NEWLY_CREATED;
        String date = LocalDateTime.now().toString();
        return new RepairOrder(date, problemDescription, repairOrderState, customerDetails, null,
                null, UUID.randomUUID().toString());
    }

    private RepairOrder createRepairOrder (RepairOrderEntity repairOrderEntity) {
        RepairOrderState repairOrderState = RepairOrderState.valueOf(repairOrderEntity.getState());
        BikeDetails bikeDetails = createBikeDetails(repairOrderEntity);
        CustomerDetails customerDetails = createCustomerDetails(repairOrderEntity, bikeDetails);
        String date = LocalDateTime.now().toString();
        List<RepairTask> repairTasks = createRepairTasks(repairOrderEntity);
        DiagnosticReport diagnosticReport = createDiagnosticReport(repairOrderEntity);

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

    private DiagnosticReport createDiagnosticReport (RepairOrderEntity repairOrderEntity) {
        DiagnosticReportEntity diagnosticReportEntity = repairOrderEntity.getDiagnosticReport();
        if(diagnosticReportEntity == null)
            return null;

        return new DiagnosticReport(diagnosticReportEntity.getDescription(), diagnosticReportEntity.getEstimatedRepairTime());

    }
    private List<RepairTask> createRepairTasks (RepairOrderEntity repairOrderEntity) {
        List<RepairTask> repairTasks = new ArrayList<>();
        List<RepairTaskEntity> repairTaskEntities = repairOrderEntity.getRepairTasks();
        if(repairTaskEntities == null)
            return repairTasks;

        for(RepairTaskEntity repairTaskEntity : repairTaskEntities){
            RepairTask repairTask = new RepairTask(repairTaskEntity.getCost(), repairTaskEntity.getDescription());
            repairTasks.add(repairTask);
        }
        return repairTasks;
    }

    private double calculateTotalCost (RepairOrder repairOrder){
        double totalCost = 0.0;
        for (RepairTask repairTask : repairOrder.getRepairTasks()){
            totalCost += repairTask.getCost();
        }
         return totalCost;
    }

}
