package bikerepairshop.model.dto;

import bikerepairshop.model.dto.common.RepairTaskDTO;

import java.util.List;

public class ReceiptDTO {
    private final String name;
    private final String email;
    private final String phoneNumber;
    private final String bikeBrand;
    private final String bikeModel;
    private final String bikeSerialNumber;
    private final String problemDescription;
    private final String state;
    private final List <RepairTaskDTO> repairTasks;
    private final DiagnosticReportDTO diagnosticReport;
    private final double totalCost;

    public ReceiptDTO(String name, String email, String phoneNumber, String bikeBrand, String bikeModel, String bikeSerialNumber, String problemDescription, String state,  List <RepairTaskDTO> repairTasks, DiagnosticReportDTO diagnosticReport, double totalCost) {
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getBikeBrand() {
        return bikeBrand;
    }

    public String getBikeModel() {
        return bikeModel;
    }

    public String getBikeSerialNumber() {
        return bikeSerialNumber;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public DiagnosticReportDTO getDiagnosticReport() {
        return diagnosticReport;
    }

    public List <RepairTaskDTO> getRepairTasks() {
        return repairTasks;
    }

    public String getState() {
        return state;
    }

    public String getProblemDescription() {
        return problemDescription;
    }
}
