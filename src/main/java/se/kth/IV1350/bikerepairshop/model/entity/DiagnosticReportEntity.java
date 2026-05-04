package se.kth.IV1350.bikerepairshop.model.entity;

import java.time.LocalDateTime;

/**
 * Represents a diagnostic report as it is stored in the external repair order
 * registry, including a description and an estimated time of completion.
 */
public class DiagnosticReportEntity {
    private String description;
    private LocalDateTime estimatedRepairTime;

    /**
     * Creates a new instance with the specified data.
     *
     * @param description The technician's description of the diagnosis.
     * @param estimatedRepairTime The estimated time when the repair will be completed.
     */
    public DiagnosticReportEntity(String description, LocalDateTime estimatedRepairTime) {
        this.description = description;
        this.estimatedRepairTime = estimatedRepairTime;
    }

    /**
     * @return The description of the diagnosis.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The new description of the diagnosis.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return The estimated time when the repair will be completed.
     */
    public LocalDateTime getEstimatedRepairTime() {
        return estimatedRepairTime;
    }

    /**
     * @param estimatedRepairTime The new estimated time for the repair to be completed.
     */
    public void setEstimatedRepairTime(LocalDateTime estimatedRepairTime) {
        this.estimatedRepairTime = estimatedRepairTime;
    }
}