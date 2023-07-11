package com.fhdw.hospitalbe.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

}
