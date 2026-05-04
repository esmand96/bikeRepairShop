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

    /**
     * Creates a new repair order with the specified data.
     *
     * @param date The date the order was created.
     * @param problemDescription The customer's description of the problem.
     * @param state The initial state of the order.
     * @param customerDetails The customer's contact and bike details.
     * @param repairTasks The proposed repair tasks, or {@code null} if none have been added yet.
     * @param diagnosticReport The technician's diagnosis, or {@code null} if no diagnosis has been added yet.
     * @param id The unique id of the order.
     */
    public RepairOrder(String date, String problemDescription, RepairOrderState state, CustomerDetails customerDetails, List<RepairTask> repairTasks, DiagnosticReport diagnosticReport, String id) {
        this.date = date;
        this.problemDescription = problemDescription;
        this.state = state;
        this.customerDetails = customerDetails;
        this.repairTasks = repairTasks;
        this.diagnosticReport = diagnosticReport;
        this.id = id;
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