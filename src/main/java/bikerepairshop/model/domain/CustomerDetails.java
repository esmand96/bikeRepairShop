package bikerepairshop.model.domain;


public class CustomerDetails {
    private String name;
    private String email;
    private String phoneNumber;
    private BikeDetails bikeDetails;
    private String consultationId;

    public CustomerDetails(String name, String email, String phoneNumber, BikeDetails bikeDetails, String consultationId){
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.bikeDetails = bikeDetails;
        this.consultationId = consultationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public BikeDetails getBikeDetails() {
        return bikeDetails;
    }

    public void setBikeDetails(BikeDetails bikeDetails) {
        this.bikeDetails = bikeDetails;
    }

    public String getConsultationId() {
        return consultationId;
    }

}
