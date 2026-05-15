package se.kth.IV1350.bikerepairshop.model.domain;

import java.util.List;

/**
 * Represents a repair order, including the customer's bike and contact details, the
 * technician's diagnosis, the proposed repair tasks, and the current state in the
 * repair order's life cycle.
 */
public class RepairOrder {
    private String date;
    private String problemDescription;
    private RepairOrderState state;
    private CustomerDetails customerDetails;
    private List<RepairTask> repairTasks;
    private DiagnosticReport diagnosticReport;
    private String id;


    private RepairOrder(Builder builder) {
        this.date = builder.date;
        this.problemDescription = builder.problemDescription;
        this.state = builder.state;
        this.customerDetails = builder.customerDetails;
        this.repairTasks = builder.repairTasks;
        this.diagnosticReport = builder.diagnosticReport;
        this.id = builder.id;
    }

    /**
     * Builder for creating instances of {@link RepairOrder}.
     * Used to avoid constructors with many parameters of the same type,
     * which makes it easy to accidentally pass arguments in the wrong order.
     */
    public static class Builder {
        private String date;
        private String problemDescription;
        private RepairOrderState state;
        private CustomerDetails customerDetails;
        private List<RepairTask> repairTasks;
        private DiagnosticReport diagnosticReport;
        private String id;

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
        public Builder state(RepairOrderState state) {
            this.state = state;
            return this;
        }

        /**
         * Sets the customer details of the repair order.
         * @param customerDetails The customer's contact and bike details.
         * @return This builder, to allow method chaining.
         */
        public Builder customerDetails(CustomerDetails customerDetails) {
            this.customerDetails = customerDetails;
            return this;
        }

        /**
         * Sets the repair tasks of the repair order.
         * @param repairTasks The proposed repair tasks, or {@code null} if none have been added yet.
         * @return This builder, to allow method chaining.
         */
        public Builder repairTasks(List<RepairTask> repairTasks) {
            this.repairTasks = repairTasks;
            return this;
        }

        /**
         * Sets the diagnostic report of the repair order.
         * @param diagnosticReport The technician's diagnosis, or {@code null} if no diagnosis has been added yet.
         * @return This builder, to allow method chaining.
         */
        public Builder diagnosticReport(DiagnosticReport diagnosticReport) {
            this.diagnosticReport = diagnosticReport;
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
         * Creates and returns a new {@link RepairOrder} with the values
         * set on this builder.
         * @return A new RepairOrder instance.
         */
        public RepairOrder build() {
            return new RepairOrder(this);
        }

    }

    /**
     * @return The date the order was created.
     */
    public String getDate() {
        return date;
    }

    /**
     * @return The customer's description of the problem.
     */
    public String getProblemDescription() {
        return problemDescription;
    }

    /**
     * @return The current state of the order.
     */
    public RepairOrderState getState() {
        return state;
    }

    /**
     * @param state The new state of the order.
     */
    public void setState(RepairOrderState state) {
        this.state = state;
    }

    /**
     * @return The customer's contact and bike details.
     */
    public CustomerDetails getCustomerDetails() {
        return customerDetails;
    }

    /**
     * @param customerDetails The new customer details.
     */
    public void setCustomerDetails(CustomerDetails customerDetails) {
        this.customerDetails = customerDetails;
    }

    /**
     * @return The proposed repair tasks, or {@code null} if none have been added.
     */
    public List<RepairTask> getRepairTasks() {
        return repairTasks;
    }

    /**
     * @param repairTasks The new list of repair tasks.
     */
    public void setRepairTasks(List<RepairTask> repairTasks) {
        this.repairTasks = repairTasks;
    }

    /**
     * @return The technician's diagnosis, or {@code null} if no diagnosis has been added.
     */
    public DiagnosticReport getDiagnosticReport() {
        return diagnosticReport;
    }

    /**
     * @param diagnosticReport The new diagnostic report.
     */
    public void setDiagnosticReport(DiagnosticReport diagnosticReport) {
        this.diagnosticReport = diagnosticReport;
    }

    /**
     * Calculates the total cost of the order by summing the costs of all repair tasks.
     *
     * @return The total cost of all repair tasks, or {@code 0.0} if no tasks have been added.
     */
    public double calculateTotalCost() {
        double totalCost = 0.0;
        if (repairTasks == null)
            return totalCost;
        for (RepairTask repairTask : repairTasks) {
            totalCost += repairTask.getCost();
        }
        return totalCost;
    }

    /**
     * Transitions the order to the specified next state, if the current state allows
     * the transition. Allowed transitions are
     * {@code NEWLY_CREATED} to {@code READY_FOR_APPROVAL} and
     * {@code READY_FOR_APPROVAL} to {@code ACCEPTED}. Other transitions are silently
     * ignored.
     *
     * @param nextState The state to transition to.
     */
    public void transitionState(RepairOrderState nextState) {
        if (nextState == RepairOrderState.READY_FOR_APPROVAL) {
            if (state != RepairOrderState.NEWLY_CREATED)
                return;
            else
                setState(nextState);
        } else if (nextState == RepairOrderState.ACCEPTED) {
            if (state != RepairOrderState.READY_FOR_APPROVAL)
                return;
            else
                setState(nextState);
        }
    }

    /**
     * @return The unique id of the order.
     */
    public String getId() {
        return id;
    }
}