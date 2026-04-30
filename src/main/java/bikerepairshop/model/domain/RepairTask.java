package bikerepairshop.model.domain;

public class RepairTask {
    private double cost;
    private String description;

    public RepairTask(double cost, String description){
        this.cost = cost;
        this.description = description;
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
