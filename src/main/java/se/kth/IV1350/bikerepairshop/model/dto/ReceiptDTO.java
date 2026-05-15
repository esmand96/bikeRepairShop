package se.kth.IV1350.bikerepairshop.model.dto;

import se.kth.IV1350.bikerepairshop.model.dto.common.RepairTaskDTO;
import se.kth.IV1350.bikerepairshop.model.entity.RepairOrderEntity;

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

    private ReceiptDTO(Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        this.bikeBrand = builder.bikeBrand;
        this.bikeModel = builder.bikeModel;
        this.bikeSerialNumber = builder.bikeSerialNumber;
        this.problemDescription = builder.problemDescription;
        this.state = builder.state;
        this.repairTasks = builder.repairTasks;
        this.diagnosticReport = builder.diagnosticReport;
        this.totalCost = builder.totalCost;
    }

    /**
     * Builder for creating instances of {@link ReceiptDTO}.
     * Provides a clear and consistent way to create receipt DTO objects.
     */
    public static class Builder {
        private String name;
        private String email;
        private String phoneNumber;
        private String bikeBrand;
        private String bikeModel;
        private String bikeSerialNumber;
        private String problemDescription;
        private String state;
        private List<RepairTaskDTO> repairTasks;
        private DiagnosticReportDTO diagnosticReport;
        private double totalCost;

        /**
         * Sets the customer's name.
         *
         * @param name The customer's full name.
         * @return This builder, to allow method chaining.
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the customer's email address.
         *
         * @param email The customer's email address.
         * @return This builder, to allow method chaining.
         */
        public Builder email(String email) {
            this.email = email;
            return this;
        }

        /**
         * Sets the customer's phone number.
         *
         * @param phoneNumber The customer's phone number.
         * @return This builder, to allow method chaining.
         */
        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        /**
         * Sets the bike brand.
         *
         * @param bikeBrand The brand of the bike.
         * @return This builder, to allow method chaining.
         */
        public Builder bikeBrand(String bikeBrand) {
            this.bikeBrand = bikeBrand;
            return this;
        }

        /**
         * Sets the bike model.
         *
         * @param bikeModel The model of the bike.
         * @return This builder, to allow method chaining.
         */
        public Builder bikeModel(String bikeModel) {
            this.bikeModel = bikeModel;
            return this;
        }

        /**
         * Sets the bike serial number.
         *
         * @param bikeSerialNumber The serial number of the bike.
         * @return This builder, to allow method chaining.
         */
        public Builder bikeSerialNumber(String bikeSerialNumber) {
            this.bikeSerialNumber = bikeSerialNumber;
            return this;
        }

        /**
         * Sets the problem description.
         *
         * @param problemDescription The customer's description of the problem.
         * @return This builder, to allow method chaining.
         */
        public Builder problemDescription(String problemDescription) {
            this.problemDescription = problemDescription;
            return this;
        }

        /**
         * Sets the state of the repair order.
         *
         * @param state The state of the repair order.
         * @return This builder, to allow method chaining.
         */
        public Builder state(String state) {
            this.state = state;
            return this;
        }

        /**
         * Sets the repair tasks.
         *
         * @param repairTasks The repair tasks performed on the bike.
         * @return This builder, to allow method chaining.
         */
        public Builder repairTasks(List<RepairTaskDTO> repairTasks) {
            this.repairTasks = repairTasks;
            return this;
        }

        /**
         * Sets the diagnostic report.
         *
         * @param diagnosticReport The technician's diagnosis.
         * @return This builder, to allow method chaining.
         */
        public Builder diagnosticReport(DiagnosticReportDTO diagnosticReport) {
            this.diagnosticReport = diagnosticReport;
            return this;
        }

        /**
         * Sets the total cost.
         *
         * @param totalCost The total cost of all repair tasks.
         * @return This builder, to allow method chaining.
         */
        public Builder totalCost(double totalCost) {
            this.totalCost = totalCost;
            return this;
        }

        /**
         * Creates and returns a new {@link ReceiptDTO} with the values
         * set on this builder.
         *
         * @return A new ReceiptDTO instance.
         */
        public ReceiptDTO build() {
            return new ReceiptDTO(this);
        }
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