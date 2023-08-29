package com.fhdw.hospitalbe.controller;

import com.fhdw.hospitalbe.model.Doctor;
import com.fhdw.hospitalbe.repository.DoctorRepository;
import com.fhdw.hospitalbe.repository.decorator.DoctorRepositoryDecorator;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/doctor")
@CrossOrigin("http://localhost:8080")

public class DoctorController {

    private final DoctorRepositoryDecorator doctorRepositoryDecorator;
    private final DoctorRepository repository;

    public DoctorController(DoctorRepositoryDecorator doctorRepositoryDecorator, DoctorRepository repository) {
        this.doctorRepositoryDecorator = doctorRepositoryDecorator;
        this.repository = repository;
    }

    @GetMapping(path = "{doctor_id}")
    @ApiResponse(responseCode = "200", description = "Found Doctor",
            content = @Content(schema = @Schema(implementation = Doctor.class)))
    public ResponseEntity<Doctor> findDoctor(@PathVariable("doctor_id") long doctor_id) {
        Doctor docDb = doctorRepositoryDecorator.findDoctor(doctor_id);
        if (docDb == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(docDb, HttpStatus.OK);
    }

    @GetMapping()
    @ApiResponse(responseCode = "200", description = "Found Doctors",
            content = @Content(schema = @Schema(implementation = List.class)))
    public ResponseEntity<List<Doctor>> findAllDoctors() {
        List<Doctor> doctors = doctorRepositoryDecorator.findAllDoctors();
        if (doctors == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    @PostMapping
    @ApiResponse(responseCode = "201", description = "Hired Doctor",
            content = @Content(schema = @Schema(implementation = Doctor.class)))
    public ResponseEntity<Doctor> hireDoctor(@RequestBody Doctor doctor) {
        Doctor docDb = doctorRepositoryDecorator.saveDoctor(doctor);
        if (docDb == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(docDb, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{doctor_id}")
    @ApiResponse(responseCode = "204", description = "Fired Doctor",
            content = @Content(schema = @Schema(implementation = Doctor.class)))
    public ResponseEntity<Void> fireDoctor(@PathVariable("doctor_id") long doctor_id) {
        doctorRepositoryDecorator.deleteDoctor(doctor_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping()
    @ApiResponse(responseCode = "200", description = "Updated Doctor",
            content = @Content(schema = @Schema(implementation = Doctor.class)))
    public ResponseEntity<Doctor> updateDoctor(@RequestBody Doctor doctor) {
        Doctor docDb = doctorRepositoryDecorator.updateDoctor(doctor);
        if (docDb == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(docDb, HttpStatus.OK);
    }
}
