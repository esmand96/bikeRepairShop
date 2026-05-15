package se.kth.IV1350.bikerepairshop.model.domain;

/**
 * Represents a customer's contact details, the bike they have brought in for repair,
 * and a reference to the consultation associated with the current visit.
 */
public class CustomerDetails {
    private String name;
    private String email;
    private String phoneNumber;
    private BikeDetails bikeDetails;
    private String consultationId;

    private CustomerDetails (Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        this.bikeDetails = builder.bikeDetails;
        this.consultationId = builder.consultationId;

    }

    /**
     * Builder for creating instances of {@link CustomerDetails}.
     * Used to avoid constructors with many parameters of the same type,
     * which makes it easy to accidentally pass arguments in the wrong order.
     */
    public static class Builder {
        private String name;
        private String email;
        private String phoneNumber;
        private BikeDetails bikeDetails;
        private String consultationId;

        /**
         * Sets the customer's name.
         * @param name The customer's full name.
         * @return This builder, to allow method chaining.
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the customer's email address.
         * @param email The customer's email address.
         * @return This builder, to allow method chaining.
         */
        public Builder email(String email) {
            this.email = email;
            return this;
        }

        /**
         * Sets the customer's phone number.
         * @param phoneNumber The customer's phone number.
         * @return This builder, to allow method chaining.
         */
        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        /**
         * Sets the customer's bike details.
         * @param bikeDetails The customer's bike details.
         * @return This builder, to allow method chaining.
         */
        public Builder bikeDetails(BikeDetails bikeDetails) {
            this.bikeDetails = bikeDetails;
            return this;
        }

        /**
         * Sets the consultation id.
         * @param consultationId The id of the consultation this visit is based on.
         * @return This builder, to allow method chaining.
         */
        public Builder consultationId(String consultationId) {
            this.consultationId = consultationId;
            return this;
        }

        /**
         * Creates and returns a new {@link CustomerDetails} with the values
         * set on this builder.
         * @return A new CustomerDetails instance.
         */
        public CustomerDetails build() {
            return new CustomerDetails(this);
        }
    }
    /**
     * @return The customer's name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The new name of the customer.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The customer's email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email The new email address of the customer.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return The customer's phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber The new phone number of the customer.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return The customer's bike details.
     */
    public BikeDetails getBikeDetails() {
        return bikeDetails;
    }

    /**
     * @param bikeDetails The new bike details for the customer.
     */
    public void setBikeDetails(BikeDetails bikeDetails) {
        this.bikeDetails = bikeDetails;
    }

    /**
     * @return The id of the consultation that this visit is based on.
     */
    public String getConsultationId() {
        return consultationId;
    }
}