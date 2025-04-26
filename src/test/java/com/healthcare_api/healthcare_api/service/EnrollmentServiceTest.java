package com.healthcare_api.healthcare_api.service;

import com.healthcare_api.healthcare_api.dto.EnrollmentDTO;
import com.healthcare_api.healthcare_api.entity.Client;
import com.healthcare_api.healthcare_api.entity.Enrollment;
import com.healthcare_api.healthcare_api.entity.Program;
import com.healthcare_api.healthcare_api.repository.ClientRepository;
import com.healthcare_api.healthcare_api.repository.EnrollmentRepository;
import com.healthcare_api.healthcare_api.repository.ProgramRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EnrollmentServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ProgramRepository programRepository;

    @Mock
    private EnrollmentRepository enrollmentRepository;

    @InjectMocks
    private EnrollmentService enrollmentService;

    private EnrollmentDTO enrollmentDTO;
    private Client client;
    private Program program;

    @BeforeEach
    void setUp() {
        enrollmentDTO = new EnrollmentDTO();
        enrollmentDTO.setClientId(1L);
        enrollmentDTO.setProgramIds(Arrays.asList(1L));

        client = new Client();
        client.setId(1L);

        program = new Program();
        program.setId(1L);
    }

    @Test
    void enrollClient_success() {
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        when(programRepository.findById(1L)).thenReturn(Optional.of(program));
        when(enrollmentRepository.save(any(Enrollment.class))).thenReturn(new Enrollment());

        enrollmentService.enrollClient(enrollmentDTO);

        verify(clientRepository).findById(1L);
        verify(programRepository).findById(1L);
        verify(enrollmentRepository).save(any(Enrollment.class));
    }

    @Test
    void enrollClient_clientNotFound_throwsException() {
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> enrollmentService.enrollClient(enrollmentDTO));
        verify(clientRepository).findById(1L);
        verify(programRepository, never()).findById(anyLong());
        verify(enrollmentRepository, never()).save(any(Enrollment.class));
    }

    @Test
    void enrollClient_programNotFound_throwsException() {
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        when(programRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> enrollmentService.enrollClient(enrollmentDTO));
        verify(clientRepository).findById(1L);
        verify(programRepository).findById(1L);
        verify(enrollmentRepository, never()).save(any(Enrollment.class));
    }
}