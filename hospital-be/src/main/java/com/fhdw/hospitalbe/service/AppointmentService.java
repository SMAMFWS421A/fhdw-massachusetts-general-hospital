package com.fhdw.hospitalbe.service;

import com.fhdw.hospitalbe.model.Appointment;
import com.fhdw.hospitalbe.model.mapper.AppointmentMapper;
import com.fhdw.hospitalbe.repository.AppointmentRepository;
import com.fhdw.hospitalbe.repository.table.AppointmentTable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

  private final AppointmentRepository repository;

  public AppointmentService(AppointmentRepository repository) {
    this.repository = repository;
  }

  public List<Appointment> findAllAppointments() {
    List<AppointmentTable> appointmentTables = this.repository.findAll();
    return appointmentTables.stream().map(AppointmentMapper::fromTable).toList();

  }

  public Appointment findAppointment(Long id) {
    if (id == null) {
      return null;
    }
    AppointmentTable appointmentTable = this.repository.findById(id).orElse(null);
    if (appointmentTable == null) return null;
    return AppointmentMapper.fromTable(appointmentTable);
  }

  public Appointment arrangeAppointment(Appointment appointment) {
    if (appointment == null) {
      return null;
    }
    AppointmentTable appointmentTable = this.repository.save(AppointmentMapper.toTable(appointment));
    return AppointmentMapper.fromTable(appointmentTable);
  }

  public void cancelAppointment(Long id) {
    if (id != null && this.repository.existsById(id)) this.repository.deleteById(id);
  }

  public Appointment updateAppointment(Appointment appointment) {
    if (appointment == null) {
      return null;
    }
    AppointmentTable appointmentTable = this.repository.save(AppointmentMapper.toTable(appointment));
    return AppointmentMapper.fromTable(appointmentTable);
  }
}
