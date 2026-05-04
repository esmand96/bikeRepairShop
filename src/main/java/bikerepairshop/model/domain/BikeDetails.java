package bikerepairshop.model.domain;

/**
 * Represents the make and identity of a bike that is being repaired.
 */
public class BikeDetails {
    private String brand;
    private String model;
    private String serialNumber;

    /**
     * Creates a new instance representing the specified bike.
     *
     * @param brand The manufacturer of the bike.
     * @param model The model name of the bike.
     * @param serialNumber The unique serial number of the bike.
     */
    public BikeDetails(String brand, String model, String serialNumber) {
        this.brand = brand;
        this.model = model;
        this.serialNumber = serialNumber;
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