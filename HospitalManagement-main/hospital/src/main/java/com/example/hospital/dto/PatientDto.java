package com.example.hospital.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PatientDto {

    private Integer patientId;

    private String patientName;

    private List<UserDto> userId;
}
