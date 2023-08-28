package com.fhdw.hospitalbe.model;

import com.fhdw.hospitalbe.model.builder.AppointmentBuilder;
import com.fhdw.hospitalbe.model.builder.VisitBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Visit {
    Long id;
    PatientRecord patientRecord;
    Doctor doctor;
    String appeal;
    LocalDateTime plannedTime;
    LocalDateTime arrivedTime;
    String anamnesis;
    String measurement;
    String diagnosis;
    String planOfActions;

    public Visit(VisitBuilder builder) {
        this.id = builder.getId();
        this.patientRecord = builder.getPatientRecord();
        this.doctor = builder.getDoctor();
        this.appeal = builder.getAppeal();
        this.plannedTime = builder.getPlannedTime();
        this.arrivedTime = builder.getArrivedTime();
        this.anamnesis = builder.getAnamnesis();
        this.measurement = builder.getMeasurement();
        this.diagnosis = builder.getDiagnosis();
        this.planOfActions = builder.getPlanOfActions();
    }

    public static Visit createVisitFromAppointment(Appointment appointment) {
        if (appointment == null) {
            return null;
        }
        Visit visit = new VisitBuilder().patientRecord(appointment.getPatientRecord())
                .doctor(appointment.getDoctor()).appeal(appointment.getAppeal()).plannedTime(appointment.getVisitingTime())
                .arrivedTime(LocalDateTime.now()).build();
        return visit;
    }
}
