package com.fhdw.hospitalbe.model.builder;

import com.fhdw.hospitalbe.model.Doctor;
import com.fhdw.hospitalbe.model.enums.Area;
import com.fhdw.hospitalbe.model.enums.Gender;
import com.fhdw.hospitalbe.model.enums.Position;
import lombok.Getter;

@Getter
public class DoctorBuilder {
    Long id;
    String firstName;
    String lastName;
    Gender gender;
    Position position;
    Area area;

    public DoctorBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public DoctorBuilder firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public DoctorBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public DoctorBuilder gender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public DoctorBuilder position(Position position) {
        this.position = position;
        return this;
    }

    public DoctorBuilder area(Area area) {
        this.area = area;
        return this;
    }

    public Doctor build() {
        return new Doctor(this);
    }

}
