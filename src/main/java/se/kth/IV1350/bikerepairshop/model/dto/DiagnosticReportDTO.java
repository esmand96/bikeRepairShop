package se.kth.IV1350.bikerepairshop.model.dto;

import java.time.LocalDateTime;

/**
 * Carries the technician's diagnosis, including a description and the estimated time
 * when the repair will be completed, for transfer between layers.
 */
public class DiagnosticReportDTO {
    private final String description;
    private final LocalDateTime estimatedRepairTime;


    private DiagnosticReportDTO(Builder builder) {
        this.description = builder.description;
        this.estimatedRepairTime = builder.estimatedRepairTime;
    }
    /**
     * Creates and returns a new {@link Builder} instance.
     *
     * @return a new {@link Builder}
     */
    public static Builder builder() {
        return new Builder();
    }
    /**
     * Builder for creating instances of {@link DiagnosticReportDTO}.
     * Provides a clear and consistent way to create diagnostic report DTO objects.
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
         * Creates and returns a new {@link DiagnosticReportDTO} with the values
         * set on this builder.
         *
         * @return A new DiagnosticReportDTO instance.
         */
        public DiagnosticReportDTO build() {
            return new DiagnosticReportDTO(this);
        }
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