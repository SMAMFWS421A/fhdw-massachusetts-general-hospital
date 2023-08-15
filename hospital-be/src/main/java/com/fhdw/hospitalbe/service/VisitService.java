package com.fhdw.hospitalbe.service;

import com.fhdw.hospitalbe.model.Appointment;
import com.fhdw.hospitalbe.model.Doctor;
import com.fhdw.hospitalbe.model.Visit;
import com.fhdw.hospitalbe.repository.VisitRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class VisitService {

    private final VisitRepository repository;
    private final AppointmentService appointmentService;

    public VisitService(VisitRepository repository, AppointmentService appointmentService) {
        this.repository = repository;
        this.appointmentService = appointmentService;
    }

    public List<Visit> getVisits() {
        return this.repository.findAll();
    }

    public Visit getVisit(Long id) {
        if (id == null) {
            return null;
        }
        return this.repository.findById(id).orElse(null);
    }

    public Visit createVisit(Visit visit) {
        if (visit == null) {
            return null;
        }
        return this.repository.save(visit);
    }

    @Transactional
    public Visit createVisitFromAppointment(Long appointmentId) {
        if (appointmentId == null) {
            return null;
        }
        Appointment appointment = appointmentService.getAppointment(appointmentId);
        if (appointment == null) {
            return null;
        }
        Visit visit =
            new Visit(null, appointment.getPatientRecord(), appointment.getDoctor(), appointment.getAppeal(),
                appointment.getVisitingTime(), LocalDateTime.now(), "",
                "", "", "");
        appointmentService.deleteAppointment(appointmentId);
        return this.repository.save(visit);
    }

    @Transactional
    public Appointment convertToAppointment(Long visitId) {
        if (visitId == null) {
            return null;
        }
        Visit visit = this.getVisit(visitId);
        if (visit == null) {
            return null;
        }
        this.deleteVisit(visitId);
        Appointment appointment = new Appointment(null, visit.getPatientRecord(), visit.getDoctor(),
            visit.getPlannedTime(), visit.getAppeal());
        return appointmentService.createAppointment(appointment);
    }

    public void deleteVisit(Long id) {
        if (id != null && this.repository.existsById(id)) {
            this.repository.deleteById(id);
        }
    }

    public Visit updateVisit(Visit visit) {
        if (visit == null) {
            return null;
        }
        return this.repository.save(visit);
    }
}
