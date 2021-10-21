package com.example.hospital.dto;


import lombok.Getter;

import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class AppointmentDto {

    private Integer appointmentId;

    private String appointmentName;

    private Date appointmentTime;

    private Integer diseaseId;

    private List<PatientDto> patientId;

    private List<DoctorDto> doctorId;
}
