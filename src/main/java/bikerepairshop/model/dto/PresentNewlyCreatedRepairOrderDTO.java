package bikerepairshop.model.dto;

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

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }

    public String getRepairOrderId() {
        return repairOrderId;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public String getBikeSerialNumber() {
        return bikeSerialNumber;
    }

    public String getBikeBrand() {
        return bikeBrand;
    }

    public String getBikeModel() {
        return bikeModel;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
