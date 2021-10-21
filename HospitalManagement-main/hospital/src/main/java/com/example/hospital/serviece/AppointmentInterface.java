package com.example.hospital.serviece;

import com.example.hospital.dto.AppointmentDto;
import com.example.hospital.entity.Appointment;

import java.util.Optional;

public interface AppointmentInterface {

    Appointment AddAppointmentInfo(AppointmentDto appointmentDTO);



    Optional<Appointment> GetAppointmentById(Integer appointmentId);

    Appointment UpdateAppointmentById(AppointmentDto appointmentDTO);

    Appointment deleteById(Integer appointmentId);
}
