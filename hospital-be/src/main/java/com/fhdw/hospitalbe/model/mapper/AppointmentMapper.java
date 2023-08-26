package com.fhdw.hospitalbe.model.mapper;

import com.fhdw.hospitalbe.model.Appointment;
import com.fhdw.hospitalbe.model.builder.AppointmentBuilder;
import com.fhdw.hospitalbe.repository.table.AppointmentTable;

public class AppointmentMapper {
    public static Appointment fromTable(AppointmentTable appointmentTable) {
        if (appointmentTable == null) return null;
        return new AppointmentBuilder()
                .id(appointmentTable.getId())
                .patientRecord(PatientRecordMapper.fromTable(appointmentTable.getPatientRecord()))
                .doctor(DoctorMapper.fromTable(appointmentTable.getDoctor()))
                .visitingTime(appointmentTable.getVisitingTime())
                .appeal(appointmentTable.getAppeal())
                .build();
    }

    public static AppointmentTable toTable(Appointment appointment) {
        if (appointment == null) return null;
        return AppointmentTable.builder()
                .id(appointment.getId())
                .patientRecord(PatientRecordMapper.toTable(appointment.getPatientRecord()))
                .doctor(DoctorMapper.toTable(appointment.getDoctor()))
                .visitingTime(appointment.getVisitingTime())
                .appeal(appointment.getAppeal())
                .build();

    }
}
