package com.fhdw.hospitalbe.repository.decorator;

import com.fhdw.hospitalbe.model.Appointment;
import com.fhdw.hospitalbe.model.Visit;
import com.fhdw.hospitalbe.model.mapper.AppointmentMapper;
import com.fhdw.hospitalbe.repository.AppointmentRepository;
import com.fhdw.hospitalbe.repository.table.AppointmentTable;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentRepositoryDecorator {

  private final AppointmentRepository repository;
  private final VisitRepositoryDecorator visitRepositoryDecorator;


  public AppointmentRepositoryDecorator(AppointmentRepository repository, VisitRepositoryDecorator visitRepositoryDecorator) {
    this.repository = repository;
    this.visitRepositoryDecorator = visitRepositoryDecorator;
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

  public Appointment saveAppointment(Appointment appointment) {
    if (appointment == null) {
      return null;
    }
    AppointmentTable appointmentTable = this.repository.save(AppointmentMapper.toTable(appointment));
    return AppointmentMapper.fromTable(appointmentTable);
  }

  @Transactional
  public Visit saveVisitForAppointment(Appointment appointment, Visit visit) {
    if (appointment == null || visit == null) {
      return null;
    }
    this.deleteAppointment(appointment.getId());
    return visitRepositoryDecorator.saveVisit(visit);
  }

  public void deleteAppointment(Long id) {
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
