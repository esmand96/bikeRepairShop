package bikerepairshop.model.entity;

import java.util.List;

/**
 * Represents a repair order as it is stored in the external repair order registry,
 * including customer details, bike details, the diagnosis, the repair tasks, and the
 * current state.
 */
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

    /**
     * Creates a new instance with the specified data.
     *
     * @param date The date the order was created.
     * @param problemDescription The customer's description of the problem.
     * @param state The state of the order.
     * @param diagnosticReport The technician's diagnosis, or {@code null} if no diagnosis has been added yet.
     * @param repairTasks The repair tasks associated with the order, or {@code null} if no tasks have been added yet.
     * @param bikeBrand The brand of the bike.
     * @param bikeSerialNumber The serial number of the bike.
     * @param bikeModel The model of the bike.
     * @param consultationId The id of the consultation the order is based on.
     * @param id The unique id of the order.
     * @param name The customer's name.
     * @param customerPhoneNumber The customer's phone number.
     * @param email The customer's email address.
     */
    public RepairOrderEntity(String date,
                             String problemDescription,
                             String state,
                             DiagnosticReportEntity diagnosticReport,
                             List<RepairTaskEntity> repairTasks,
                             String bikeBrand,
                             String bikeSerialNumber,
                             String bikeModel,
                             String consultationId,
                             String id,
                             String name,
                             String customerPhoneNumber,
                             String email) {
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
        this.bikeModel = bikeModel;
        this.name = name;
        this.email = email;
    }

    /**
     * @return The customer's email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email The new email address of the customer.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return The customer's name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The new name of the customer.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The date the order was created.
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date The new date for the order.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return The customer's description of the problem.
     */
    public String getProblemDescription() {
        return problemDescription;
    }

    /**
     * @param problemDescription The new problem description.
     */
    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    /**
     * @return The state of the order.
     */
    public String getState() {
        return state;
    }

    /**
     * @param state The new state of the order.
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return The technician's diagnosis, or {@code null} if none has been added.
     */
    public DiagnosticReportEntity getDiagnosticReport() {
        return diagnosticReport;
    }

    /**
     * @param diagnosticReport The new diagnostic report.
     */
    public void setDiagnosticReport(DiagnosticReportEntity diagnosticReport) {
        this.diagnosticReport = diagnosticReport;
    }

    /**
     * @return The repair tasks associated with the order, or {@code null} if none have been added.
     */
    public List<RepairTaskEntity> getRepairTasks() {
        return repairTasks;
    }

    /**
     * @param repairTasks The new list of repair tasks.
     */
    public void setRepairTasks(List<RepairTaskEntity> repairTasks) {
        this.repairTasks = repairTasks;
    }

    /**
     * @return The customer's phone number.
     */
    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    /**
     * @param customerPhoneNumber The new phone number of the customer.
     */
    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    /**
     * @return The brand of the bike.
     */
    public String getBikeBrand() {
        return bikeBrand;
    }

    /**
     * @param bikeBrand The new brand of the bike.
     */
    public void setBikeBrand(String bikeBrand) {
        this.bikeBrand = bikeBrand;
    }

    /**
     * @return The serial number of the bike.
     */
    public String getBikeSerialNumber() {
        return bikeSerialNumber;
    }

    /**
     * @param bikeSerialNumber The new serial number of the bike.
     */
    public void setBikeSerialNumber(String bikeSerialNumber) {
        this.bikeSerialNumber = bikeSerialNumber;
    }

    /**
     * @return The id of the consultation the order is based on.
     */
    public String getConsultationId() {
        return consultationId;
    }

    /**
     * @param consultationId The new consultation id.
     */
    public void setConsultationId(String consultationId) {
        this.consultationId = consultationId;
    }

    /**
     * @return The unique id of the order.
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The new id of the order.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @param bikeModel The new model of the bike.
     */
    public void setBikeModel(String bikeModel) {
        this.bikeModel = bikeModel;
    }

    /**
     * @return The model of the bike.
     */
    public String getBikeModel() {
        return bikeModel;
    }
}