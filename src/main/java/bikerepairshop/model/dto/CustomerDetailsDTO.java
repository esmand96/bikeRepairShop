package bikerepairshop.model.dto;
import bikerepairshop.model.domain.CustomerDetails;

public class CustomerDetailsDTO {
    private final String name;
    private final String email;
    private final String phoneNumber;
    private final String bikeBrand;
    private final String bikeModel;
    private final String bikeSerialNumber;
    private final String consultationId;

    public CustomerDetailsDTO (CustomerDetails customerDetails){
        this.name = customerDetails.getName();
        this.email = customerDetails.getEmail();
        this.phoneNumber = customerDetails.getPhoneNumber();
        this.bikeBrand = customerDetails.getBikeDetails().getBrand();
        this.bikeModel = customerDetails.getBikeDetails().getModel();
        this.bikeSerialNumber = customerDetails.getBikeDetails().getSerialNumber();
        this.consultationId = customerDetails.getConsultationId();
    }
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getBikeBrand() {
        return bikeBrand;
    }

    public String getBikeModel() {
        return bikeModel;
    }

    public String getBikeSerialNumber() {
        return bikeSerialNumber;
    }

    public String getConsultationId() {
        return consultationId;
    }
}
