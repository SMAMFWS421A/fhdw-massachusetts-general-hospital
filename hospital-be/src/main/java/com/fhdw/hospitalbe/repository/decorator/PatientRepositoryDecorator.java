package com.fhdw.hospitalbe.repository.decorator;

import com.fhdw.hospitalbe.model.Patient;
import com.fhdw.hospitalbe.model.mapper.PatientMapper;
import com.fhdw.hospitalbe.repository.PatientRepository;
import com.fhdw.hospitalbe.repository.table.PatientTable;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientRepositoryDecorator {

    private final PatientRepository repository;

    public PatientRepositoryDecorator(PatientRepository repository) {
        this.repository = repository;
    }

    public List<Patient> findAllPatients() {
        List<PatientTable> patientTableList = this.repository.findAll();
        return patientTableList.stream().map(PatientMapper::fromTable).toList();
    }

    public Patient findPatient(Long id) {
        if (id == null) {
            return null;
        }
        PatientTable patientTable = this.repository.findById(id).orElse(null);
        if (patientTable == null) return null;
        return PatientMapper.fromTable(patientTable);
    }

    public Patient savePatient(Patient patient) {
        if (patient == null) {
            return null;
        }
        PatientTable patientTable = this.repository.save(PatientMapper.toTable(patient));
        if (patientTable.getPatientRecord() != null) {
            patientTable.getPatientRecord().setPatient(patientTable);
        }
        return PatientMapper.fromTable(patientTable);
    }

    @Transactional
    public void deletePatient(Long id) {
        if (id != null && this.repository.existsById(id)){
            this.repository.deleteById(id);
        }

    }

    public Patient updatePatient(Patient patient) {
        if (patient == null) {
            return null;
        }
        PatientTable patientTable = this.repository.save(PatientMapper.toTable(patient));
        if (patientTable.getPatientRecord() != null) {
            patientTable.getPatientRecord().setPatient(patientTable);
        }
        return PatientMapper.fromTable(patientTable);
    }
}
