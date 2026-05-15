package se.kth.IV1350.bikerepairshop.model.entity;

/**
 * Represents a repair task as it is stored in the external repair order registry,
 * including a description and the cost of performing the task.
 */
public class RepairTaskEntity {
    private String description;
    private double cost;


    private RepairTaskEntity(Builder builder) {
        this.description = builder.description;
        this.cost = builder.cost;
    }

    /**
     * Creates and returns a new {@link Builder} instance.
     *
     * @return a new {@link Builder}
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder for creating instances of {@link RepairTaskEntity}.
     * Provides a clear and consistent way to create repair task entity objects.
     */
    public static class Builder {
        private String description;
        private double cost;

        /**
         * Sets the description of the repair task.
         *
         * @param description The description of the task.
         * @return This builder, to allow method chaining.
         */
        public Builder description(String description) {
            this.description = description;
            return this;
        }

        /**
         * Sets the cost of the repair task.
         *
         * @param cost The cost of performing the task.
         * @return This builder, to allow method chaining.
         */
        public Builder cost(double cost) {
            this.cost = cost;
            return this;
        }

        /**
         * Creates and returns a new {@link RepairTaskEntity} with the values
         * set on this builder.
         *
         * @return A new RepairTaskEntity instance.
         */
        public RepairTaskEntity build() {
            return new RepairTaskEntity(this);
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