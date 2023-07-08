package com.fhdw.hospitalbe.service;

import com.fhdw.hospitalbe.model.Visit;
import com.fhdw.hospitalbe.repository.VisitRepository;
import org.springframework.stereotype.Service;

@Service
public class VisitService {

    private final VisitRepository repository;

    public VisitService(VisitRepository repository) {
        this.repository = repository;
    }


    public Visit getVisit(Long id) {
        if (id == null) return null;
        return this.repository.findById(id).orElse(null);
    }

    public Visit createVisit(Visit visit) {
        if (visit == null) return null;
        return this.repository.save(visit);
    }

    public void deleteVisit(Long id) {
        if (id != null && this.repository.existsById(id)) this.repository.deleteById(id);
    }


}
