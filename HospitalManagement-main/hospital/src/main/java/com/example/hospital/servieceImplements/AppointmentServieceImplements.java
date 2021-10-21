package com.example.hospital.servieceImplements;

import com.example.hospital.dto.AppointmentDto;
import com.example.hospital.entity.Appointment;
import com.example.hospital.entity.Disease;
import com.example.hospital.entity.Doctor;
import com.example.hospital.entity.Patient;
import com.example.hospital.repository.AppointmentRepository;
import com.example.hospital.repository.DiseaseRepository;
import com.example.hospital.repository.DoctorRepository;
import com.example.hospital.repository.PatientRepository;
import com.example.hospital.serviece.AppointmentInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class AppointmentServieceImplements implements AppointmentInterface {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DiseaseRepository diseaseRepository;

    @Override
    public Appointment AddAppointmentInfo(AppointmentDto appointmentDTO) {
      Appointment appointment= new Appointment();
      appointment.setAppointmentName(appointmentDTO.getAppointmentName());
      appointment.setAppointmentTime(appointmentDTO.getAppointmentTime());

        Disease   disease=diseaseRepository.findByDiseaseId(appointmentDTO.getDiseaseId()).orElse(null);
        appointment.setDiseaseId(disease);

     Appointment appointment1= appointment;
     appointmentDTO.getPatientId().forEach(patientDto -> {
         Patient patient = patientRepository.findByPatientId(patientDto.getPatientId()).orElse(null);
         appointment1.setPatientId(patient);

     });

        Appointment appointment2= appointment;
        appointmentDTO.getDoctorId().forEach(doctorDto -> {
            Doctor doctor = doctorRepository.findByDoctorId(doctorDto.getDoctorId()).orElse(null);
            appointment2.setDoctorId(doctor);
        });

        appointmentRepository.save(appointment);

     return appointment;
    }


    @Override
    public Optional<Appointment> GetAppointmentById(Integer appointmentId) {
        Optional<Appointment> appointment=appointmentRepository.findById(appointmentId);
        return appointment;
    }

    @Override
    public  Appointment deleteById(Integer appointmentId){
        Appointment appointment = new Appointment();
         appointmentRepository.deleteById(appointmentId);
        return appointment;
    }

   @Override
    public Appointment UpdateAppointmentById(AppointmentDto appointmentDTO) {
       Appointment existAppointment= appointmentRepository.findByAppointmentId(appointmentDTO.getAppointmentId());
       existAppointment.setAppointmentName(appointmentDTO.getAppointmentName());
       existAppointment.setAppointmentTime(appointmentDTO.getAppointmentTime());

       Disease disease =diseaseRepository.findByDiseaseId(appointmentDTO.getDiseaseId()).orElse(null);
       existAppointment.setDiseaseId(disease);

       appointmentDTO.getDoctorId().forEach(doctorDto -> {
           Doctor doctor= doctorRepository.findByDoctorId(doctorDto.getDoctorId()).orElse(null);
           existAppointment.setDoctorId(doctor);
       });
       appointmentDTO.getPatientId().forEach(patientDto -> {
           Patient patient=patientRepository.findByPatientId(patientDto.getPatientId()).orElse(null);
           existAppointment.setPatientId(patient);
       });
       appointmentRepository.save(existAppointment);
       return existAppointment;
    }

}
