package com.healthcare_api.healthcare_api.service;

import com.healthcare_api.healthcare_api.dto.ProgramDTO;
import com.healthcare_api.healthcare_api.entity.Program;
import com.healthcare_api.healthcare_api.repository.ProgramRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProgramService {
    private static final Logger logger = LoggerFactory.getLogger(ProgramService.class);
    private final ProgramRepository programRepository;

    public ProgramDTO createProgram(ProgramDTO programDTO) {
        logger.info("Creating program: {}", programDTO.getName());
        if (programDTO.getName() == null || programDTO.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Program name is required");
        }
        Program program = new Program();
        program.setName(programDTO.getName());
        program.setDescription(programDTO.getDescription());
        program = programRepository.save(program);
        logger.info("Program created with ID: {}", program.getId());

        ProgramDTO result = new ProgramDTO();
        result.setId(program.getId());
        result.setName(program.getName());
        result.setDescription(program.getDescription());
        return result;
    }

    public List<ProgramDTO> getAllPrograms() {
        return programRepository.findAll().stream().map(program -> {
            ProgramDTO dto = new ProgramDTO();
            dto.setId(program.getId());
            dto.setName(program.getName());
            dto.setDescription(program.getDescription());
            return dto;
        }).collect(Collectors.toList());
    }
}