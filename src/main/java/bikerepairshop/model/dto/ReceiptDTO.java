package bikerepairshop.model.dto;

import bikerepairshop.model.dto.common.RepairTaskDTO;

import java.util.List;

/**
 * Carries all data needed to print a receipt for an approved repair order, including
 * customer details, bike details, the diagnosis, the performed repair tasks, and the
 * total cost.
 */
public class ReceiptDTO {
    private final String name;
    private final String email;
    private final String phoneNumber;
    private final String bikeBrand;
    private final String bikeModel;
    private final String bikeSerialNumber;
    private final String problemDescription;
    private final String state;
    private final List<RepairTaskDTO> repairTasks;
    private final DiagnosticReportDTO diagnosticReport;
    private final double totalCost;

    /**
     * Creates a new instance with the specified data.
     *
     * @param name The customer's name.
     * @param email The customer's email address.
     * @param phoneNumber The customer's phone number.
     * @param bikeBrand The brand of the bike.
     * @param bikeModel The model of the bike.
     * @param bikeSerialNumber The serial number of the bike.
     * @param problemDescription The customer's description of the problem.
     * @param state The state of the repair order.
     * @param repairTasks The repair tasks performed on the bike.
     * @param diagnosticReport The technician's diagnosis.
     * @param totalCost The total cost of all repair tasks.
     */
    public ReceiptDTO(String name, String email, String phoneNumber, String bikeBrand, String bikeModel, String bikeSerialNumber, String problemDescription, String state, List<RepairTaskDTO> repairTasks, DiagnosticReportDTO diagnosticReport, double totalCost) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.bikeBrand = bikeBrand;
        this.bikeModel = bikeModel;
        this.bikeSerialNumber = bikeSerialNumber;
        this.problemDescription = problemDescription;
        this.state = state;
        this.repairTasks = repairTasks;
        this.diagnosticReport = diagnosticReport;
        this.totalCost = totalCost;
    }

    /**
     * @return The customer's phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @return The customer's name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return The customer's email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return The brand of the bike.
     */
    public String getBikeBrand() {
        return bikeBrand;
    }

    /**
     * @return The model of the bike.
     */
    public String getBikeModel() {
        return bikeModel;
    }

    /**
     * @return The serial number of the bike.
     */
    public String getBikeSerialNumber() {
        return bikeSerialNumber;
    }

    /**
     * @return The total cost of all repair tasks.
     */
    public double getTotalCost() {
        return totalCost;
    }

    /**
     * @return The technician's diagnosis.
     */
    public DiagnosticReportDTO getDiagnosticReport() {
        return diagnosticReport;
    }

    /**
     * @return The repair tasks performed on the bike.
     */
    public List<RepairTaskDTO> getRepairTasks() {
        return repairTasks;
    }

    /**
     * @return The state of the repair order.
     */
    public String getState() {
        return state;
    }

    /**
     * @return The customer's description of the problem.
     */
    public String getProblemDescription() {
        return problemDescription;
    }
}