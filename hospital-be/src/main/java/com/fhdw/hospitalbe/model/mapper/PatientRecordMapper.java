package com.fhdw.hospitalbe.model.mapper;

import com.fhdw.hospitalbe.model.PatientRecord;
import com.fhdw.hospitalbe.model.builder.PatientRecordBuilder;
import com.fhdw.hospitalbe.repository.table.PatientRecordTable;

public class PatientRecordMapper {
    public static PatientRecord fromTable(PatientRecordTable patientRecordTableTable) {
        if (patientRecordTableTable == null) return null;
        return new PatientRecordBuilder()
                .id(patientRecordTableTable.getId())
                .patient(PatientMapper.fromTable(patientRecordTableTable.getPatient()))
                .diseases(patientRecordTableTable.getDiseases())
                .medication(patientRecordTableTable.getMedication())
                .build();
    }

    public static PatientRecordTable toTable(PatientRecord patientRecord) {
        if (patientRecord == null) return null;
        return PatientRecordTable.builder()
                .id(patientRecord.getId())
                .patient(PatientMapper.toTable(patientRecord.getPatient()))
                .diseases(patientRecord.getDiseases())
                .medication(patientRecord.getMedication())
                .build();
    }
}
