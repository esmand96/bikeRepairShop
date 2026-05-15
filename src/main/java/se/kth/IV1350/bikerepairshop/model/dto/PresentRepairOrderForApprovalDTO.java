package se.kth.IV1350.bikerepairshop.model.dto;

import se.kth.IV1350.bikerepairshop.model.dto.common.RepairTaskDTO;

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

    private PresentRepairOrderForApprovalDTO(Builder builder) {
        this.diagnosticReport = builder.diagnosticReport;
        this.totalCost = builder.totalCost;
        this.proposedRepairTasks = builder.proposedRepairTasks;
        this.repairOrderId = builder.repairOrderId;
        this.customerPhoneNumber = builder.customerPhoneNumber;
        this.bikeModel = builder.bikeModel;
        this.bikeSerialNumber = builder.bikeSerialNumber;
        this.bikeBrand = builder.bikeBrand;
    }

    /**
     * Builder for creating instances of {@link PresentRepairOrderForApprovalDTO}.
     * Provides a clear and consistent way to create repair order approval DTO objects.
     */
    public static class Builder {
        private double totalCost;
        private DiagnosticReportDTO diagnosticReport;
        private List<RepairTaskDTO> proposedRepairTasks;
        private String repairOrderId;
        private String customerPhoneNumber;
        private String bikeModel;
        private String bikeSerialNumber;
        private String bikeBrand;

        /**
         * Sets the total cost of all proposed repair tasks.
         *
         * @param totalCost The total cost of all proposed repair tasks.
         * @return This builder, to allow method chaining.
         */
        public Builder totalCost(double totalCost) {
            this.totalCost = totalCost;
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
         * Sets the proposed repair tasks.
         *
         * @param proposedRepairTasks The repair tasks proposed by the technician.
         * @return This builder, to allow method chaining.
         */
        public Builder proposedRepairTasks(List<RepairTaskDTO> proposedRepairTasks) {
            this.proposedRepairTasks = proposedRepairTasks;
            return this;
        }

        /**
         * Sets the repair order id.
         *
         * @param repairOrderId The id of the repair order.
         * @return This builder, to allow method chaining.
         */
        public Builder repairOrderId(String repairOrderId) {
            this.repairOrderId = repairOrderId;
            return this;
        }

        /**
         * Sets the customer's phone number.
         *
         * @param customerPhoneNumber The customer's phone number.
         * @return This builder, to allow method chaining.
         */
        public Builder customerPhoneNumber(String customerPhoneNumber) {
            this.customerPhoneNumber = customerPhoneNumber;
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
         * Creates and returns a new {@link PresentRepairOrderForApprovalDTO} with the values
         * set on this builder.
         *
         * @return A new PresentRepairOrderForApprovalDTO instance.
         */
        public PresentRepairOrderForApprovalDTO build() {
            return new PresentRepairOrderForApprovalDTO(this);
        }
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

