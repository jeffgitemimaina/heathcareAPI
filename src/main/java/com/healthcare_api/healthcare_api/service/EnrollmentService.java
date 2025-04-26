package com.healthcare_api.healthcare_api.service;

import com.healthcare_api.healthcare_api.dto.EnrollmentDTO;
import com.healthcare_api.healthcare_api.entity.Client;
import com.healthcare_api.healthcare_api.entity.Enrollment;
import com.healthcare_api.healthcare_api.entity.Program;
import com.healthcare_api.healthcare_api.repository.ClientRepository;
import com.healthcare_api.healthcare_api.repository.EnrollmentRepository;
import com.healthcare_api.healthcare_api.repository.ProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final ClientRepository clientRepository;
    private final ProgramRepository programRepository;

    public void enrollClient(EnrollmentDTO enrollmentDTO) {
        Client client = clientRepository.findById(enrollmentDTO.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));

        for (Long programId : enrollmentDTO.getProgramIds()) {
            Program program = programRepository.findById(programId)
                    .orElseThrow(() -> new RuntimeException("Program not found"));
            Enrollment enrollment = new Enrollment();
            enrollment.setClientId(client.getId());
            enrollment.setProgramId(program.getId());
            enrollmentRepository.save(enrollment);
            client.getPrograms().add(program);
        }
        clientRepository.save(client);
    }
}