package com.example.hospital.repository;

import com.example.hospital.entity.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Integer> {

    Optional<Doctor> findByDoctorId(Integer doctorId);

    Page<Doctor> searchAllByDoctorNameLike(String s, Pageable paging);
}
