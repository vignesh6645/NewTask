package com.example.hospital.dto;

import com.example.hospital.entity.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class UserDto {

    private Integer Id;

    private String userName;

    private String password;

    private String roleName;

    private List<Role> roles;
}
