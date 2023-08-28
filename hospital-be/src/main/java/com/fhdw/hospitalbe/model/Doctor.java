package com.fhdw.hospitalbe.model;


import com.fhdw.hospitalbe.model.builder.DoctorBuilder;
import com.fhdw.hospitalbe.model.enums.Area;
import com.fhdw.hospitalbe.model.enums.Gender;
import com.fhdw.hospitalbe.model.enums.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
    Long id;
    String firstName;
    String lastName;
    Gender gender;
    Position position;
    Area area;


    public Doctor(DoctorBuilder builder) {
        this.id = builder.getId();
        this.firstName = builder.getFirstName();
        this.lastName = builder.getLastName();
        this.gender = builder.getGender();
        this.position = builder.getPosition();
        this.area = builder.getArea();
    }
}
