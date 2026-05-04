package bikerepairshop.model.entity;

/**
 * Represents a repair task as it is stored in the external repair order registry,
 * including a description and the cost of performing the task.
 */
public class RepairTaskEntity {
    private String description;
    private double cost;

    /**
     * Creates a new instance with the specified data.
     *
     * @param description The description of the task.
     * @param cost The cost of performing the task.
     */
    public RepairTaskEntity(String description, double cost) {
        this.description = description;
        this.cost = cost;
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