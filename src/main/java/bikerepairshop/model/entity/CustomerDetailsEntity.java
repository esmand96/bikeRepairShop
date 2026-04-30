package bikerepairshop.model.entity;

import java.util.List;

public class CustomerDetailsEntity {
    private String name;
    private String email;
    private String phoneNumber;
    private List<BikeRepairConsultationEntity> consultations;

    public CustomerDetailsEntity(String name, String email, String phoneNumber, List<BikeRepairConsultationEntity> consultations) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.consultations = consultations;
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

    public List<BikeRepairConsultationEntity> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<BikeRepairConsultationEntity> consultations) {
        this.consultations = consultations;
    }
}
