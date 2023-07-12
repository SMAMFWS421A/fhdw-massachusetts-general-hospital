package com.fhdw.hospitalbe.controller;

import com.fhdw.hospitalbe.model.Doctor;
import com.fhdw.hospitalbe.service.DoctorService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @ApiResponse(responseCode = "200", description = "Found Doctor",
            content = @Content(schema = @Schema(implementation = Doctor.class)))
    public ResponseEntity<Doctor> getDoctor(@PathVariable("doctor_id") long doctor_id) {
        return new ResponseEntity<Doctor>(doctorService.getDoctor(doctor_id), HttpStatus.OK);
    }

    @PostMapping
    @ApiResponse(responseCode = "201", description = "Created Doctor",
            content = @Content(schema = @Schema(implementation = Doctor.class)))
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
        return new ResponseEntity<Doctor>(doctorService.createDoctor(doctor), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{doctor_id}")
    @ApiResponse(responseCode = "204", description = "Deleted Doctor",
            content = @Content(schema = @Schema(implementation = Doctor.class)))
    public ResponseEntity<Void> deleteDoctor(@PathVariable("doctor_id") long doctor_id) {
        doctorService.deleteDoctor(doctor_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
