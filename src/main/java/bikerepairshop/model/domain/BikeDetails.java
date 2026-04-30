package bikerepairshop.model.domain;


public class BikeDetails {
    private String brand;
    private String model;
    private String serialNumber;

    public BikeDetails(String brand, String model, String serialNumber){
        this.brand = brand;
        this.model = model;
        this.serialNumber = serialNumber;
    }

    public void setBrand(String brand){
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setModel(String model){
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setSerialNumber(String serialNumber){
        this.serialNumber = serialNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }
}