package com.example.hospital.controller;

import com.example.hospital.baseResponse.BaseResponse;
import com.example.hospital.baseResponse.PageResponse;
import com.example.hospital.dto.DoctorDto;
import com.example.hospital.entity.Doctor;
import com.example.hospital.serviece.DoctorInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Optional;

@RequestMapping("/doctor")
@RestController
public class DoctorController {

    @Autowired
    private DoctorInterface doctorInterface;

    @RolesAllowed(value = "USER")
    @PostMapping("/create")
    public BaseResponse<Doctor> AddDoctorInfo(@RequestBody DoctorDto doctorDTO) {
        BaseResponse<Doctor> baseResponse=null;
        baseResponse = BaseResponse.<Doctor>builder().Data(doctorInterface.AddDoctorInfo(doctorDTO)).build();
        return baseResponse;
    }

    @RolesAllowed(value = "USER")
    @GetMapping("/getById/{doctorId}")
    public BaseResponse<Optional<Doctor>> getDoctorById(@PathVariable Integer doctorId){
        BaseResponse<Optional<Doctor>> baseResponse=null;
        baseResponse = BaseResponse.<Optional<Doctor>>builder().Data(doctorInterface.GetDoctorById(doctorId)).build();
        return baseResponse;

    }

    @RolesAllowed(value = "USER")
    @PutMapping("/update")
    public BaseResponse<Doctor> updated (@RequestBody DoctorDto doctorDto){
        BaseResponse<Doctor> baseResponse=null;
        baseResponse = BaseResponse.<Doctor>builder().Data(doctorInterface.UpdateDoctor(doctorDto)).build();
        return baseResponse;
    }

    @RolesAllowed(value="USER")
    @GetMapping("/pagination/{offset}/{pageSize}/{doctorName}")
    public PageResponse<Doctor> getDoctorWithPagination(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String doctorName){
        return doctorInterface.GetDoctorWithPagination(offset, pageSize, doctorName);
    }

    @RolesAllowed(value = "USER")
    @DeleteMapping("/delete/{doctorId}")
    public String delete(@PathVariable Integer doctorId) {
        doctorInterface.deleteById(doctorId);
        return "Success";
    }
}

