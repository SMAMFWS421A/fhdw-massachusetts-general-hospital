package com.fhdw.hospitalbe.model.builder;

import com.fhdw.hospitalbe.model.Appointment;
import com.fhdw.hospitalbe.model.Doctor;
import com.fhdw.hospitalbe.model.PatientRecord;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AppointmentBuilder {

    Long id;
    PatientRecord patientRecord;
    Doctor doctor;
    LocalDateTime visitingTime;
    String appeal;

    public AppointmentBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public AppointmentBuilder patientRecord(PatientRecord patientRecord) {
        this.patientRecord = patientRecord;
        return this;
    }

    public AppointmentBuilder doctor(Doctor doctor) {
        this.doctor = doctor;
        return this;
    }

    public AppointmentBuilder visitingTime(LocalDateTime visitingTime) {
        this.visitingTime = visitingTime;
        return this;
    }

    public AppointmentBuilder appeal(String appeal) {
        this.appeal = appeal;
        return this;
    }

    public Appointment build() {
        return new Appointment(this);
    }

}
