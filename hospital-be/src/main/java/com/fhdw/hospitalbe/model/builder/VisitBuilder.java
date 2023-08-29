package com.fhdw.hospitalbe.model.builder;

import com.fhdw.hospitalbe.model.Doctor;
import com.fhdw.hospitalbe.model.PatientRecord;
import com.fhdw.hospitalbe.model.Visit;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class VisitBuilder {
    Long id;
    PatientRecord patientRecord;
    Doctor doctor;
    String appeal;
    LocalDateTime plannedTime;
    LocalDateTime arrivedTime;
    String anamnesis;
    String measurement;
    String diagnosis;
    String planOfActions;

    public VisitBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public VisitBuilder patientRecord(PatientRecord patientRecord) {
        this.patientRecord = patientRecord;
        return this;
    }

    public VisitBuilder doctor(Doctor doctor) {
        this.doctor = doctor;
        return this;
    }

    public VisitBuilder appeal(String appeal) {
        this.appeal = appeal;
        return this;
    }

    public VisitBuilder plannedTime(LocalDateTime plannedTime) {
        this.plannedTime = plannedTime;
        return this;
    }

    public VisitBuilder arrivedTime(LocalDateTime arrivedTime) {
        this.arrivedTime = arrivedTime;
        return this;
    }

    public VisitBuilder anamnesis(String anamnesis) {
        this.anamnesis = anamnesis;
        return this;
    }

    public VisitBuilder measurement(String measurement) {
        this.measurement = measurement;
        return this;
    }

    public VisitBuilder diagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
        return this;
    }

    public VisitBuilder planOfActions(String planOfActions) {
        this.planOfActions = planOfActions;
        return this;
    }

    public Visit build() {
        return new Visit(this);
    }
}
