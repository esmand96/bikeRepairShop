package se.kth.IV1350.bikerepairshop.service;

import se.kth.IV1350.bikerepairshop.exceptions.CustomerNotFoundException;
import se.kth.IV1350.bikerepairshop.exceptions.DatabaseFailureException;
import se.kth.IV1350.bikerepairshop.integration.CustomerRegistryIntegration;
import se.kth.IV1350.bikerepairshop.integration.PrinterIntegration;
import se.kth.IV1350.bikerepairshop.integration.RepairOrderRegistryIntegration;

import se.kth.IV1350.bikerepairshop.model.domain.*;
import se.kth.IV1350.bikerepairshop.model.dto.CustomerDetailsDTO;
import se.kth.IV1350.bikerepairshop.model.dto.PresentNewlyCreatedRepairOrderDTO;
import se.kth.IV1350.bikerepairshop.model.dto.PresentRepairOrderForApprovalDTO;
import se.kth.IV1350.bikerepairshop.model.dto.ReceiptDTO;
import se.kth.IV1350.bikerepairshop.model.dto.common.RepairTaskDTO;
import se.kth.IV1350.bikerepairshop.model.entity.BikeRepairConsultationEntity;
import se.kth.IV1350.bikerepairshop.model.entity.CustomerDetailsEntity;
import se.kth.IV1350.bikerepairshop.model.entity.RepairOrderEntity;
import se.kth.IV1350.bikerepairshop.util.Util;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Coordinates the system's business logic by orchestrating calls between the
 * integration layer, the model and the {@link Mapper}. All system operations exposed
 * to the controller are implemented as public methods of this class.
 */
public class Service {
    private final RepairOrderRegistryIntegration repairOrderRegistryIntegration;
    private final CustomerRegistryIntegration customerRegistryIntegration;
    private final PrinterIntegration printerIntegration;
    private final Mapper mapper;

    /**
     * Creates a new instance using the specified integrations and mapper.
     *
     * @param repairOrderRegistryIntegration The integration used to access the repair order registry.
     * @param customerRegistryIntegration The integration used to access the customer registry.
     * @param printerIntegration The integration used to print receipts.
     * @param mapper The mapper used to convert between model representations.
     */
    public Service(RepairOrderRegistryIntegration repairOrderRegistryIntegration, CustomerRegistryIntegration customerRegistryIntegration, PrinterIntegration printerIntegration, Mapper mapper) {
        this.repairOrderRegistryIntegration = repairOrderRegistryIntegration;
        this.customerRegistryIntegration = customerRegistryIntegration;
        this.printerIntegration = printerIntegration;
        this.mapper = mapper;
    }
    /**
     * Finds the customer registered with the specified phone number, including the
     * customer's first unhandled consultation.
     *
     * @param phoneNumber The phone number used to identify the customer.
     * @return A DTO containing the customer's details and the selected consultation.
     * @throws CustomerNotFoundException if no customer has the specified phone number.
     * @throws DatabaseFailureException if the customer registry cannot be accessed.
     */
    public CustomerDetailsDTO findCustomerByPhoneNumber(String phoneNumber) throws CustomerNotFoundException, DatabaseFailureException {
        CustomerDetailsEntity customerDetailsEntity = customerRegistryIntegration.findCustomerEntityByPhoneNumber(phoneNumber);
        if (customerDetailsEntity == null)
            throw new CustomerNotFoundException("Ingen kund kopplad till telefonnummer " + phoneNumber );
        BikeRepairConsultationEntity bikeRepairConsultationEntity = selectFirstUnhandledConsultation(customerDetailsEntity);
        CustomerDetails customerDetails = mapper.ENTITY.mergeCustomerDetailsEntityAndBikeConsultationEntityToCustomerDetails(customerDetailsEntity, bikeRepairConsultationEntity);
        return mapper.DOMAIN.customerDetailsToDTO(customerDetails);
    }

    /**
     * Creates a new repair order for the consultation with the specified id, using the
     * specified problem description. The order is initialized in the
     * {@code NEWLY_CREATED} state and saved to the repair order registry.
     *
     * @param consultationId The id of the consultation the repair order is created for.
     * @param problemDescription The customer's description of the problem.
     */
    public void createRepairOrder(String consultationId, String problemDescription) {
        CustomerDetailsEntity customerDetailsEntity = customerRegistryIntegration.findCustomerByConsultationId(consultationId);
        BikeRepairConsultationEntity consultationEntity = selectConsultation(consultationId, customerDetailsEntity);
        CustomerDetails customerDetails = mapper.ENTITY.mergeCustomerDetailsEntityAndBikeConsultationEntityToCustomerDetails(customerDetailsEntity, consultationEntity);
        RepairOrder repairOrder = createNewRepairOrder(problemDescription, customerDetails);
        RepairOrderEntity repairOrderEntity = mapper.DOMAIN.repairOrderToEntity(repairOrder);
        repairOrderRegistryIntegration.saveRepairOrder(repairOrderEntity);
    }

    /**
     * Retrieves all repair orders that have been created but not yet diagnosed.
     *
     * @return A list of DTOs representing each newly created repair order.
     */
    public List<PresentNewlyCreatedRepairOrderDTO> getAllNewlyCreatedRepairOrders() {
        List<RepairOrderEntity> repairOrderEntities = repairOrderRegistryIntegration.getAllNewlyCreatedRepairOrders();
        List<PresentNewlyCreatedRepairOrderDTO> newlyCreatedRepairOrderDTOS = new ArrayList<>();
        for (RepairOrderEntity repairOrderEntity : repairOrderEntities) {
            RepairOrder repairOrder = mapper.ENTITY.repairOrderEntityToDomain(repairOrderEntity);
            PresentNewlyCreatedRepairOrderDTO presentNewlyCreatedRepairOrderDTO = mapper.DOMAIN.createPresentNewlyCreatedRepairOrderDTO(repairOrder);
            newlyCreatedRepairOrderDTOS.add(presentNewlyCreatedRepairOrderDTO);
        }
        return newlyCreatedRepairOrderDTOS;
    }

    /**
     * Retrieves all repair orders that are ready to be approved by the customer,
     * including the calculated total cost for each order.
     *
     * @return A list of DTOs representing each repair order awaiting approval.
     */
    public List<PresentRepairOrderForApprovalDTO> getAllReadyForApprovalOrders() {
        List<RepairOrderEntity> repairOrderEntities = repairOrderRegistryIntegration.getAllReadyForApprovalOrders();
        List<PresentRepairOrderForApprovalDTO> repairOrderForApprovalDTOs = new ArrayList<>();
        for (RepairOrderEntity repairOrderEntity : repairOrderEntities) {
            RepairOrder repairOrder = mapper.ENTITY.repairOrderEntityToDomain(repairOrderEntity);
            double totalCost = repairOrder.calculateTotalCost();
            PresentRepairOrderForApprovalDTO presentRepairOrderForApprovalDTO = mapper.DOMAIN.createPresentRepairOrderForApprovalDTO(repairOrder, totalCost);
            repairOrderForApprovalDTOs.add(presentRepairOrderForApprovalDTO);
        }
        return repairOrderForApprovalDTOs;
    }

    /**
     * Adds a diagnostic report and a list of repair tasks to the repair order with the
     * specified id, and transitions the order to the {@code READY_FOR_APPROVAL} state.
     *
     * @param repairOrderId The id of the repair order to update.
     * @param diagnosticReportDescription The technician's description of the diagnosis.
     * @param repairTaskDTOs The proposed repair tasks with their associated costs.
     * @param estimatedRepairTime The estimated time when the repair will be completed.
     */
    public void enterDiagnosticReportAndRepairTasks(String repairOrderId, String diagnosticReportDescription, List<RepairTaskDTO> repairTaskDTOs, LocalDateTime estimatedRepairTime) {
        RepairOrderEntity repairOrderEntity = repairOrderRegistryIntegration.getRepairOrderById(repairOrderId);
        RepairOrder repairOrder = mapper.ENTITY.repairOrderEntityToDomain(repairOrderEntity);
        DiagnosticReport diagnosticReport = new DiagnosticReport(diagnosticReportDescription, estimatedRepairTime);
        List<RepairTask> repairTasks = mapper.DTO.repairTaskDTOToDomain(repairTaskDTOs);
        repairOrder.setDiagnosticReport(diagnosticReport);
        repairOrder.setRepairTasks(repairTasks);
        repairOrder.transitionState(RepairOrderState.READY_FOR_APPROVAL);
        RepairOrderEntity updatedRepairOrderEntity = mapper.DOMAIN.repairOrderToEntity(repairOrder);
        repairOrderRegistryIntegration.saveRepairOrder(updatedRepairOrderEntity);
    }

    /**
     * Approves the repair order with the specified id by transitioning it to the
     * {@code ACCEPTED} state and saving the change.
     *
     * @param repairOrderId The id of the repair order to approve.
     */
    public void approveRepairOrder(String repairOrderId) {
        updateRepairOrderState(repairOrderId, RepairOrderState.ACCEPTED);
    }

    /**
     * Generates a receipt for the approved repair order with the specified id and
     * sends it to the printer. If the order is not in the {@code ACCEPTED} state, no
     * receipt is generated.
     *
     * @param repairOrderId The id of the approved repair order.
     * @return A DTO containing the receipt data, or {@code null} if the order is not approved.
     */
    public ReceiptDTO getReceipt(String repairOrderId) {
        RepairOrderEntity repairOrderEntity = repairOrderRegistryIntegration.getRepairOrderById(repairOrderId);
        RepairOrder repairOrder = mapper.ENTITY.repairOrderEntityToDomain(repairOrderEntity);
        if (repairOrder.getState() != RepairOrderState.ACCEPTED) {
            return null;
        }
        double totalCost = repairOrder.calculateTotalCost();
        ReceiptDTO receiptDTO = mapper.DOMAIN.createReceiptDTO(repairOrder, totalCost);
        printerIntegration.printReceipt(receiptDTO);
        return receiptDTO;
    }


    public void rejectRepairOrder(String repairOrderId) {
      updateRepairOrderState(repairOrderId, RepairOrderState.REJECTED);
    }

    private void updateRepairOrderState(String repairOrderId, RepairOrderState repairOrderState){
        RepairOrderEntity repairOrderEntity = repairOrderRegistryIntegration.getRepairOrderById(repairOrderId);
        RepairOrder repairOrder = mapper.ENTITY.repairOrderEntityToDomain(repairOrderEntity);
        repairOrder.transitionState(repairOrderState);
        RepairOrderEntity updatedRepairOrderEntity = mapper.DOMAIN.repairOrderToEntity(repairOrder);
        repairOrderRegistryIntegration.saveRepairOrder(updatedRepairOrderEntity);
    }

    private BikeRepairConsultationEntity selectFirstUnhandledConsultation(CustomerDetailsEntity customerDetailsEntity) {
        List<BikeRepairConsultationEntity> consultations = customerDetailsEntity.getConsultations();
        consultations.sort(Comparator.comparing(BikeRepairConsultationEntity::getDate));
        for (BikeRepairConsultationEntity consultation : consultations) {
            if (!repairOrderRegistryIntegration.existsByConsultationId(consultation.getId())) {
                return consultation;
            }
        }
        return null;
    }

    private BikeRepairConsultationEntity selectConsultation(String consultationId, CustomerDetailsEntity customerDetailsEntity) {
        List<BikeRepairConsultationEntity> consultations = customerDetailsEntity.getConsultations();
        for (BikeRepairConsultationEntity consultation : consultations) {
            if (consultationId.equals(consultation.getId()))
                return consultation;
        }
        return null;
    }

    private RepairOrder createNewRepairOrder(String problemDescription, CustomerDetails customerDetails) {
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
}