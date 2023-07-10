package com.fhdw.hospitalbe.model;

import jakarta.persistence.*;
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

    @MapsId
    @OneToOne(mappedBy = "patientRecord")
    @JoinColumn(name = "id")
    private Patient patient;

    @Column(name = "medication")
    String medication;

    @Column(name = "diseases")
    String diseases;

}
