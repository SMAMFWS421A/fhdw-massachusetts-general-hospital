package com.fhdw.hospitalbe.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fhdw.hospitalbe.model.builder.PatientRecordBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientRecord {
    Long id;
    String medication;
    String diseases;
    //--------------------------------------
    @JsonBackReference //TODO remove
    Patient patient;

    public PatientRecord(PatientRecordBuilder builder) {
        this.id = builder.getId();
        this.medication = builder.getMedication();
        this.diseases = builder.getDiseases();
        this.patient = builder.getPatient();
    }

}
