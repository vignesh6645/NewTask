package com.example.hospital.servieceImplements;

import com.example.hospital.baseResponse.PageResponse;
import com.example.hospital.dto.DoctorDto;
import com.example.hospital.entity.Doctor;
import com.example.hospital.entity.User;
import com.example.hospital.exception.ControllerException;
import com.example.hospital.repository.*;
import com.example.hospital.serviece.DoctorInterface;
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
public class DoctorServieceImplements implements DoctorInterface {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DiseaseRepository diseaseRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Doctor AddDoctorInfo(DoctorDto doctorDTO) {
        Doctor doctor = new Doctor();
        doctor.setDoctorName(doctorDTO.getDoctorName());
        doctorDTO.getUserId().forEach(userDTO -> {
            Optional<User> users=userRepository.findById(userDTO.getId());
            if (users.isPresent())
            {
                doctor.setUsers(users.get());
                doctor.setDoctorId(doctorDTO.getDoctorId());
                doctor.setDoctorName(doctorDTO.getDoctorName());
            }
            else  {
                throw new ControllerException("404","patient details not found");
            }
        });
        doctorRepository.save(doctor);
        return doctor;
    }

    @Override
    public Optional<Doctor> GetDoctorById(Integer doctorId) {
        Optional<Doctor> doctor=doctorRepository.findById(doctorId);

        return doctor;
    }

    @Override
    public Doctor deleteById(Integer doctorId){
        Doctor doctor = new Doctor();
        doctorRepository.deleteById(doctorId);
        return doctor;
    }

    @Override
    public Doctor UpdateDoctor(DoctorDto doctorDto) {
        Doctor exitsDoctor = doctorRepository.findById(doctorDto.getDoctorId()).orElse(null);
        exitsDoctor.setDoctorName(doctorDto.getDoctorName());
        doctorDto.getUserId().forEach(userDTO -> {
            User users=userRepository.findById(userDTO.getId()).orElse(null);
            exitsDoctor.setUsers(users);

        });
        doctorRepository.save(exitsDoctor);
        return exitsDoctor;
    }



    @Override
    public PageResponse<Doctor> GetDoctorWithPagination(int offset, int pageSize, String doctorName) {
        PageResponse pageResponse=new PageResponse();
        try {
            Pageable paging= PageRequest.of(offset,pageSize);
            Page<Doctor> doctors = doctorRepository.searchAllByDoctorNameLike("%" + doctorName + "%", paging);
            pageResponse.setResponse(doctors);
            pageResponse.setRecordCount(doctors.getTotalPages());
        } catch (
                NoSuchElementException e) {
            throw new ControllerException("404","patient details not found");
        }
        return pageResponse;
    }
}
