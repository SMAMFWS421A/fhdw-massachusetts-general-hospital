package com.fhdw.hospitalbe;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fhdw.hospitalbe.model.Appointment;
import com.fhdw.hospitalbe.model.Doctor;
import com.fhdw.hospitalbe.model.Patient;
import com.fhdw.hospitalbe.model.Visit;
import com.fhdw.hospitalbe.model.builder.*;
import com.fhdw.hospitalbe.model.enums.Area;
import com.fhdw.hospitalbe.model.enums.Gender;
import com.fhdw.hospitalbe.model.enums.Position;
import com.fhdw.hospitalbe.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class DatabaseTestUtil {
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRecordRepository patientRecordRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private VisitRepository visitRepository;

    @Transactional
    public void deleteAll() {
        visitRepository.deleteAll();
        appointmentRepository.deleteAll();
        doctorRepository.deleteAll();
        patientRecordRepository.deleteAll();
        patientRepository.deleteAll();
    }

    public Doctor createDoctor() {
        return doctorRepository.save(new DoctorBuilder()
                .firstName("First")
                .lastName("Last")
                .gender(Gender.Male)
                .position(Position.ASSISTANT_DOCTOR)
                .area(Area.DERMATOLOGY)
                .build());
    }

    public Patient createPatientWithRecord() {
        Patient p =  new PatientBuilder()
                .firstName("F")
                .lastName("L")
                .gender(Gender.Male)
                .birthday(LocalDate.of(2020, 10, 10))
                .isPrivate(true)
                .patientRecord(new PatientRecordBuilder()
                        .build())
                .build();
        p.getPatientRecord().setPatient(p);
        return patientRepository.save(p);
    }

    public Appointment createAppointment(Doctor d, Patient p) {
        return appointmentRepository.save(new AppointmentBuilder()
                .appeal("Grund")
                .doctor(d)
                .patientRecord(p.getPatientRecord())
                .visitingTime(LocalDateTime.of(2020, 10, 10, 10, 10))
                .build());
    }

    public Visit createVisit(Doctor d, Patient p) {
        return visitRepository.save(
                new VisitBuilder()
                        .doctor(d)
                        .patientRecord(p.getPatientRecord())
                        .arrivedTime(LocalDateTime.of(2020, 10, 10, 10, 10))
                        .build()
        );
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }




}
