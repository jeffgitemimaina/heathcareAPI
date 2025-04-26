package com.healthcare_api.healthcare_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthcare_api.healthcare_api.dto.ProgramDTO;
import com.healthcare_api.healthcare_api.service.ProgramService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProgramController.class)
class ProgramControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProgramService programService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createProgram_success() throws Exception {
        ProgramDTO programDTO = new ProgramDTO();
        programDTO.setName("TB");
        programDTO.setDescription("Tuberculosis treatment program");
        programDTO.setId(1L);

        when(programService.createProgram(any(ProgramDTO.class))).thenReturn(programDTO);

        mockMvc.perform(post("/api/programs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(programDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("TB"));
    }

    @Test
    void getAllPrograms_success() throws Exception {
        ProgramDTO programDTO = new ProgramDTO();
        programDTO.setId(1L);
        programDTO.setName("TB");

        when(programService.getAllPrograms()).thenReturn(Arrays.asList(programDTO));

        mockMvc.perform(get("/api/programs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("TB"));
    }
}