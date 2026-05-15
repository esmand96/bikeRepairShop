package se.kth.IV1350.bikerepairshop.model.dto.common;

/**
 * Carries the data needed to present a newly created repair order to the technician,
 * including customer details, bike details, the problem description, and the order's
 * current state.
 */
public class PresentNewlyCreatedRepairOrderDTO {
    private final String name;
    private final String email;
    private final String phoneNumber;
    private final String bikeBrand;
    private final String bikeModel;
    private final String bikeSerialNumber;
    private final String problemDescription;
    private final String state;
    private final String repairOrderId;

    private PresentNewlyCreatedRepairOrderDTO(Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        this.bikeBrand = builder.bikeBrand;
        this.bikeModel = builder.bikeModel;
        this.bikeSerialNumber = builder.bikeSerialNumber;
        this.problemDescription = builder.problemDescription;
        this.state = builder.state;
        this.repairOrderId = builder.repairOrderId;
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
     * Builder for creating instances of {@link PresentNewlyCreatedRepairOrderDTO}.
     * Provides a clear and consistent way to create newly created repair order DTO objects.
     */
    public static class Builder {
        private String name;
        private String email;
        private String phoneNumber;
        private String bikeBrand;
        private String bikeModel;
        private String bikeSerialNumber;
        private String problemDescription;
        private String state;
        private String repairOrderId;

        /**
         * Sets the customer's name.
         *
         * @param name The customer's full name.
         * @return This builder, to allow method chaining.
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the customer's email address.
         *
         * @param email The customer's email address.
         * @return This builder, to allow method chaining.
         */
        public Builder email(String email) {
            this.email = email;
            return this;
        }

        /**
         * Sets the customer's phone number.
         *
         * @param phoneNumber The customer's phone number.
         * @return This builder, to allow method chaining.
         */
        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        /**
         * Sets the bike brand.
         *
         * @param bikeBrand The brand of the bike.
         * @return This builder, to allow method chaining.
         */
        public Builder bikeBrand(String bikeBrand) {
            this.bikeBrand = bikeBrand;
            return this;
        }

        /**
         * Sets the bike model.
         *
         * @param bikeModel The model of the bike.
         * @return This builder, to allow method chaining.
         */
        public Builder bikeModel(String bikeModel) {
            this.bikeModel = bikeModel;
            return this;
        }

        /**
         * Sets the bike serial number.
         *
         * @param bikeSerialNumber The serial number of the bike.
         * @return This builder, to allow method chaining.
         */
        public Builder bikeSerialNumber(String bikeSerialNumber) {
            this.bikeSerialNumber = bikeSerialNumber;
            return this;
        }

        /**
         * Sets the problem description.
         *
         * @param problemDescription The customer's description of the problem.
         * @return This builder, to allow method chaining.
         */
        public Builder problemDescription(String problemDescription) {
            this.problemDescription = problemDescription;
            return this;
        }

        /**
         * Sets the state of the repair order.
         *
         * @param state The current state of the repair order.
         * @return This builder, to allow method chaining.
         */
        public Builder state(String state) {
            this.state = state;
            return this;
        }

        /**
         * Sets the repair order id.
         *
         * @param repairOrderId The id of the repair order.
         * @return This builder, to allow method chaining.
         */
        public Builder repairOrderId(String repairOrderId) {
            this.repairOrderId = repairOrderId;
            return this;
        }

        /**
         * Creates and returns a new {@link PresentNewlyCreatedRepairOrderDTO} with the values
         * set on this builder.
         *
         * @return A new PresentNewlyCreatedRepairOrderDTO instance.
         */
        public PresentNewlyCreatedRepairOrderDTO build() {
            return new PresentNewlyCreatedRepairOrderDTO(this);
        }
    }


    /**
     * @return The customer's email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return The customer's name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return The current state of the repair order.
     */
    public String getState() {
        return state;
    }

    /**
     * @return The id of the repair order.
     */
    public String getRepairOrderId() {
        return repairOrderId;
    }

    /**
     * @return The customer's description of the problem.
     */
    public String getProblemDescription() {
        return problemDescription;
    }

    /**
     * @return The serial number of the bike.
     */
    public String getBikeSerialNumber() {
        return bikeSerialNumber;
    }

    /**
     * @return The brand of the bike.
     */
    public String getBikeBrand() {
        return bikeBrand;
    }

    /**
     * @return The model of the bike.
     */
    public String getBikeModel() {
        return bikeModel;
    }

    /**
     * @return The customer's phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
}