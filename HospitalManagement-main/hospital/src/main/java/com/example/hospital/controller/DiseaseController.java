package com.example.hospital.controller;

import com.example.hospital.baseResponse.BaseResponse;
import com.example.hospital.dto.DiseaseDto;
import com.example.hospital.entity.Disease;
import com.example.hospital.serviece.DiseaseInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;

@RequestMapping("/disease")
@RestController
public class DiseaseController {

    @Autowired
    private DiseaseInterface diseaseInterface;

    @RolesAllowed(value = "ADMIN")
    @PostMapping("/create")
    public BaseResponse<Disease> addDiseaseInfo(@RequestBody DiseaseDto diseaseDTO) {
        BaseResponse<Disease> baseResponse=null;
        baseResponse = BaseResponse.<Disease>builder().Data(diseaseInterface.AddDiseaseInfo(diseaseDTO)).build();
        return baseResponse;

    }

    @RolesAllowed(value = "USER")
    @GetMapping("/getAll")
    public BaseResponse<List<Disease>> list(){
        BaseResponse<List<Disease>> baseResponse= null;
        baseResponse = BaseResponse.<List<Disease>>builder().Data( diseaseInterface.listAlldisease()).build();
        return baseResponse;
    }

    @RolesAllowed(value = "USER")
    @DeleteMapping("/delete/{diseaseId}")
    public String delete(@PathVariable Integer diseaseId) {
        BaseResponse<Disease> baseResponse= null;
        baseResponse = BaseResponse.<Disease>builder().Data(diseaseInterface.deleteById(diseaseId)).build();
        return "Success";
    }

    @RolesAllowed(value = "USER")
    @PutMapping("/update")
    public BaseResponse<Optional<Disease>> updateDiseaseById(@RequestBody DiseaseDto diseaseDTO){
        BaseResponse<Optional<Disease>>  baseResponse=null;
        baseResponse = BaseResponse.<Optional<Disease>> builder().Data(diseaseInterface.UpdateDiseaseById(diseaseDTO)).build();
        return baseResponse;
    }
}
