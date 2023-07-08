package com.fhdw.hospitalbe.service;

import com.fhdw.hospitalbe.model.PatientRecord;
import com.fhdw.hospitalbe.repository.PatientRecordRepository;
import org.springframework.stereotype.Service;

@Service
public class PatientRecordService {

    private final PatientRecordRepository repository;

    public PatientRecordService(PatientRecordRepository repository) {
        this.repository = repository;
    }


    public PatientRecord getPatientRecord(Long id) {
        if (id == null) return null;
        return this.repository.findById(id).orElse(null);
    }

    public PatientRecord createPatientRecord(PatientRecord patientRecord) {
        if (patientRecord == null) return null;
        return this.repository.save(patientRecord);
    }

    public void deletePatientRecord(Long id) {
        if (id != null && this.repository.existsById(id)) this.repository.deleteById(id);
    }


}
