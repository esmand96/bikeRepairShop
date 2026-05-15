package se.kth.IV1350.bikerepairshop.model.domain;

/**
 * Represents the make and identity of a bike that is being repaired.
 */
public class BikeDetails {
    private String brand;
    private String model;
    private String serialNumber;

    private BikeDetails(Builder builder) {
        this.brand = builder.brand;
        this.model = builder.model;
        this.serialNumber = builder.serialNumber;
    }

    /**
     * Builder for creating instances of {@link BikeDetails}.
     * Provides a clear and consistent way to create bike details objects.
     */
    public static class Builder {
        private String brand;
        private String model;
        private String serialNumber;

        /**
         * Sets the brand of the bike.
         *
         * @param brand The manufacturer of the bike.
         * @return This builder, to allow method chaining.
         */
        public Builder brand(String brand) {
            this.brand = brand;
            return this;
        }

        /**
         * Sets the model of the bike.
         *
         * @param model The model name of the bike.
         * @return This builder, to allow method chaining.
         */
        public Builder model(String model) {
            this.model = model;
            return this;
        }

        /**
         * Sets the serial number of the bike.
         *
         * @param serialNumber The unique serial number of the bike.
         * @return This builder, to allow method chaining.
         */
        public Builder serialNumber(String serialNumber) {
            this.serialNumber = serialNumber;
            return this;
        }

        /**
         * Creates and returns a new {@link BikeDetails} with the values
         * set on this builder.
         *
         * @return A new BikeDetails instance.
         */
        public BikeDetails build() {
            return new BikeDetails(this);
        }
    }

    /**
     * @param brand The new brand of the bike.
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * @return The brand of the bike.
     */
    public String getBrand() {
        return brand;
    }

    /**
     * @param model The new model of the bike.
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return The model of the bike.
     */
    public String getModel() {
        return model;
    }

    /**
     * @param serialNumber The new serial number of the bike.
     */
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * @return The serial number of the bike.
     */
    public String getSerialNumber() {
        return serialNumber;
    }
}