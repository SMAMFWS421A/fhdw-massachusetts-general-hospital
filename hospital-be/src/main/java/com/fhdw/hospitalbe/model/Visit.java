package com.fhdw.hospitalbe.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @JoinColumn(name = "doctor_id", nullable = false)
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
}
