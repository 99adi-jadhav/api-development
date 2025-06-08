package com.example.project.Hospital_management.repositories;

import com.example.project.Hospital_management.Entities.PatientEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {
	// Optional<PatientEntity> findByName(String name);
	}
