package com.fhdw.hospitalbe.model;

import com.fhdw.hospitalbe.model.builder.PatientRecordBuilder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    Long id;

    @Column(name = "medication")
    String medication;

    @Column(name = "diseases")
    String diseases;

    //--------------------------------------
    @MapsId
    @OneToOne(mappedBy = "patientRecord")
    @JoinColumn(name = "id")
    Patient patient;

    @OneToMany(mappedBy = "patientRecord")
    Set<Appointment> appointments;

    @OneToMany(mappedBy = "patientRecord")
    Set<Visit> visits;

    public PatientRecord(PatientRecordBuilder builder) {
        this.id = builder.getId();
        this.medication = builder.getMedication();
        this.diseases = builder.getDiseases();
        this.patient = builder.getPatient();
        this.appointments = builder.getAppointments();
        this.visits = builder.getVisits();
    }

}
