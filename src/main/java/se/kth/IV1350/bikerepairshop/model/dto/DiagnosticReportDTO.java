package se.kth.IV1350.bikerepairshop.model.dto;

import java.time.LocalDateTime;

/**
 * Carries the technician's diagnosis, including a description and the estimated time
 * when the repair will be completed, for transfer between layers.
 */
public class DiagnosticReportDTO {
    private final String description;
    private final LocalDateTime estimatedRepairTime;

    /**
     * Creates a new instance with the specified data.
     *
     * @param description The technician's description of the diagnosis.
     * @param estimatedRepairTime The estimated time when the repair will be completed.
     */
    public DiagnosticReportDTO(String description, LocalDateTime estimatedRepairTime) {
        this.description = description;
        this.estimatedRepairTime = estimatedRepairTime;
    }

    /**
     * @return The estimated time when the repair will be completed.
     */
    public LocalDateTime getEstimatedRepairTime() {
        return estimatedRepairTime;
    }

    /**
     * @return The description of the diagnosis.
     */
    public String getDescription() {
        return description;
    }
}