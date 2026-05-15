package se.kth.IV1350.bikerepairshop.model.domain;

import java.time.LocalDateTime;

/**
 * Represents the technician's diagnosis of a bike, including a description of the
 * problem and an estimate of when the repair will be completed.
 */
public class DiagnosticReport {
    private String description;
    private LocalDateTime estimatedRepairTime;

    private DiagnosticReport(Builder builder) {
        this.description = builder.description;
        this.estimatedRepairTime = builder.estimatedRepairTime;
    }
    public static Builder builder (){
        return new Builder();
    }

    /**
     * Builder for creating instances of {@link DiagnosticReport}.
     * Provides a clear and consistent way to create diagnostic report objects.
     */
    public static class Builder {
        private String description;
        private LocalDateTime estimatedRepairTime;

        /**
         * Sets the description of the diagnosis.
         *
         * @param description The technician's description of the diagnosis.
         * @return This builder, to allow method chaining.
         */
        public Builder description(String description) {
            this.description = description;
            return this;
        }

        /**
         * Sets the estimated repair time.
         *
         * @param estimatedRepairTime The estimated time when the repair will be completed.
         * @return This builder, to allow method chaining.
         */
        public Builder estimatedRepairTime(LocalDateTime estimatedRepairTime) {
            this.estimatedRepairTime = estimatedRepairTime;
            return this;
        }

        /**
         * Creates and returns a new {@link DiagnosticReport} with the values
         * set on this builder.
         *
         * @return A new DiagnosticReport instance.
         */
        public DiagnosticReport build() {
            return new DiagnosticReport(this);
        }
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