package com.fhdw.hospitalbe.model;

import com.fhdw.hospitalbe.model.builder.AppointmentBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

    Long id;
    PatientRecord patientRecord;
    Doctor doctor;
    LocalDateTime visitingTime;
    String appeal;

    public Appointment(AppointmentBuilder builder) {
        this.id = builder.getId();
        this.patientRecord = builder.getPatientRecord();
        this.doctor = builder.getDoctor();
        this.visitingTime = builder.getVisitingTime();
        this.appeal = builder.getAppeal();
    }
}
