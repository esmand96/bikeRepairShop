package bikerepairshop.model.entity;

public class RepairTaskEntity {
    private String description;
    private double cost;

    public RepairTaskEntity(String description, double cost) {
        this.description = description;
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
