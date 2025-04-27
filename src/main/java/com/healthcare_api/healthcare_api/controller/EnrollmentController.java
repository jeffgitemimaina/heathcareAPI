package com.healthcare_api.healthcare_api.controller;

import com.healthcare_api.healthcare_api.dto.EnrollmentDTO;
import com.healthcare_api.healthcare_api.service.EnrollmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<Void> enrollClient(@Valid @RequestBody EnrollmentDTO enrollmentDTO) {
        enrollmentService.enrollClient(enrollmentDTO.getClientId(), enrollmentDTO.getProgramId());
        return ResponseEntity.ok().build();
    }
}