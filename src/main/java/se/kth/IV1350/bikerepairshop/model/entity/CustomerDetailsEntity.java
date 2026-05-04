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

    /**
     * Creates a new instance with the specified data.
     *
     * @param name The customer's name.
     * @param email The customer's email address.
     * @param phoneNumber The customer's phone number.
     * @param consultations The customer's previously booked consultations.
     */
    public CustomerDetailsEntity(String name, String email, String phoneNumber, List<BikeRepairConsultationEntity> consultations) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.consultations = consultations;
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