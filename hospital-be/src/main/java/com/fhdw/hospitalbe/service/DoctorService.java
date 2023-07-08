package com.fhdw.hospitalbe.service;

import com.fhdw.hospitalbe.model.Doctor;
import com.fhdw.hospitalbe.repository.DoctorRepository;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    private final DoctorRepository repository;

    public DoctorService(DoctorRepository repository) {
        this.repository = repository;
    }

    public Doctor getDoctor(Long id) {
        if (id == null) return null;
        return this.repository.findById(id).orElse(null);
    }

    public Doctor createDoctor(Doctor doctor) {
        if (doctor == null) return null;
        return this.repository.save(doctor);
    }

    public void deleteDoctor(Long id) {
        if (id != null && this.repository.existsById(id)) this.repository.deleteById(id);
    }


}
