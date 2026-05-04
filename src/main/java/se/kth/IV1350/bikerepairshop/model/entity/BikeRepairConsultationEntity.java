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

    /**
     * Creates a new instance with the specified data.
     *
     * @param date The date of the consultation.
     * @param id The unique id of the consultation.
     * @param model The model of the bike.
     * @param brand The brand of the bike.
     * @param serialNumber The serial number of the bike.
     */
    public BikeRepairConsultationEntity(String date, String id, String model, String brand, String serialNumber) {
        this.date = date;
        this.id = id;
        this.model = model;
        this.brand = brand;
        this.serialNumber = serialNumber;
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