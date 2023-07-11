package com.fhdw.hospitalbe.controller;

import com.fhdw.hospitalbe.model.Doctor;
import com.fhdw.hospitalbe.service.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/doctor")

public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping(path = "{doctor_id}")
    public ResponseEntity<Doctor> getDoctor(@PathVariable("doctor_id") long doctor_id) {
        return new ResponseEntity<Doctor>(doctorService.getDoctor(doctor_id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Doctor> createDoctor(Doctor doctor) {
        return new ResponseEntity<Doctor>(doctorService.createDoctor(doctor), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{doctor_id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable("doctor_id") long doctor_id) {
        doctorService.deleteDoctor(doctor_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
