package com.fhdw.hospitalbe.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

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
    @JoinColumn(name = "patient_id",nullable = false)
    PatientRecord patientRecord;

    @Column(name = "appeal")
    String appeal;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "visiting_time")
    LocalDate plannedTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "arrived_time")
    LocalDate arrivedTime;

    @Column(name = "anamnesis")
    String anamnesis;

    @Column(name = "measurement")
    String measurement;

    @Column(name = "diagnosis")
    String diagnosis;

    @Column(name = "plan_of_actions")
    String planOfActions;
}
