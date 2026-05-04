package se.kth.IV1350.bikerepairshop.model.dto;

/**
 * Carries the data needed to present a newly created repair order to the technician,
 * including customer details, bike details, the problem description, and the order's
 * current state.
 */
public class PresentNewlyCreatedRepairOrderDTO {
    private final String name;
    private final String email;
    private final String phoneNumber;
    private final String bikeBrand;
    private final String bikeModel;
    private final String bikeSerialNumber;
    private final String problemDescription;
    private final String state;
    private final String repairOrderId;

    /**
     * Creates a new instance with the specified data.
     *
     * @param name The customer's name.
     * @param email The customer's email address.
     * @param phoneNumber The customer's phone number.
     * @param bikeBrand The brand of the bike.
     * @param bikeModel The model of the bike.
     * @param bikeSerialNumber The serial number of the bike.
     * @param problemDescription The customer's description of the problem.
     * @param state The current state of the repair order.
     * @param repairOrderId The id of the repair order.
     */
    public PresentNewlyCreatedRepairOrderDTO(String name,
                                             String email,
                                             String phoneNumber,
                                             String bikeBrand,
                                             String bikeModel,
                                             String bikeSerialNumber,
                                             String problemDescription,
                                             String state,
                                             String repairOrderId) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.bikeBrand = bikeBrand;
        this.bikeModel = bikeModel;
        this.bikeSerialNumber = bikeSerialNumber;
        this.problemDescription = problemDescription;
        this.state = state;
        this.repairOrderId = repairOrderId;
    }

    /**
     * @return The customer's email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return The customer's name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return The current state of the repair order.
     */
    public String getState() {
        return state;
    }

    /**
     * @return The id of the repair order.
     */
    public String getRepairOrderId() {
        return repairOrderId;
    }

    /**
     * @return The customer's description of the problem.
     */
    public String getProblemDescription() {
        return problemDescription;
    }

    /**
     * @return The serial number of the bike.
     */
    public String getBikeSerialNumber() {
        return bikeSerialNumber;
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
     * @return The customer's phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
}