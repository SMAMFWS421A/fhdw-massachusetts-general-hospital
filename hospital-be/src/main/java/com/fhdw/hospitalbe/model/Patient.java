package com.fhdw.hospitalbe.model;

import com.fhdw.hospitalbe.model.builder.PatientBuilder;
import com.fhdw.hospitalbe.model.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    @PrimaryKeyJoinColumn
    PatientRecord patientRecord;

    //@OneToMany(fetch = FetchType.EAGER, mappedBy = "patient")
    //private List<Appointment> appointmentList;

    public Patient(PatientBuilder patientBuilder) {
        this.id = patientBuilder.getId();
        this.firstName = patientBuilder.getFirstName();
        this.lastName = patientBuilder.getLastName();
        this.gender = patientBuilder.getGender();
        this.birthday = patientBuilder.getBirthday();
        this.isPrivate = patientBuilder.getIsPrivate();
        this.phoneNumber = patientBuilder.getPhoneNumber();
        this.city = patientBuilder.getCity();
        this.patientRecord = patientBuilder.getPatientRecord();
    }

}
