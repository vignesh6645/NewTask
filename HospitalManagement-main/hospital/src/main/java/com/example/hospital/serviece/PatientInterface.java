package com.example.hospital.serviece;

import com.example.hospital.baseResponse.PageResponse;
import com.example.hospital.dto.PatientDto;
import com.example.hospital.entity.Patient;

import java.util.Optional;

public interface PatientInterface {
    Patient AddPatientInfo(PatientDto patientDTO);

    Patient GetPatientById(Integer patientId);

    Optional<Patient> UpdatePatientById(PatientDto patientDTO);

    PageResponse<Patient> GetPatientWithPagination(int offset, int pageSize, String patientName);

    Patient deleteById(Integer patientId);
}
