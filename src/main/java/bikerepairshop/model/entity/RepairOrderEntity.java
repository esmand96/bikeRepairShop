package bikerepairshop.model.entity;

import java.util.List;

public class RepairOrderEntity {
    private String date;
    private String problemDescription;
    private String state;
    private DiagnosticReportEntity diagnosticReport;
    private List<RepairTaskEntity> repairTasks;
    private String customerPhoneNumber;
    private String bikeBrand;
    private String bikeSerialNumber;
    private String bikeModel;
    private String consultationId;
    private String id;
    private String name;
    private String email;

    public RepairOrderEntity(String date, String problemDescription, String state, DiagnosticReportEntity diagnosticReport, List<RepairTaskEntity> repairTasks, String customerPhoneNumber, String bikeBrand, String bikeSerialNumber, String consultationId, String id, String bikeModel, String name, String email) {
        this.date = date;
        this.problemDescription = problemDescription;
        this.state = state;
        this.diagnosticReport = diagnosticReport;
        this.repairTasks = repairTasks;
        this.customerPhoneNumber = customerPhoneNumber;
        this.bikeBrand = bikeBrand;
        this.bikeSerialNumber = bikeSerialNumber;
        this.consultationId = consultationId; ///kanske ta bort consultationId hörifrån?
        this.id = id;
        this.bikeModel = bikeModel;
        this.name = name;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public DiagnosticReportEntity getDiagnosticReport() {
        return diagnosticReport;
    }

    public void setDiagnosticReport(DiagnosticReportEntity diagnosticReport) {
        this.diagnosticReport = diagnosticReport;
    }

    public List<RepairTaskEntity> getRepairTasks() {
        return repairTasks;
    }

    public void setRepairTasks(List<RepairTaskEntity> repairTasks) {
        this.repairTasks = repairTasks;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public String getBikeBrand() {
        return bikeBrand;
    }

    public void setBikeBrand(String bikeBrand) {
        this.bikeBrand = bikeBrand;
    }

    public String getBikeSerialNumber() {
        return bikeSerialNumber;
    }

    public void setBikeSerialNumber(String bikeSerialNumber) {
        this.bikeSerialNumber = bikeSerialNumber;
    }

    public String getConsultationId() {
        return consultationId;
    }

    public void setConsultationId(String consultationId) {
        this.consultationId = consultationId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBikeModel(String bikeModel) {
        this.bikeModel = bikeModel;
    }
    public String getBikeModel() {
        return bikeModel;
    }
}
