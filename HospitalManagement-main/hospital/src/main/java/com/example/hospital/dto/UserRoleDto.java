package com.example.hospital.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRoleDto {

    private Integer id;

    private String userName;

    private String jwtToken;

    private String password;
}
