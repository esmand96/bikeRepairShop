package se.kth.IV1350.bikerepairshop.model.entity;

/**
 * Represents a previously booked bike repair consultation as it is stored in the
 * external customer registry, including the date, the bike's identifying data, and a
 * unique id.
 */
public class BikeRepairConsultationEntity {
    private String date;
    private String serialNumber;
    private String brand;
    private String model;
    private String id;

    private BikeRepairConsultationEntity(Builder builder) {
        this.date = builder.date;
        this.id = builder.id;
        this.model = builder.model;
        this.brand = builder.brand;
        this.serialNumber = builder.serialNumber;
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
     * Builder for creating instances of {@link BikeRepairConsultationEntity}.
     * Provides a clear and consistent way to create bike repair consultation entity objects.
     */
    public static class Builder {
        private String date;
        private String serialNumber;
        private String brand;
        private String model;
        private String id;

        /**
         * Sets the date of the consultation.
         *
         * @param date The date of the consultation.
         * @return This builder, to allow method chaining.
         */
        public Builder date(String date) {
            this.date = date;
            return this;
        }

        /**
         * Sets the serial number of the bike.
         *
         * @param serialNumber The serial number of the bike.
         * @return This builder, to allow method chaining.
         */
        public Builder serialNumber(String serialNumber) {
            this.serialNumber = serialNumber;
            return this;
        }

        /**
         * Sets the brand of the bike.
         *
         * @param brand The brand of the bike.
         * @return This builder, to allow method chaining.
         */
        public Builder brand(String brand) {
            this.brand = brand;
            return this;
        }

        /**
         * Sets the model of the bike.
         *
         * @param model The model of the bike.
         * @return This builder, to allow method chaining.
         */
        public Builder model(String model) {
            this.model = model;
            return this;
        }

        /**
         * Sets the unique id of the consultation.
         *
         * @param id The unique id of the consultation.
         * @return This builder, to allow method chaining.
         */
        public Builder id(String id) {
            this.id = id;
            return this;
        }

        /**
         * Creates and returns a new {@link BikeRepairConsultationEntity} with the values
         * set on this builder.
         *
         * @return A new BikeRepairConsultationEntity instance.
         */
        public BikeRepairConsultationEntity build() {
            return new BikeRepairConsultationEntity(this);
        }
    }

    /**
     * @return The date of the consultation.
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date The new date of the consultation.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return The unique id of the consultation.
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The new id of the consultation.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return The brand of the bike.
     */
    public String getBrand() {
        return brand;
    }

    /**
     * @param brand The new brand of the bike.
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * @return The serial number of the bike.
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * @param serialNumber The new serial number of the bike.
     */
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * @return The model of the bike.
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model The new model of the bike.
     */
    public void setModel(String model) {
        this.model = model;
    }
}