package com.healthcare_api.healthcare_api.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class EnrollmentDTO {
    @NotNull(message = "Client ID is required")
    private Long clientId;
    @NotEmpty(message = "At least one program ID is required")
    private List<Long> programIds;
}