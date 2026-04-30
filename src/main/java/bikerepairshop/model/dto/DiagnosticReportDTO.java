package bikerepairshop.model.dto;

import java.time.LocalDateTime;

public class DiagnosticReportDTO {
    private final String description;
    private final LocalDateTime estimatedRepairTime;

    public DiagnosticReportDTO(String description, LocalDateTime estimatedRepairTime) {
        this.description = description;
        this.estimatedRepairTime = estimatedRepairTime;
    }

    public LocalDateTime getEstimatedRepairTime() {
        return estimatedRepairTime;
    }

    public String getDescription() {
        return description;
    }
}
