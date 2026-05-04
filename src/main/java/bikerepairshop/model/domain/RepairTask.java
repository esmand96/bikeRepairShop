package bikerepairshop.model.domain;

/**
 * Represents a single repair task that is part of a repair order, including its
 * description and the cost of performing it.
 */
public class RepairTask {
    private double cost;
    private String description;

    /**
     * Creates a new repair task with the specified cost and description.
     *
     * @param cost The cost of performing the task.
     * @param description The description of what the task involves.
     */
    public RepairTask(double cost, String description) {
        this.cost = cost;
        this.description = description;
    }

    /**
     * @return The cost of performing the task.
     */
    public double getCost() {
        return cost;
    }

    /**
     * @param cost The new cost of the task.
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The new description of the task.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}