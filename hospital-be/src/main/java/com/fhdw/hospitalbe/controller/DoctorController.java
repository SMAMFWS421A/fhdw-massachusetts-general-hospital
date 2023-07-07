package com.fhdw.hospitalbe.controller;

import com.fhdw.hospitalbe.service.DoctorService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }
}
