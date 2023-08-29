package com.fhdw.hospitalbe.model.builder;

import com.fhdw.hospitalbe.model.Patient;
import com.fhdw.hospitalbe.model.PatientRecord;
import com.fhdw.hospitalbe.model.enums.Gender;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class PatientBuilder {
    Long id;
    String firstName;
    String lastName;
    Gender gender;
    LocalDate birthday;
    Boolean isPrivate;
    String phoneNumber;
    String city;
    PatientRecord patientRecord;

    public PatientBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public PatientBuilder firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public PatientBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public PatientBuilder gender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public PatientBuilder birthday(LocalDate birthday) {
        this.birthday = birthday;
        return this;
    }

    public PatientBuilder isPrivate(Boolean isPrivate) {
        this.isPrivate = isPrivate;
        return this;
    }

    public PatientBuilder phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public PatientBuilder city(String city) {
        this.city = city;
        return this;
    }

    public PatientBuilder patientRecord(PatientRecord patientRecord) {
        this.patientRecord = patientRecord;
        return this;
    }

    public Patient build() {
        return new Patient(this);
    }
}
