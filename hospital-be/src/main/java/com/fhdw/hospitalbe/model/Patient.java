package com.fhdw.hospitalbe.model;

import com.fhdw.hospitalbe.model.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

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
    String first_name;

    @Column(name = "last_name")
    String last_name;

    @Column(name = "gender")
    Gender gender;

    @Temporal(TemporalType.DATE)
    @Column(name = "birthday")
    Date birthday;

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


}
