package com.healthcare_api.healthcare_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthcare_api.healthcare_api.dto.EnrollmentDTO;
import com.healthcare_api.healthcare_api.service.EnrollmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EnrollmentController.class)
class EnrollmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EnrollmentService enrollmentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void enrollClient_success() throws Exception {
        EnrollmentDTO enrollmentDTO = new EnrollmentDTO();
        enrollmentDTO.setClientId(1L);
        enrollmentDTO.setProgramIds(Arrays.asList(1L));

        doNothing().when(enrollmentService).enrollClient(any(EnrollmentDTO.class));

        mockMvc.perform(post("/api/enrollments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(enrollmentDTO)))
                .andExpect(status().isOk());
    }
}