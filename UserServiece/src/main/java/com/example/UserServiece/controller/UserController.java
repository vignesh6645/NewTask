package com.example.UserServiece.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import reactor.core.scheduler.Scheduler;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@EnableDiscoveryClient
@RestController
public class UserController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;



    @GetMapping("/getAll")
    public String invokeWorkerDetails(){

        URI uri = discoveryClient.getInstances("hospitalManagement").stream()
                .map(serviceInstance -> serviceInstance.getUri()).findFirst()
                .map(s -> s.resolve("/user/userid/{id}")).get();
        return restTemplate.getForObject(uri,String.class);
    }

    @GetMapping("/getAll")
    public String getMsg(){

        URI uri = discoveryClient.getInstances("hospitalManagement").stream()
                .map(serviceInstance -> serviceInstance.getUri()).findFirst()
                .map(s -> s.resolve("/patient/patient/{patientId}")).get();
        return restTemplate.getForObject(uri,String.class);
    }
    @GetMapping("/getAll")
    public String getDoctor(){

        URI uri = discoveryClient.getInstances("hospitalManagement").stream()
                .map(serviceInstance -> serviceInstance.getUri()).findFirst()
                .map(s -> s.resolve("/doctor/getById/{doctorId}")).get();
        return restTemplate.getForObject(uri,String.class);
    }
    @GetMapping("/getAll")
    public String getAppointment(){

        URI uri = discoveryClient.getInstances("hospitalManagement").stream()
                .map(serviceInstance -> serviceInstance.getUri()).findFirst()
                .map(s -> s.resolve("/appointment/appointmentId/{appointmentId}")).get();
        return restTemplate.getForObject(uri,String.class);
    }

    @GetMapping("/getAll")
    public String getDisease(){

        URI uri = discoveryClient.getInstances("hospitalManagement").stream()
                .map(serviceInstance -> serviceInstance.getUri()).findFirst()
                .map(s -> s.resolve("/disease/getAll")).get();
        return restTemplate.getForObject(uri,String.class);
    }
}
