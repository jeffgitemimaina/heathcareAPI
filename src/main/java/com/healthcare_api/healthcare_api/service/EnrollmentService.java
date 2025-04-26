package com.healthcare_api.healthcare_api.service;

import com.healthcare_api.healthcare_api.dto.EnrollmentDTO;
import com.healthcare_api.healthcare_api.entity.Client;
import com.healthcare_api.healthcare_api.entity.Enrollment;
import com.healthcare_api.healthcare_api.entity.Program;
import com.healthcare_api.healthcare_api.repository.ClientRepository;
import com.healthcare_api.healthcare_api.repository.EnrollmentRepository;
import com.healthcare_api.healthcare_api.repository.ProgramRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnrollmentService {
    private static final Logger logger = LoggerFactory.getLogger(EnrollmentService.class);
    private final ClientRepository clientRepository;
    private final ProgramRepository programRepository;
    private final EnrollmentRepository enrollmentRepository;

    public void enrollClient(EnrollmentDTO enrollmentDTO) {
        logger.info("Enrolling client ID: {} in programs: {}", enrollmentDTO.getClientId(), enrollmentDTO.getProgramIds());
        Client client = clientRepository.findById(enrollmentDTO.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));

        for (Long programId : enrollmentDTO.getProgramIds()) {
            Program program = programRepository.findById(programId)
                    .orElseThrow(() -> new RuntimeException("Program not found"));
            Enrollment enrollment = new Enrollment();
            enrollment.setClient(client);
            enrollment.setProgram(program);
            enrollmentRepository.save(enrollment);
            logger.info("Enrolled client {} in program {}", client.getId(), program.getId());
        }
    }
}