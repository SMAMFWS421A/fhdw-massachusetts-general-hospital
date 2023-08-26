package com.fhdw.hospitalbe.service;

import com.fhdw.hospitalbe.model.PatientRecord;
import com.fhdw.hospitalbe.model.mapper.PatientRecordMapper;
import com.fhdw.hospitalbe.repository.PatientRecordRepository;
import com.fhdw.hospitalbe.repository.table.PatientRecordTable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientRecordService {

  private final PatientRecordRepository repository;

  public PatientRecordService(PatientRecordRepository repository) {
    this.repository = repository;
  }


  public List<PatientRecord> findAllPatientRecords() {
    List<PatientRecordTable> patientRecordList = this.repository.findAll();
    return patientRecordList.stream().map(PatientRecordMapper::fromTable).toList();
  }

  public PatientRecord findPatientRecord(Long id) {
    if (id == null) {
      return null;
    }
    PatientRecordTable patientRecordTable = this.repository.findById(id).orElse(null);
    if (patientRecordTable == null) return null;
    return PatientRecordMapper.fromTable(patientRecordTable);
  }

  public PatientRecord createPatientRecord(PatientRecord patientRecord) {
    if (patientRecord == null) {
      return null;
    }
    PatientRecordTable patientRecordTable = this.repository.save(PatientRecordMapper.toTable(patientRecord));
    return PatientRecordMapper.fromTable(patientRecordTable);
  }

  public void deletePatientRecord(Long id) {
        if (id != null && this.repository.existsById(id)) this.repository.deleteById(id);
    }

  public PatientRecord updatePatientRecord(PatientRecord patientRecord) {
    if (patientRecord == null) {
      return null;
    }
    PatientRecordTable patientRecordTable = this.repository.save(PatientRecordMapper.toTable(patientRecord));
    return PatientRecordMapper.fromTable(patientRecordTable);
  }
}
