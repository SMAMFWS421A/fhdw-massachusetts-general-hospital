package com.fhdw.hospitalbe.controller;

import com.fhdw.hospitalbe.model.PatientRecord;
import com.fhdw.hospitalbe.service.PatientRecordService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @ApiResponse(responseCode = "200", description = "Found Patient Record",
            content = @Content(schema = @Schema(implementation = PatientRecord.class)))
    public ResponseEntity<PatientRecord> findPatientRecord(@PathVariable("patientRecord_id") long patientRecord_id) {
        PatientRecord patRecDb = patientRecordService.findPatientRecord(patientRecord_id);
        if (patRecDb == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<PatientRecord>(patRecDb, HttpStatus.OK);
    }

    @PostMapping
    @ApiResponse(responseCode = "201", description = "Created Patient Record",
            content = @Content(schema = @Schema(implementation = PatientRecord.class)))
    public ResponseEntity<PatientRecord> createPatientRecord(@RequestBody PatientRecord patientRecord) {
        PatientRecord patRecDb = patientRecordService.createPatientRecord(patientRecord);
        if (patRecDb == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<PatientRecord>(patRecDb, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{patientRecord_id}")
    @ApiResponse(responseCode = "204", description = "Deleted Patient Record",
            content = @Content(schema = @Schema(implementation = PatientRecord.class)))
    public ResponseEntity<Void> deletePatientRecord(@PathVariable("patientRecord_id") long patientRecord) {
        patientRecordService.deletePatientRecord(patientRecord);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path= "{patientRecord_id}")
    @ApiResponse(responseCode = "200", description = "Update Patient Record",
            content = @Content(schema = @Schema(implementation = PatientRecord.class)))
    public ResponseEntity<PatientRecord> updatePatientRecord(@RequestBody PatientRecord patientRecord) {
        PatientRecord patRecDb = patientRecordService.updatePatientRecord(patientRecord);
        if (patRecDb == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<PatientRecord>(patRecDb, HttpStatus.OK);
    }
}
