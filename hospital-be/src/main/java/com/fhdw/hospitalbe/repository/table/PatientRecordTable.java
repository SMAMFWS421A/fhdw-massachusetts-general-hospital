package com.fhdw.hospitalbe.repository.table;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientRecordTable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    Long id;

    @Column(name = "medication")
    String medication;

    @Column(name = "diseases")
    String diseases;

    @OneToOne(mappedBy = "patientRecord")
    PatientTable patient;

    //--------------------------------------
    @OneToMany(mappedBy = "patientRecord", cascade = CascadeType.REMOVE)
    Set<AppointmentTable> appointments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "patientRecord")
    Set<VisitTable> visits;

    @PreRemove
    private void preRemove() {
        for (VisitTable v : visits) {
            v.setPatientRecord(null);
        }
    }
}
