package com.example.hospital.repository;


import com.example.hospital.entity.Disease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface DiseaseRepository extends JpaRepository<Disease,Integer> {


    Optional<Disease> findByDiseaseId(Integer diseaseId);
}
