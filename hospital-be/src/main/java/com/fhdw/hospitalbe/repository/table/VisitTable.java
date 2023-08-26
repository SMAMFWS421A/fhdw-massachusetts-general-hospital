package com.fhdw.hospitalbe.repository.table;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VisitTable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    PatientRecordTable patientRecord;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    DoctorTable doctor;

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
