package com.example.hospital.serviece;

import com.example.hospital.dto.DiseaseDto;
import com.example.hospital.entity.Disease;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DiseaseInterface {
    Disease AddDiseaseInfo(DiseaseDto diseaseDTO);

    List<Disease> listAlldisease();

    Optional<Disease> UpdateDiseaseById(DiseaseDto diseaseDTO);

    Disease deleteById(Integer diseaseId);
}
