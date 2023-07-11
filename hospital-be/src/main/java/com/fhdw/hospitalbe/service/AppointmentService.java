package com.fhdw.hospitalbe.service;

import com.fhdw.hospitalbe.model.Appointment;
import com.fhdw.hospitalbe.repository.AppointmentRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

  private final AppointmentRepository repository;

  public AppointmentService(AppointmentRepository repository) {
    this.repository = repository;
  }

  public List<Appointment> getAppointments() {
    return this.repository.findAll();
  }

  public Appointment getAppointment(Long id) {
    if (id == null) {
      return null;
    }
    return this.repository.findById(id).orElse(null);
  }

  public Appointment createAppointment(Appointment appointment) {
    if (appointment == null) {
      return null;
    }
    return this.repository.save(appointment);
  }

  public void deleteAppointment(Long id) {
        if (id != null && this.repository.existsById(id)) this.repository.deleteById(id);
    }

}
