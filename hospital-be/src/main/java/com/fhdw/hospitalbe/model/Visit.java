package com.fhdw.hospitalbe.model;

import com.fhdw.hospitalbe.model.builder.VisitBuilder;
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
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    PatientRecord patientRecord;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    Doctor doctor;

    @Column(name = "appeal")
    String appeal;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "visiting_time")
    LocalDateTime plannedTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "arrived_time")
    LocalDateTime arrivedTime;

    @Column(name = "anamnesis")
    String anamnesis;

    @Column(name = "measurement")
    String measurement;

    @Column(name = "diagnosis")
    String diagnosis;

    @Column(name = "plan_of_actions")
    String planOfActions;

    public Visit(VisitBuilder builder) {
        this.id = builder.getId();
        this.patientRecord = builder.getPatientRecord();
        this.doctor = builder.getDoctor();
        this.appeal = builder.getAppeal();
        this.plannedTime = builder.getPlannedTime();
        this.arrivedTime = builder.getArrivedTime();
        this.anamnesis = builder.getAnamnesis();
        this.measurement = builder.getMeasurement();
        this.diagnosis = builder.getDiagnosis();
        this.planOfActions = builder.getPlanOfActions();
    }
}
