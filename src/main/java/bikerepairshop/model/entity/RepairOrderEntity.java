package bikerepairshop.model.entity;

public class RepairOrderEntity {
    private String date;
    private String problemDescription;
    private String state;
    private DiagnosticReportEntity diagnosticReport;
    private RepairTaskEntity[] repairTasks;
    private String customerPhoneNumber;
    private String bikeBrand;
    private String bikeSerialNumber;
    private String consultationId;
    private String id;

    public RepairOrderEntity(String date, String problemDescription, String state, DiagnosticReportEntity diagnosticReport, RepairTaskEntity[] repairTasks, String customerPhoneNumber, String bikeBrand, String bikeSerialNumber, String consultationId, String id) {
        this.date = date;
        this.problemDescription = problemDescription;
        this.state = state;
        this.diagnosticReport = diagnosticReport;
        this.repairTasks = repairTasks;
        this.customerPhoneNumber = customerPhoneNumber;
        this.bikeBrand = bikeBrand;
        this.bikeSerialNumber = bikeSerialNumber;
        this.consultationId = consultationId;
        this.id = id;
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

    public RepairTaskEntity[] getRepairTasks() {
        return repairTasks;
    }

    public void setRepairTasks(RepairTaskEntity[] repairTasks) {
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
}
