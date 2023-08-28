package com.fhdw.hospitalbe.model.mapper;

import com.fhdw.hospitalbe.model.Doctor;
import com.fhdw.hospitalbe.model.builder.DoctorBuilder;
import com.fhdw.hospitalbe.repository.table.DoctorTable;

public class DoctorMapper {
    public static Doctor fromTable(DoctorTable doctorTable) {
        if (doctorTable == null) return null;
        return new DoctorBuilder()
                .id(doctorTable.getId())
                .firstName(doctorTable.getFirstName())
                .lastName(doctorTable.getLastName())
                .area(doctorTable.getArea())
                .gender(doctorTable.getGender())
                .position(doctorTable.getPosition())
                .build();
    }

    public static DoctorTable toTable(Doctor doctor) {
        if (doctor == null) return null;
        return DoctorTable.builder()
                .id(doctor.getId())
                .firstName(doctor.getFirstName())
                .lastName(doctor.getLastName())
                .area(doctor.getArea())
                .gender(doctor.getGender())
                .position(doctor.getPosition())
                .build();
    }
}
