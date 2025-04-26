package com.healthcare_api.healthcare_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProgramDTO {
    private Long id;
    @NotBlank(message = "Program name is required")
    private String name;
    private String description;
}