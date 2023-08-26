package com.fhdw.hospitalbe.service;

import com.fhdw.hospitalbe.model.Doctor;
import com.fhdw.hospitalbe.model.mapper.DoctorMapper;
import com.fhdw.hospitalbe.repository.DoctorRepository;
import com.fhdw.hospitalbe.repository.table.DoctorTable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

  private final DoctorRepository repository;

  public DoctorService(DoctorRepository repository) {
    this.repository = repository;
  }

  public List<Doctor> findAllDoctors() {
    List<DoctorTable> doctorTableList = this.repository.findAll();
    return doctorTableList.stream().map(DoctorMapper::fromTable).toList();
  }

  public Doctor findDoctor(Long id) {
    if (id == null) {
      return null;
    }
    DoctorTable doctorTable = this.repository.findById(id).orElse(null);
    if (doctorTable == null) return null;
    return DoctorMapper.fromTable(doctorTable);
  }

  public Doctor hireDoctor(Doctor doctor) {
    if (doctor == null) {
      return null;
    }
    DoctorTable doctorTable = this.repository.save(DoctorMapper.toTable(doctor));
    return DoctorMapper.fromTable(doctorTable);
  }

  public void fireDoctor(Long id) {
    if (id != null && this.repository.existsById(id)) this.repository.deleteById(id);
  }

  public Doctor updateDoctor(Doctor doctor) {
    if (doctor == null) {
      return null;
    }
    DoctorTable doctorTable = this.repository.save(DoctorMapper.toTable(doctor));
    return DoctorMapper.fromTable(doctorTable);
  }
}
