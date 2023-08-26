package com.fhdw.hospitalbe;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fhdw.hospitalbe.model.enums.Area;
import com.fhdw.hospitalbe.model.enums.Gender;
import com.fhdw.hospitalbe.model.enums.Position;
import com.fhdw.hospitalbe.repository.*;
import com.fhdw.hospitalbe.repository.table.*;
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

    public DoctorTable createDoctor() {
        return doctorRepository.save(DoctorTable.builder()
                .firstName("First")
                .lastName("Last")
                .gender(Gender.Male)
                .position(Position.ASSISTANT_DOCTOR)
                .area(Area.DERMATOLOGY)
                .build());
    }

    public PatientTable createPatientWithRecord() {
        PatientTable p =  PatientTable.builder()
                .firstName("F")
                .lastName("L")
                .gender(Gender.Male)
                .birthday(LocalDate.of(2020, 10, 10))
                .isPrivate(true)
                .patientRecord(PatientRecordTable.builder()
                        .build())
                .build();
        p.getPatientRecord().setPatient(p);
        return patientRepository.save(p);
    }

    public AppointmentTable createAppointment(DoctorTable d, PatientRecordTable pr) {
        return appointmentRepository.save(AppointmentTable.builder()
                .appeal("Grund")
                .doctor(d)
                .patientRecord(pr)
                .visitingTime(LocalDateTime.of(2020, 10, 10, 10, 10))
                .build());
    }

    public VisitTable createVisit(DoctorTable d, PatientRecordTable pr) {
        return visitRepository.save(
                VisitTable.builder()
                        .doctor(d)
                        .patientRecord(pr)
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
