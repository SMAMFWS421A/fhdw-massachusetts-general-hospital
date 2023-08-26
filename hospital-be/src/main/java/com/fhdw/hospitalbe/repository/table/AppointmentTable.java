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
public class AppointmentTable {

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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "visiting_time")
    LocalDateTime visitingTime;

    @Column(name = "appeal")
    String appeal;

}
