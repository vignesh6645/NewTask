package com.example.hospital.servieceImplements;

import com.example.hospital.dto.DiseaseDto;
import com.example.hospital.entity.Disease;
import com.example.hospital.exception.ControllerException;
import com.example.hospital.repository.DiseaseRepository;
import com.example.hospital.serviece.DiseaseInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DiseaseServieceImplements implements DiseaseInterface {

    @Autowired
    private DiseaseRepository diseaseRepository;

    @Override
    public Disease AddDiseaseInfo(DiseaseDto diseaseDTO) {
        Disease disease = new Disease();
        disease.setDiseaseId(diseaseDTO.getDiseaseId());
        disease.setDiseaseName(diseaseDTO.getDiseaseName());
        diseaseRepository.save(disease);
        return disease;
    }

    @Override
    public List<Disease> listAlldisease() {
        List<Disease> disease=  diseaseRepository.findAll();
        return disease;
    }

    @Override
    public Disease deleteById(Integer diseaseId) {
        Disease disease = new Disease();
        diseaseRepository.deleteById(diseaseId);
        return disease;
    }

    @Override
    public Optional<Disease> UpdateDiseaseById(DiseaseDto diseaseDTO) {
        Optional<Disease> existDisease = diseaseRepository.findById(diseaseDTO.getDiseaseId());
        if (existDisease.isPresent())
        {
            existDisease.get().setDiseaseName(diseaseDTO.getDiseaseName());
        }
        else  {
            throw new ControllerException("404","patient details not found");
        }
        diseaseRepository.save(existDisease.get());
        return existDisease;
    }

}
