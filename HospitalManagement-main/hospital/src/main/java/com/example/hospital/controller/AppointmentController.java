package com.example.hospital.controller;

import com.example.hospital.baseResponse.BaseResponse;
import com.example.hospital.dto.AppointmentDto;
import com.example.hospital.entity.Appointment;
import com.example.hospital.entity.User;
import com.example.hospital.serviece.AppointmentInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Optional;

@RequestMapping("/appointment")
@RestController
public class AppointmentController {

    @Autowired
    private AppointmentInterface appointmentInterface;

    @RolesAllowed(value = "USER")
    @PostMapping("/create")
    public BaseResponse<Appointment> addAppointmentInfo(@RequestBody AppointmentDto appointmentDto) {
        BaseResponse<Appointment> baseResponse=null;
        baseResponse = BaseResponse.<Appointment>builder().Data(appointmentInterface.AddAppointmentInfo(appointmentDto)).build();
        return baseResponse;
    }

    @RolesAllowed(value = "USER")
    @GetMapping("/appointmentId/{appointmentId}")
    public BaseResponse<Optional<Appointment>> getAppointmentById(@PathVariable Integer appointmentId){
        BaseResponse<Optional<Appointment>> baseResponse = null;
        baseResponse = BaseResponse.<Optional<Appointment>>builder()
                .Data(appointmentInterface.GetAppointmentById(appointmentId)).build();
        return baseResponse;
    }

    @RolesAllowed(value = "USER")
    @PutMapping("/update")
    public BaseResponse<Appointment> updateInfo(@RequestBody AppointmentDto appointmentDTO){
        BaseResponse<Appointment> baseResponse=null;
        baseResponse = BaseResponse.<Appointment>builder().Data(appointmentInterface.UpdateAppointmentById(appointmentDTO)).build();
        return baseResponse;
    }
    @RolesAllowed(value = "USER")
    @DeleteMapping("/delete/{appointmentId}")
    public BaseResponse<Appointment> deleteAppointment(@PathVariable Integer appointmentId) {
        BaseResponse<Appointment> baseResponse = null;
        baseResponse=BaseResponse.<Appointment>builder().Data(appointmentInterface.deleteById(appointmentId)).build();
        return baseResponse;
    }
}
