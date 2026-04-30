package bikerepairshop.model.entity;

import java.time.LocalDateTime;

public class DiagnosticReportEntity {
    private String description;
    private LocalDateTime estimatedRepairTime;

    public DiagnosticReportEntity(String description, LocalDateTime estimatedRepairTime) {
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
