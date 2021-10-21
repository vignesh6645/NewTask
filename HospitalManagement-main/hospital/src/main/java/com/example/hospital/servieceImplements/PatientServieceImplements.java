package com.example.hospital.servieceImplements;

import com.example.hospital.baseResponse.PageResponse;
import com.example.hospital.dto.PatientDto;
import com.example.hospital.entity.Patient;
import com.example.hospital.entity.User;
import com.example.hospital.exception.ControllerException;
import com.example.hospital.repository.PatientRepository;
import com.example.hospital.repository.UserRepository;
import com.example.hospital.serviece.PatientInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class PatientServieceImplements implements PatientInterface {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PatientRepository patientRepository;

    @Override
    public Patient AddPatientInfo(PatientDto patientDTO) {
        Patient patient = new Patient();
        patient.setPatientName(patientDTO.getPatientName());
        Patient finalPatient = patient;
        patientDTO.getUserId().forEach(userDTO -> {
            User users=userRepository.findById(userDTO.getId()).orElse(null);
           /* if(users.isPresent())
            {*/
                finalPatient.setUsers(users);
            /*}else  {
                throw new ControllerException("404","patient details not found");
            }*/

        });
        patient.setPatientId(patientDTO.getPatientId());
        patient.setPatientName(patientDTO.getPatientName());
        patientRepository.save(patient);

        return patient;
    }

    @Override
    public Patient GetPatientById(Integer id) {
        Patient patient=patientRepository.findById(id).orElse(null);
        return patient;

    }

    @Override
    public Patient deleteById(Integer patientId){
        Patient patient = new Patient();
         patientRepository.deleteById(patientId);
        return patient;
    }

    @Override
    public Optional<Patient> UpdatePatientById(PatientDto patientDTO) {
        Optional<Patient> exitsPatient = patientRepository.findByPatientId(patientDTO.getPatientId());
        if (exitsPatient.isPresent())
        {
            exitsPatient.get().setPatientName(patientDTO.getPatientName());
        }else  {
            throw new ControllerException("404","patient details not found");
        }

            patientDTO.getUserId().forEach(userDTO -> {
            Optional<User> users=userRepository.findById(userDTO.getId());
            if (users.isPresent())
            {
                exitsPatient.get().setUsers(users.get());
            }
            else  {
                throw new ControllerException("404","patient details not found");
            }

        });
        patientRepository.save(exitsPatient.get());
        return exitsPatient;
    }
    @Override
    public PageResponse<Patient> GetPatientWithPagination(int offset, int pageSize, String patientName) {
        PageResponse pageResponse=new PageResponse();
        try {
            Pageable paging= PageRequest.of(offset,pageSize);
            Page<Patient> patients = patientRepository.searchAllByPatientNameLike("%" + patientName + "%", paging);
            pageResponse.setResponse(patients);
            pageResponse.setRecordCount(patients.getTotalPages());
        } catch (
                NoSuchElementException e) {
            throw new ControllerException("404","patient details not found");
        }
        return pageResponse;
    }
}
