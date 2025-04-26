package com.healthcare_api.healthcare_api.repository;


import com.healthcare_api.healthcare_api.entity.Program;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramRepository extends JpaRepository<Program, Long> {
}