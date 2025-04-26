package com.healthcare_api.healthcare_api.service;

import com.healthcare_api.healthcare_api.dto.ProgramDTO;
import com.healthcare_api.healthcare_api.entity.Program;
import com.healthcare_api.healthcare_api.repository.ProgramRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProgramServiceTest {

    @Mock
    private ProgramRepository programRepository;

    @InjectMocks
    private ProgramService programService;

    private ProgramDTO programDTO;
    private Program program;

    @BeforeEach
    void setUp() {
        programDTO = new ProgramDTO();
        programDTO.setName("TB");
        programDTO.setDescription("Tuberculosis treatment program");

        program = new Program();
        program.setId(1L);
        program.setName("TB");
        program.setDescription("Tuberculosis treatment program");
    }

    @Test
    void createProgram_success() {
        when(programRepository.save(any(Program.class))).thenReturn(program);

        ProgramDTO result = programService.createProgram(programDTO);

        assertNotNull(result);
        assertEquals("TB", result.getName());
        assertEquals(1L, result.getId());
        verify(programRepository).save(any(Program.class));
    }

    @Test
    void createProgram_nullName_throwsException() {
        programDTO.setName(null);

        assertThrows(IllegalArgumentException.class, () -> programService.createProgram(programDTO));
        verify(programRepository, never()).save(any(Program.class));
    }

    @Test
    void getAllPrograms_success() {
        when(programRepository.findAll()).thenReturn(Arrays.asList(program));

        List<ProgramDTO> result = programService.getAllPrograms();

        assertEquals(1, result.size());
        assertEquals("TB", result.get(0).getName());
        verify(programRepository).findAll();
    }
}