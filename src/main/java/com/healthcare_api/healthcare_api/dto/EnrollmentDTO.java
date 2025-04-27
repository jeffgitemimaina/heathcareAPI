package com.healthcare_api.healthcare_api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EnrollmentDTO {
    @NotNull(message = "Client ID is required")
    private Long clientId;

    @NotNull(message = "Program ID is required")
    private Long programId;
}