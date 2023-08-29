package com.fhdw.hospitalbe.model.mapper;

import com.fhdw.hospitalbe.model.Patient;
import com.fhdw.hospitalbe.model.builder.PatientBuilder;
import com.fhdw.hospitalbe.repository.table.PatientTable;

public class PatientMapper {
    public static Patient fromTable(PatientTable patientTable) {
        if (patientTable == null) return null;
        if(patientTable.getPatientRecord()!=null)patientTable.getPatientRecord().setPatient(null);
        return new PatientBuilder()
                .id(patientTable.getId())
                .firstName(patientTable.getFirstName())
                .lastName(patientTable.getLastName())
                .gender(patientTable.getGender())
                .isPrivate(patientTable.getIsPrivate())
                .birthday(patientTable.getBirthday())
                .patientRecord(PatientRecordMapper.fromTable(patientTable.getPatientRecord()))
                .build();
    }

    public static PatientTable toTable(Patient patient) {
        if (patient == null) return null;
        if(patient.getPatientRecord()!=null)patient.getPatientRecord().setPatient(null);
        return PatientTable.builder()
                .id(patient.getId())
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .gender(patient.getGender())
                .isPrivate(patient.getIsPrivate())
                .birthday(patient.getBirthday())
                .patientRecord(PatientRecordMapper.toTable(patient.getPatientRecord()))
                .build();
    }
}
