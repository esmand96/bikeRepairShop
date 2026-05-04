package se.kth.IV1350.bikerepairshop.model.domain;

/**
 * Represents a customer's contact details, the bike they have brought in for repair,
 * and a reference to the consultation associated with the current visit.
 */
public class CustomerDetails {
    private String name;
    private String email;
    private String phoneNumber;
    private BikeDetails bikeDetails;
    private String consultationId;

    /**
     * Creates a new instance representing the specified customer.
     *
     * @param name The customer's full name.
     * @param email The customer's email address.
     * @param phoneNumber The customer's phone number.
     * @param bikeDetails The customer's bike details.
     * @param consultationId The id of the consultation this visit is based on.
     */
    public CustomerDetails(String name, String email, String phoneNumber, BikeDetails bikeDetails, String consultationId) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.bikeDetails = bikeDetails;
        this.consultationId = consultationId;
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
     * @return The customer's bike details.
     */
    public BikeDetails getBikeDetails() {
        return bikeDetails;
    }

    /**
     * @param bikeDetails The new bike details for the customer.
     */
    public void setBikeDetails(BikeDetails bikeDetails) {
        this.bikeDetails = bikeDetails;
    }

    /**
     * @return The id of the consultation that this visit is based on.
     */
    public String getConsultationId() {
        return consultationId;
    }
}