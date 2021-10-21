package com.example.hospital.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DoctorDto {

    private Integer doctorId;

    private String doctorName;

    private List<UserDto> userId;
}
