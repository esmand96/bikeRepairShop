package bikerepairshop.model.dto;

public class RepairTaskDTO {
    private final String description;
    private final double cost;

    public RepairTaskDTO(String description, double cost) {
        this.description = description;
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public double getCost() {
        return cost;
    }
}
