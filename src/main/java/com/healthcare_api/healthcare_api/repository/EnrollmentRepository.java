package com.healthcare_api.healthcare_api.repository;

import com.healthcare_api.healthcare_api.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
}