package se.kth.IV1350.bikerepairshop.model.dto.common;

/**
 * Carries customer details together with the bike data and the consultation id, for
 * transfer from the service layer to the view.
 */
public class CustomerDetailsDTO {
    private final String name;
    private final String email;
    private final String phoneNumber;
    private final String bikeBrand;
    private final String bikeModel;
    private final String bikeSerialNumber;
    private final String consultationId;


    private CustomerDetailsDTO(Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        this.bikeBrand = builder.bikeBrand;
        this.bikeModel = builder.bikeModel;
        this.bikeSerialNumber = builder.bikeSerialNumber;
        this.consultationId = builder.consultationId;
    }

    /**
     * Creates and returns a new {@link Builder} instance.
     *
     * @return a new {@link Builder}
     */
    public static Builder builder() {
        return new Builder();}

    /**
     * Builder for creating instances of {@link CustomerDetailsDTO}.
     * Provides a clear and consistent way to create customer details DTO objects.
     */
    public static class Builder {
        private String name;
        private String email;
        private String phoneNumber;
        private String bikeBrand;
        private String bikeModel;
        private String bikeSerialNumber;
        private String consultationId;

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
         * Sets the consultation id.
         *
         * @param consultationId The id of the consultation this visit is based on.
         * @return This builder, to allow method chaining.
         */
        public Builder consultationId(String consultationId) {
            this.consultationId = consultationId;
            return this;
        }

        /**
         * Creates and returns a new {@link CustomerDetailsDTO} with the values
         * set on this builder.
         *
         * @return A new CustomerDetailsDTO instance.
         */
        public CustomerDetailsDTO build() {
            return new CustomerDetailsDTO(this);
        }
    }


    /**
     * @return The customer's name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return The customer's email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return The customer's phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
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
     * @return The serial number of the bike.
     */
    public String getBikeSerialNumber() {
        return bikeSerialNumber;
    }

    /**
     * @return The id of the consultation that this visit is based on.
     */
    public String getConsultationId() {
        return consultationId;
    }
}