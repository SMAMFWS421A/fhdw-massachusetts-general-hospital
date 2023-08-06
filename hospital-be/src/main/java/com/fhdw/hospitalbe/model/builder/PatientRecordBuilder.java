package com.fhdw.hospitalbe.model.builder;

import com.fhdw.hospitalbe.model.Appointment;
import com.fhdw.hospitalbe.model.Patient;
import com.fhdw.hospitalbe.model.PatientRecord;
import com.fhdw.hospitalbe.model.Visit;
import lombok.Getter;

import java.util.Set;

@Getter
public class PatientRecordBuilder {
    Long id;
    String medication;
    String diseases;
    Patient patient;
    Set<Appointment> appointments;
    Set<Visit> visits;

    public PatientRecordBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public PatientRecordBuilder medication(String medication) {
        this.medication = medication;
        return this;
    }

    public PatientRecordBuilder diseases(String diseases) {
        this.diseases = diseases;
        return this;
    }

    public PatientRecordBuilder patient(Patient patient) {
        this.patient = patient;
        return this;
    }

    public PatientRecordBuilder appointments(Set<Appointment> appointments) {
        this.appointments = appointments;
        return this;
    }

    public PatientRecordBuilder visits(Set<Visit> visits) {
        this.visits = visits;
        return this;
    }

    public PatientRecord build() {
        return new PatientRecord(this);
    }
}
