package com.fhdw.hospitalbe.controller;

import com.fhdw.hospitalbe.model.PatientRecord;
import com.fhdw.hospitalbe.service.PatientRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/patientRecord")

public class PatientRecordController {

    private final PatientRecordService patientRecordService;

    public PatientRecordController(PatientRecordService patientRecordService) {
        this.patientRecordService = patientRecordService;
    }

    @GetMapping(path = "{patientRecord_id}")
    public ResponseEntity<PatientRecord> getPatientRecord(@PathVariable("patientRecord_id") long patientRecord_id) {
        return new ResponseEntity<PatientRecord>(patientRecordService.getPatientRecord(patientRecord_id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PatientRecord> createPatientRecord(@RequestBody PatientRecord patientRecord) {
        return new ResponseEntity<PatientRecord>(patientRecordService.createPatientRecord(patientRecord), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{patientRecord_id}")
    public ResponseEntity<Void> deletePatientRecord(@PathVariable("patientRecord_id") long patientRecord) {
        patientRecordService.deletePatientRecord(patientRecord);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
