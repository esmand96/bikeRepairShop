package se.kth.IV1350.bikerepairshop.model.dto;

import se.kth.IV1350.bikerepairshop.model.domain.CustomerDetails;

/**
 * Carries customer details together with the bike data and the consultation id, for
 * transfer from the service layer to the view.
 */
public class CustomerDetailsDTO {
    private final String name;
    private final String email;
    private final String phoneNumber;
    private final String bikeBrand;
    private final String bikeModel;
    private final String bikeSerialNumber;
    private final String consultationId;

    /**
     * Creates a new instance of the customer details DTO with the specified information.
     *
     * @param name              The name of the customer.
     * @param email             The email address of the customer.
     * @param phoneNumber       The phone number of the customer.
     * @param bikeBrand         The brand of the bike.
     * @param bikeModel         The model of the bike.
     * @param bikeSerialNumber  The serial number of the bike.
     * @param consultationId    The unique identifier for the consultation.
     */
    public CustomerDetailsDTO(String name, String email, String phoneNumber, String bikeBrand, String bikeModel, String bikeSerialNumber, String consultationId) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.bikeBrand = bikeBrand;
        this.bikeModel = bikeModel;
        this.bikeSerialNumber = bikeSerialNumber;
        this.consultationId = consultationId;
    }
    /**
     * @return The customer's name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return The customer's email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return The customer's phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @return The brand of the bike.
     */
    public String getBikeBrand() {
        return bikeBrand;
    }

    /**
     * @return The model of the bike.
     */
    public String getBikeModel() {
        return bikeModel;
    }

    /**
     * @return The serial number of the bike.
     */
    public String getBikeSerialNumber() {
        return bikeSerialNumber;
    }

    /**
     * @return The id of the consultation that this visit is based on.
     */
    public String getConsultationId() {
        return consultationId;
    }
}