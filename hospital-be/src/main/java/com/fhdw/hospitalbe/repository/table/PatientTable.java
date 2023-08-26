package com.fhdw.hospitalbe.repository.table;

import com.fhdw.hospitalbe.model.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientTable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    Long id;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "gender")
    Gender gender;

    @Temporal(TemporalType.DATE)
    @Column(name = "birthday")
    LocalDate birthday;

    @Column(name = "is_privat")
    Boolean isPrivate;

    @Column(name = "phone_number")
    String phoneNumber;

    @Column(name = "city")
    String city;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "patient_record_id")
    PatientRecordTable patientRecord;

}
