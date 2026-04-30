package bikerepairshop.model.entity;

public class BikeRepairConsultationEntity {
    private String date;
    private String serialNumber;
    private String brand;
    private String model;
    private String id;

    public BikeRepairConsultationEntity(String date, String id, String model, String brand, String serialNumber) {
        this.date = date;
        this.id = id;
        this.model = model;
        this.brand = brand;
        this.serialNumber = serialNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
