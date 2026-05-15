package se.kth.IV1350.bikerepairshop.model.domain;

import se.kth.IV1350.bikerepairshop.model.dto.DiagnosticReportDTO;

/**
 * Represents a single repair task that is part of a repair order, including its
 * description and the cost of performing it.
 */
public class RepairTask {
    private double cost;
    private String description;


    private RepairTask(Builder builder) {
        this.cost = builder.cost;
        this.description = builder.description;
    }

    public static Builder builder (){
        return new Builder();
    }
    /**
     * Builder for creating instances of {@link RepairTask}.
     * Provides a clear and consistent way to create repair task objects.
     */
    public static class Builder {
        private double cost;
        private String description;

        /**
         * Sets the cost of the repair task.
         * @param cost The cost of performing the task.
         * @return This builder, to allow method chaining.
         */
        public Builder cost(double cost) {
            this.cost = cost;
            return this;
        }

        /**
         * Sets the description of the repair task.
         * @param description The description of what the task involves.
         * @return This builder, to allow method chaining.
         */
        public Builder description(String description) {
            this.description = description;
            return this;
        }

        /**
         * Creates and returns a new {@link RepairTask} with the values
         * set on this builder.
         * @return A new RepairTask instance.
         */
        public RepairTask build() {
            return new RepairTask(this);
        }
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