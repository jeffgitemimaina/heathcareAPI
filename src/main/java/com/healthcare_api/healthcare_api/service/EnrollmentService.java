package com.healthcare_api.healthcare_api.service;

import com.healthcare_api.healthcare_api.entity.Client;
import com.healthcare_api.healthcare_api.entity.Enrollment;
import com.healthcare_api.healthcare_api.entity.Program;
import com.healthcare_api.healthcare_api.repository.ClientRepository;
import com.healthcare_api.healthcare_api.repository.EnrollmentRepository;
import com.healthcare_api.healthcare_api.repository.ProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnrollmentService {
    private final ClientRepository clientRepository;
    private final ProgramRepository programRepository;
    private final EnrollmentRepository enrollmentRepository;

    public Enrollment enrollClient(Long clientId, Long programId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        Program program = programRepository.findById(programId)
                .orElseThrow(() -> new RuntimeException("Program not found"));

        Enrollment enrollment = new Enrollment();
        enrollment.setClient(client);
        enrollment.setProgram(program);
        return enrollmentRepository.save(enrollment);
    }
}