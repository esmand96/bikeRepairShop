package se.kth.IV1350.bikerepairshop.model.dto;

/**
 * Carries data describing a single repair task, including its description and cost,
 * for transfer between layers. Used both as input from the technician and as output
 * to the receptionist and on the printed receipt.
 */
public class RepairTaskDTO {
    private final String description;
    private final double cost;


    private RepairTaskDTO(Builder builder) {
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
     * Builder for creating instances of {@link RepairTaskDTO}.
     * Provides a clear and consistent way to create repair task DTO objects.
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
         * Creates and returns a new {@link RepairTaskDTO} with the values
         * set on this builder.
         *
         * @return A new RepairTaskDTO instance.
         */
        public RepairTaskDTO build() {
            return new RepairTaskDTO(this);
        }
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