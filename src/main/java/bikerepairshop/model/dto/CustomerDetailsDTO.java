package bikerepairshop.model.dto;

import bikerepairshop.model.domain.CustomerDetails;

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
     * Creates a new instance by extracting the relevant fields from the specified
     * domain object.
     *
     * @param customerDetails The domain object to extract data from.
     */
    public CustomerDetailsDTO(CustomerDetails customerDetails) {
        this.name = customerDetails.getName();
        this.email = customerDetails.getEmail();
        this.phoneNumber = customerDetails.getPhoneNumber();
        this.bikeBrand = customerDetails.getBikeDetails().getBrand();
        this.bikeModel = customerDetails.getBikeDetails().getModel();
        this.bikeSerialNumber = customerDetails.getBikeDetails().getSerialNumber();
        this.consultationId = customerDetails.getConsultationId();
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