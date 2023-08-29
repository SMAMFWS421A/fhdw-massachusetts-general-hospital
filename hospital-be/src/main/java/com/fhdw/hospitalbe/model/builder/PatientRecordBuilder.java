package com.fhdw.hospitalbe.model.builder;

import com.fhdw.hospitalbe.model.Patient;
import com.fhdw.hospitalbe.model.PatientRecord;
import lombok.Getter;

@Getter
public class PatientRecordBuilder {
    Long id;
    String medication;
    String diseases;
    Patient patient;

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

    public PatientRecord build() {
        return new PatientRecord(this);
    }
}
