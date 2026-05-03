package bikerepairshop.model.dto;

import bikerepairshop.model.dto.common.RepairTaskDTO;

import java.util.List;

public class PresentRepairOrderForApprovalDTO {
    private final DiagnosticReportDTO diagnosticReport;
    private final double totalCost;
    private final List<RepairTaskDTO> proposedRepairTasks;
    private final String repairOrderId;
    private final String customerPhoneNumber;
    private final String bikeModel;
    private final String bikeSerialNumber;
    private final String bikeBrand;

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

    public String getBikeBrand() {
        return bikeBrand;
    }

    public String getBikeSerialNumber() {
        return bikeSerialNumber;
    }

    public String getBikeModel() {
        return bikeModel;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public String getRepairOrderId() {
        return repairOrderId;
    }

    public DiagnosticReportDTO getDiagnosticReport() {
        return diagnosticReport;
    }

    public List<RepairTaskDTO> proposedRepairTasks() {
        return proposedRepairTasks;
    }

    public double getTotalCost() {
        return totalCost;
    }
}
