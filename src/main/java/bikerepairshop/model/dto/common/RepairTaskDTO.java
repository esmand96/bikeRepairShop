package bikerepairshop.model.dto.common;

/**
 * Carries data describing a single repair task, including its description and cost,
 * for transfer between layers. Used both as input from the technician and as output
 * to the receptionist and on the printed receipt.
 */
public class RepairTaskDTO {
    private final String description;
    private final double cost;

    /**
     * Creates a new instance with the specified data.
     *
     * @param description The description of the task.
     * @param cost The cost of performing the task.
     */
    public RepairTaskDTO(String description, double cost) {
        this.description = description;
        this.cost = cost;
    }

    /**
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return The cost of performing the task.
     */
    public double getCost() {
        return cost;
    }
}