package com.fhdw.hospitalbe.model;


import com.fhdw.hospitalbe.model.builder.DoctorBuilder;
import com.fhdw.hospitalbe.model.enums.Area;
import com.fhdw.hospitalbe.model.enums.Gender;
import com.fhdw.hospitalbe.model.enums.Position;
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
public class Doctor {
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

    @Column(name = "position")
    Position position;

    @Column(name = "area")
    Area area;

    //--------------------------------------
    @OneToMany(mappedBy = "doctor")
    Set<Appointment> appointments;

    @OneToMany(mappedBy = "doctor")
    Set<Visit> visits;

    public Doctor(DoctorBuilder builder) {
        this.id = builder.getId();
        this.firstName = builder.getFirstName();
        this.lastName = builder.getLastName();
        this.gender = builder.getGender();
        this.position = builder.getPosition();
        this.area = builder.getArea();
        this.appointments = builder.getAppointments();
        this.visits = builder.getVisits();
    }
}
