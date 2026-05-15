package se.kth.IV1350.bikerepairshop.model.entity;

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

    private RepairOrderEntity(Builder builder) {
        this.date = builder.date;
        this.problemDescription = builder.problemDescription;
        this.state = builder.state;
        this.diagnosticReport = builder.diagnosticReport;
        this.repairTasks = builder.repairTasks;
        this.customerPhoneNumber = builder.customerPhoneNumber;
        this.bikeBrand = builder.bikeBrand;
        this.bikeSerialNumber = builder.bikeSerialNumber;
        this.consultationId = builder.consultationId;
        this.id = builder.id;
        this.bikeModel = builder.bikeModel;
        this.name = builder.name;
        this.email = builder.email;
    }

    /**
     * Builder for creating instances of {@link RepairOrderEntity}.
     * Used to avoid constructors with many parameters of the same type,
     * which makes it easy to accidentally pass arguments in the wrong order.
     */
    public static class Builder {
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
         * Sets the date of the repair order.
         * @param date The date the order was created.
         * @return This builder, to allow method chaining.
         */
        public Builder date(String date) {
            this.date = date;
            return this;
        }

        /**
         * Sets the problem description of the repair order.
         * @param problemDescription The customer's description of the problem.
         * @return This builder, to allow method chaining.
         */
        public Builder problemDescription(String problemDescription) {
            this.problemDescription = problemDescription;
            return this;
        }

        /**
         * Sets the state of the repair order.
         * @param state The state of the order.
         * @return This builder, to allow method chaining.
         */
        public Builder state(String state) {
            this.state = state;
            return this;
        }

        /**
         * Sets the diagnostic report of the repair order.
         * @param diagnosticReport The technician's diagnosis.
         * @return This builder, to allow method chaining.
         */
        public Builder diagnosticReport(DiagnosticReportEntity diagnosticReport) {
            this.diagnosticReport = diagnosticReport;
            return this;
        }

        /**
         * Sets the repair tasks of the repair order.
         * @param repairTasks The repair tasks associated with the order.
         * @return This builder, to allow method chaining.
         */
        public Builder repairTasks(List<RepairTaskEntity> repairTasks) {
            this.repairTasks = repairTasks;
            return this;
        }

        /**
         * Sets the customer's phone number.
         * @param customerPhoneNumber The customer's phone number.
         * @return This builder, to allow method chaining.
         */
        public Builder customerPhoneNumber(String customerPhoneNumber) {
            this.customerPhoneNumber = customerPhoneNumber;
            return this;
        }

        /**
         * Sets the bike brand.
         * @param bikeBrand The brand of the bike.
         * @return This builder, to allow method chaining.
         */
        public Builder bikeBrand(String bikeBrand) {
            this.bikeBrand = bikeBrand;
            return this;
        }

        /**
         * Sets the bike serial number.
         * @param bikeSerialNumber The serial number of the bike.
         * @return This builder, to allow method chaining.
         */
        public Builder bikeSerialNumber(String bikeSerialNumber) {
            this.bikeSerialNumber = bikeSerialNumber;
            return this;
        }

        /**
         * Sets the bike model.
         * @param bikeModel The model of the bike.
         * @return This builder, to allow method chaining.
         */
        public Builder bikeModel(String bikeModel) {
            this.bikeModel = bikeModel;
            return this;
        }

        /**
         * Sets the consultation id.
         * @param consultationId The id of the consultation the order is based on.
         * @return This builder, to allow method chaining.
         */
        public Builder consultationId(String consultationId) {
            this.consultationId = consultationId;
            return this;
        }

        /**
         * Sets the id of the repair order.
         * @param id The unique id of the order.
         * @return This builder, to allow method chaining.
         */
        public Builder id(String id) {
            this.id = id;
            return this;
        }

        /**
         * Sets the customer's name.
         * @param name The customer's name.
         * @return This builder, to allow method chaining.
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the customer's email address.
         * @param email The customer's email address.
         * @return This builder, to allow method chaining.
         */
        public Builder email(String email) {
            this.email = email;
            return this;
        }

        /**
         * Creates and returns a new {@link RepairOrderEntity} with the values
         * set on this builder.
         * @return A new RepairOrderEntity instance.
         */
        public RepairOrderEntity build() {
            return new RepairOrderEntity(this);
        }
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