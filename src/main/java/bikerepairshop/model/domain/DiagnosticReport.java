package bikerepairshop.model.domain;


import java.time.LocalDateTime;

public class DiagnosticReport {
    private String description;
    private LocalDateTime estimatedRepairTime;

    public DiagnosticReport (String description, LocalDateTime estimatedRepairTime){
        this.description = description;
        this.estimatedRepairTime = estimatedRepairTime;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public LocalDateTime getEstimatedRepairTime() {

        return estimatedRepairTime;
    }

    public void setEstimatedRepairTime(LocalDateTime estimatedRepairTime) {
        this.estimatedRepairTime = estimatedRepairTime;


    }
}
