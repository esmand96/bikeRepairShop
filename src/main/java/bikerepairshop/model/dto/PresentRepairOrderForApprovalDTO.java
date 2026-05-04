package bikerepairshop.model.dto;

import bikerepairshop.model.dto.common.RepairTaskDTO;

import java.util.List;

/**
 * Carries the data needed to present a repair order to the receptionist for customer
 * approval, including the diagnosis, the proposed repair tasks, the total cost, and
 * identifying information about the order and the bike.
 */
public class PresentRepairOrderForApprovalDTO {
    private final DiagnosticReportDTO diagnosticReport;
    private final double totalCost;
    private final List<RepairTaskDTO> proposedRepairTasks;
    private final String repairOrderId;
    private final String customerPhoneNumber;
    private final String bikeModel;
    private final String bikeSerialNumber;
    private final String bikeBrand;

    /**
     * Creates a new instance with the specified data.
     *
     * @param diagnosticReport The technician's diagnosis.
     * @param totalCost The total cost of all proposed repair tasks.
     * @param proposedRepairTasks The repair tasks proposed by the technician.
     * @param repairOrderId The id of the repair order.
     * @param customerPhoneNumber The customer's phone number.
     * @param bikeModel The model of the bike.
     * @param bikeSerialNumber The serial number of the bike.
     * @param bikeBrand The brand of the bike.
     */
    public PresentRepairOrderForApprovalDTO(DiagnosticReportDTO diagnosticReport,
                                            double totalCost,
                                            List<RepairTaskDTO> proposedRepairTasks,
                                            String repairOrderId,
                                            String customerPhoneNumber,
                                            String bikeModel,
                                            String bikeSerialNumber,
                                            String bikeBrand) {
        this.diagnosticReport = diagnosticReport;
        this.totalCost = totalCost;
        this.proposedRepairTasks = proposedRepairTasks;
        this.repairOrderId = repairOrderId;
        this.customerPhoneNumber = customerPhoneNumber;
        this.bikeModel = bikeModel;
        this.bikeSerialNumber = bikeSerialNumber;
        this.bikeBrand = bikeBrand;
    }

    /**
     * @return The brand of the bike.
     */
    public String getBikeBrand() {
        return bikeBrand;
    }

    /**
     * @return The serial number of the bike.
     */
    public String getBikeSerialNumber() {
        return bikeSerialNumber;
    }

    /**
     * @return The model of the bike.
     */
    public String getBikeModel() {
        return bikeModel;
    }

    /**
     * @return The customer's phone number.
     */
    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    /**
     * @return The id of the repair order.
     */
    public String getRepairOrderId() {
        return repairOrderId;
    }

    /**
     * @return The technician's diagnosis.
     */
    public DiagnosticReportDTO getDiagnosticReport() {
        return diagnosticReport;
    }

    /**
     * @return The repair tasks proposed by the technician.
     */
    public List<RepairTaskDTO> proposedRepairTasks() {
        return proposedRepairTasks;
    }

    /**
     * @return The total cost of all proposed repair tasks.
     */
    public double getTotalCost() {
        return totalCost;
    }
}

