package com.example.hospital.controller;


import com.example.hospital.baseResponse.BaseResponse;
import com.example.hospital.baseResponse.PageResponse;
import com.example.hospital.dto.PatientDto;
import com.example.hospital.entity.Patient;
import com.example.hospital.serviece.PatientInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Optional;

@RequestMapping("/patient")
@RestController
public class PatientController {

    @Autowired
    private PatientInterface patientInterface;

    @RolesAllowed(value = "USER")
    @PostMapping("/create")
    public BaseResponse<Patient> addPatient(@RequestBody PatientDto patientDTO) {
        BaseResponse<Patient> baseResponse=null;
        baseResponse = BaseResponse.<Patient>builder().Data(patientInterface.AddPatientInfo(patientDTO)).build();
        return baseResponse;

    }

    @RolesAllowed(value = "USER")
    @GetMapping("/patient/{patientId}")
    public BaseResponse<Patient> getPatientById(@PathVariable Integer patientId){
        BaseResponse<Patient> baseResponse=null;
        baseResponse = BaseResponse.<Patient>builder().Data(patientInterface.GetPatientById(patientId)).build();
        return baseResponse;

    }

    @RolesAllowed(value = "USER")
    @DeleteMapping("/delete/{patientId}")
    public BaseResponse<Patient>  delete(@PathVariable Integer patientId) {
        BaseResponse<Patient> baseResponse=null;
        baseResponse = BaseResponse.<Patient>builder().Data(patientInterface.deleteById(patientId)).build();
        return baseResponse;
    }

    @RolesAllowed(value = "USER")
    @PutMapping("/update")
    public BaseResponse<Optional<Patient>> update(@RequestBody PatientDto patientDTO){
        BaseResponse<Optional<Patient>> baseResponse=null;
        baseResponse = BaseResponse.<Optional<Patient>>builder().Data(patientInterface.UpdatePatientById(patientDTO)).build();
        return baseResponse;
    }

    @RolesAllowed(value = "USER")
    @GetMapping("/pagination/{offset}/{pageSize}/{patientName}")
    private PageResponse<Patient> getPatientWithPagination(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String patientName){
        return patientInterface.GetPatientWithPagination(offset, pageSize, patientName);
    }
}
