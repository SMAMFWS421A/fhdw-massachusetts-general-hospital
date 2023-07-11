package com.fhdw.hospitalbe.controller;

import com.fhdw.hospitalbe.model.Patient;
import com.fhdw.hospitalbe.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/patient")

public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping(path = "{patient_id}")
    public ResponseEntity<Patient> getPatient(@PathVariable("patient_id") long patient_id) {
        return new ResponseEntity<Patient>(patientService.getPatient(patient_id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(Patient patient) {
        return new ResponseEntity<Patient>(patientService.createPatient(patient), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{patient_id}")
    public ResponseEntity<Void> deletePatient(@PathVariable("patient_id") long patient_id) {
        patientService.deletePatient(patient_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
