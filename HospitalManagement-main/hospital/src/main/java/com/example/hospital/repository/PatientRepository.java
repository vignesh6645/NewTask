package com.example.hospital.repository;
import com.example.hospital.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Integer> {

    Page<Patient> searchAllByPatientNameLike(String s, Pageable paging);

    Optional<Patient> findByPatientId(Integer patientId);
}
