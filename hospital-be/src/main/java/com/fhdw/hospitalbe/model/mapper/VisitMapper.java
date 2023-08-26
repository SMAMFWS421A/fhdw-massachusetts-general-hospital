package com.fhdw.hospitalbe.model.mapper;

import com.fhdw.hospitalbe.model.Visit;
import com.fhdw.hospitalbe.model.builder.VisitBuilder;
import com.fhdw.hospitalbe.repository.table.VisitTable;

public class VisitMapper {
    public static Visit fromTable(VisitTable visitTable) {
        if (visitTable == null) return null;
        return new VisitBuilder()
                .id(visitTable.getId())
                .diagnosis(visitTable.getDiagnosis())
                .arrivedTime(visitTable.getArrivedTime())
                .appeal(visitTable.getAppeal())
                .patientRecord(PatientRecordMapper.fromTable(visitTable.getPatientRecord()))
                .anamnesis(visitTable.getAnamnesis())
                .doctor(DoctorMapper.fromTable(visitTable.getDoctor()))
                .plannedTime(visitTable.getPlannedTime())
                .measurement(visitTable.getMeasurement())
                .planOfActions(visitTable.getPlanOfActions())
                .build();
    }

    public static VisitTable toTable(Visit visit) {
        if (visit == null) return null;
        return VisitTable.builder()
                .id(visit.getId())
                .diagnosis(visit.getDiagnosis())
                .arrivedTime(visit.getArrivedTime())
                .appeal(visit.getAppeal())
                .patientRecord(PatientRecordMapper.toTable(visit.getPatientRecord()))
                .anamnesis(visit.getAnamnesis())
                .doctor(DoctorMapper.toTable(visit.getDoctor()))
                .plannedTime(visit.getPlannedTime())
                .measurement(visit.getMeasurement())
                .planOfActions(visit.getPlanOfActions())
                .build();
    }
}
