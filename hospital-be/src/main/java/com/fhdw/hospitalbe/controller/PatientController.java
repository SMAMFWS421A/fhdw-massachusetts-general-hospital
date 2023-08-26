package com.fhdw.hospitalbe.controller;

import com.fhdw.hospitalbe.model.Patient;
import com.fhdw.hospitalbe.service.PatientService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/patient")

public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping(path = "{patient_id}")
    @ApiResponse(responseCode = "200", description = "Found Patient",
            content = @Content(schema = @Schema(implementation = Patient.class)))
    public ResponseEntity<Patient> findPatient(@PathVariable("patient_id") long patient_id) {
        Patient patDb = patientService.findPatient(patient_id);
        if (patDb == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(patDb, HttpStatus.OK);
    }

    @GetMapping()
    @ApiResponse(responseCode = "200", description = "Found Patients",
            content = @Content(schema = @Schema(implementation = List.class)))
    public ResponseEntity<List<Patient>> findAllPatients() {
        List<Patient> patients = patientService.findAllPatients();
        if (patients == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @PostMapping
    @ApiResponse(responseCode = "201", description = "Received Patient",
            content = @Content(schema = @Schema(implementation = Patient.class)))
    public ResponseEntity<Patient> receivePatient(@RequestBody Patient patient) {
        Patient patDb = patientService.receivePatient(patient);
        if (patDb == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(patDb, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{patient_id}")
    @ApiResponse(responseCode = "204", description = "Deleted Patient",
            content = @Content(schema = @Schema(implementation = Patient.class)))
    public ResponseEntity<Void> deletePatient(@PathVariable("patient_id") long patient_id) {
        patientService.deletePatient(patient_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "{patient_id}")
    @ApiResponse(responseCode = "200", description = "Updated Patient",
            content = @Content(schema = @Schema(implementation = Patient.class)))
    public ResponseEntity<Patient> updateDoctor(@RequestBody Patient patient) {
        Patient patDb = patientService.updatePatient(patient);
        if (patDb == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(patDb, HttpStatus.OK);
    }
}
