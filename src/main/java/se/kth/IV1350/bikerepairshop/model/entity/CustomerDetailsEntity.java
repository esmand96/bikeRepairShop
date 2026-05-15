package se.kth.IV1350.bikerepairshop.model.entity;

import java.util.List;

/**
 * Represents a customer as stored in the external customer registry, including contact
 * details and the list of previously booked consultations.
 */
public class CustomerDetailsEntity {
    private String name;
    private String email;
    private String phoneNumber;
    private List<BikeRepairConsultationEntity> consultations;

    private CustomerDetailsEntity(Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        this.consultations = builder.consultations;
    }

    /**
     * Builder for creating instances of {@link CustomerDetailsEntity}.
     * Provides a clear and consistent way to create customer details entity objects.
     */
    public static class Builder {
        private String name;
        private String email;
        private String phoneNumber;
        private List<BikeRepairConsultationEntity> consultations;

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
         * Sets the customer's previously booked consultations.
         *
         * @param consultations The list of consultations.
         * @return This builder, to allow method chaining.
         */
        public Builder consultations(List<BikeRepairConsultationEntity> consultations) {
            this.consultations = consultations;
            return this;
        }

        /**
         * Creates and returns a new {@link CustomerDetailsEntity} with the values
         * set on this builder.
         *
         * @return A new CustomerDetailsEntity instance.
         */
        public CustomerDetailsEntity build() {
            return new CustomerDetailsEntity(this);
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
     * @return The customer's previously booked consultations.
     */
    public List<BikeRepairConsultationEntity> getConsultations() {
        return consultations;
    }

    /**
     * @param consultations The new list of consultations.
     */
    public void setConsultations(List<BikeRepairConsultationEntity> consultations) {
        this.consultations = consultations;
    }
}