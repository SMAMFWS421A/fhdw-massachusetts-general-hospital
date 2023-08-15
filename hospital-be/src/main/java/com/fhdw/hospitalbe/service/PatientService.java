package com.fhdw.hospitalbe.service;

import com.fhdw.hospitalbe.model.Doctor;
import com.fhdw.hospitalbe.model.Patient;
import com.fhdw.hospitalbe.repository.PatientRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    private final PatientRepository repository;

    public PatientService(PatientRepository repository) {
        this.repository = repository;
    }

    public List<Patient> getPatients() {
        return this.repository.findAll();
    }

    public Patient getPatient(Long id) {
        if (id == null) {
            return null;
        }
        return this.repository.findById(id).orElse(null);
    }

    public Patient createPatient(Patient patient) {
        if (patient == null) {
            return null;
        }
        if (patient.getPatientRecord() != null) {
            patient.getPatientRecord().setPatient(patient);
        }
        return this.repository.save(patient);
    }

    public void deletePatient(Long id) {
        if (id != null && this.repository.existsById(id)) this.repository.deleteById(id);
    }

    public Patient updatePatient(Patient patient) {
        if (patient == null) {
            return null;
        }
        return this.repository.save(patient);
    }
}
