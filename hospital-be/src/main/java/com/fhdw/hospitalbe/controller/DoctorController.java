package com.fhdw.hospitalbe.controller;

import com.fhdw.hospitalbe.model.Doctor;
import com.fhdw.hospitalbe.repository.DoctorRepository;
import com.fhdw.hospitalbe.service.DoctorService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/doctor")
public class DoctorController {

    private final DoctorService doctorService;
    private final DoctorRepository repository;

    public DoctorController(DoctorService doctorService, DoctorRepository repository) {
        this.doctorService = doctorService;
        this.repository = repository;
    }

    @GetMapping(path = "{doctor_id}")
    @ApiResponse(responseCode = "200", description = "Found Doctor",
            content = @Content(schema = @Schema(implementation = Doctor.class)))
    public ResponseEntity<Doctor> findDoctor(@PathVariable("doctor_id") long doctor_id) {
        Doctor docDb = doctorService.findDoctor(doctor_id);
        if (docDb == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(docDb, HttpStatus.OK);
    }

    @GetMapping()
    @ApiResponse(responseCode = "200", description = "Found Doctors",
            content = @Content(schema = @Schema(implementation = List.class)))
    public ResponseEntity<List<Doctor>> findAllDoctors() {
        List<Doctor> doctors = doctorService.findAllDoctors();
        if (doctors == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    @PostMapping
    @ApiResponse(responseCode = "201", description = "Hired Doctor",
            content = @Content(schema = @Schema(implementation = Doctor.class)))
    public ResponseEntity<Doctor> hireDoctor(@RequestBody Doctor doctor) {
        Doctor docDb = doctorService.hireDoctor(doctor);
        if (docDb == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(docDb, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{doctor_id}")
    @ApiResponse(responseCode = "204", description = "Fired Doctor",
            content = @Content(schema = @Schema(implementation = Doctor.class)))
    public ResponseEntity<Void> fireDoctor(@PathVariable("doctor_id") long doctor_id) {
        doctorService.fireDoctor(doctor_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path= "{doctor_id}")
    @ApiResponse(responseCode = "200", description = "Update Doctor",
            content = @Content(schema = @Schema(implementation = Doctor.class)))
    public ResponseEntity<Doctor> updateDoctor(@RequestBody Doctor doctor) {
        Doctor docDb = doctorService.updateDoctor(doctor);
        if (docDb == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(docDb, HttpStatus.OK);
    }
}
