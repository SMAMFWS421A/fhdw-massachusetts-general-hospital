package com.fhdw.hospitalbe.model;

import com.fhdw.hospitalbe.model.builder.PatientBuilder;
import com.fhdw.hospitalbe.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    Long id;
    String firstName;
    String lastName;
    Gender gender;
    LocalDate birthday;
    Boolean isPrivate;
    String phoneNumber;
    String city;
    PatientRecord patientRecord;

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
