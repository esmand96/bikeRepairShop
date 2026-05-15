package se.kth.IV1350.bikerepairshop.model.dto.common;

import java.util.List;

public class RepairOrderUpdatedDTO {
    private final String name;
    private final String email;
    private final String phoneNumber;
    private final String bikeBrand;
    private final String bikeModel;
    private final String bikeSerialNumber;
    private final String problemDescription;
    private final String state;
    private final String repairOrderId;
    private final DiagnosticReportDTO diagnosticReport;
    private final List<RepairTaskDTO> proposedRepairTasks;;


    private RepairOrderUpdatedDTO(String name,
                                 String email,
                                 String phoneNumber,
                                 String bikeBrand,
                                 String bikeModel,
                                 String bikeSerialNumber,
                                 String problemDescription,
                                 String state,
                                 String repairOrderId,
                                 DiagnosticReportDTO diagnosticReport,
                                 List<RepairTaskDTO> proposedRepairTasks) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.bikeBrand = bikeBrand;
        this.bikeModel = bikeModel;
        this.bikeSerialNumber = bikeSerialNumber;
        this.problemDescription = problemDescription;
        this.state = state;
        this.repairOrderId = repairOrderId;
        this.diagnosticReport = diagnosticReport;
        this.proposedRepairTasks = proposedRepairTasks;
    }

    public static Builder builder(){
        return new Builder();
    }

    public String getName() {
        return name;
    }

    public List<RepairTaskDTO> getProposedRepairTasks() {
        return proposedRepairTasks;
    }

    public DiagnosticReportDTO getDiagnosticReport() {
        return diagnosticReport;
    }

    public String getRepairOrderId() {
        return repairOrderId;
    }

    public String getState() {
        return state;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public String getBikeSerialNumber() {
        return bikeSerialNumber;
    }

    public String getBikeModel() {
        return bikeModel;
    }

    public String getBikeBrand() {
        return bikeBrand;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public static class Builder {
        private String name;
        private String email;
        private String phoneNumber;
        private String bikeBrand;
        private String bikeModel;
        private String bikeSerialNumber;
        private String problemDescription;
        private String state;
        private String repairOrderId;
        private DiagnosticReportDTO diagnosticReport;
        private List<RepairTaskDTO> proposedRepairTasks;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder bikeBrand(String bikeBrand) {
            this.bikeBrand = bikeBrand;
            return this;
        }

        public Builder bikeModel(String bikeModel) {
            this.bikeModel = bikeModel;
            return this;
        }

        public Builder bikeSerialNumber(String bikeSerialNumber) {
            this.bikeSerialNumber = bikeSerialNumber;
            return this;
        }

        public Builder problemDescription(String problemDescription) {
            this.problemDescription = problemDescription;
            return this;
        }

        public Builder state(String state) {
            this.state = state;
            return this;
        }

        public Builder repairOrderId(String repairOrderId) {
            this.repairOrderId = repairOrderId;
            return this;
        }

        public Builder diagnosticReport(DiagnosticReportDTO diagnosticReport) {
            this.diagnosticReport = diagnosticReport;
            return this;
        }

        public Builder proposedRepairTasks(List<RepairTaskDTO> proposedRepairTasks) {
            this.proposedRepairTasks = proposedRepairTasks;
            return this;
        }

        public RepairOrderUpdatedDTO build() {
            return new RepairOrderUpdatedDTO(
                    name,
                    email,
                    phoneNumber,
                    bikeBrand,
                    bikeModel,
                    bikeSerialNumber,
                    problemDescription,
                    state,
                    repairOrderId,
                    diagnosticReport,
                    proposedRepairTasks
            );
        }
    }


}

