package com.fhdw.hospitalbe.controller;

import com.fhdw.hospitalbe.model.Doctor;
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
    @ApiResponse(responseCode = "200", description = "Found Patient_Record",
            content = @Content(schema = @Schema(implementation = PatientRecord.class)))
    public ResponseEntity<PatientRecord> getPatientRecord(@PathVariable("patientRecord_id") long patientRecord_id) {
        return new ResponseEntity<PatientRecord>(patientRecordService.getPatientRecord(patientRecord_id), HttpStatus.OK);
    }

    @PostMapping
    @ApiResponse(responseCode = "201", description = "Created Patient_Record",
            content = @Content(schema = @Schema(implementation = PatientRecord.class)))
    public ResponseEntity<PatientRecord> createPatientRecord(@RequestBody PatientRecord patientRecord) {
        return new ResponseEntity<PatientRecord>(patientRecordService.createPatientRecord(patientRecord), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{patientRecord_id}")
    @ApiResponse(responseCode = "204", description = "Deleted Patient_Record",
            content = @Content(schema = @Schema(implementation = PatientRecord.class)))
    public ResponseEntity<Void> deletePatientRecord(@PathVariable("patientRecord_id") long patientRecord) {
        patientRecordService.deletePatientRecord(patientRecord);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path= "{patientRecord_id}")
    @ApiResponse(responseCode = "200", description = "Update Patient_Record",
            content = @Content(schema = @Schema(implementation = PatientRecord.class)))
    public ResponseEntity<PatientRecord> updatePatientRecord(@RequestBody PatientRecord patientRecord) {
        return new ResponseEntity<PatientRecord>(patientRecordService.updatePatientRecord(patientRecord), HttpStatus.OK);
    }
}
