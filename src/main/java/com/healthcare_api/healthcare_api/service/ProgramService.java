package com.healthcare_api.healthcare_api.service;


import com.healthcare_api.healthcare_api.dto.ProgramDTO;
import com.healthcare_api.healthcare_api.entity.Program;
import com.healthcare_api.healthcare_api.repository.ProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProgramService {
    private final ProgramRepository programRepository;

    public ProgramDTO createProgram(ProgramDTO programDTO) {
        Program program = new Program();
        program.setName(programDTO.getName());
        program.setDescription(programDTO.getDescription());
        program = programRepository.save(program);
        programDTO.setId(program.getId());
        return programDTO;
    }

    public List<ProgramDTO> getAllPrograms() {
        return programRepository.findAll().stream()
                .map(program -> {
                    ProgramDTO dto = new ProgramDTO();
                    dto.setId(program.getId());
                    dto.setName(program.getName());
                    dto.setDescription(program.getDescription());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}