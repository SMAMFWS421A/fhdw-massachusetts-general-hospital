package com.fhdw.hospitalbe.model;

import com.fhdw.hospitalbe.model.builder.AppointmentBuilder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id",nullable = false)
    PatientRecord patientRecord;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    Doctor doctor;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "visiting_time")
    LocalDateTime visitingTime;

    @Column(name = "appeal")
    String appeal;

    public Appointment(AppointmentBuilder builder) {
        this.id = builder.getId();
        this.patientRecord = builder.getPatientRecord();
        this.doctor = builder.getDoctor();
        this.visitingTime = builder.getVisitingTime();
        this.appeal = builder.getAppeal();
    }

}
