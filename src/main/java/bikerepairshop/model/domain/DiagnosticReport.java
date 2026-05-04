package bikerepairshop.model.domain;

import java.time.LocalDateTime;

/**
 * Represents the technician's diagnosis of a bike, including a description of the
 * problem and an estimate of when the repair will be completed.
 */
public class DiagnosticReport {
    private String description;
    private LocalDateTime estimatedRepairTime;

    /**
     * Creates a new diagnostic report with the specified description and estimated
     * repair time.
     *
     * @param description The technician's description of the diagnosis.
     * @param estimatedRepairTime The estimated time when the repair will be completed.
     */
    public DiagnosticReport(String description, LocalDateTime estimatedRepairTime) {
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