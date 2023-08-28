package com.fhdw.hospitalbe.service;

import com.fhdw.hospitalbe.model.Visit;
import com.fhdw.hospitalbe.model.mapper.VisitMapper;
import com.fhdw.hospitalbe.repository.VisitRepository;
import com.fhdw.hospitalbe.repository.table.VisitTable;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitService {

    private final VisitRepository repository;
    private final AppointmentService appointmentService;

    public VisitService(VisitRepository repository, AppointmentService appointmentService) {
        this.repository = repository;
        this.appointmentService = appointmentService;
    }

    public List<Visit> findAllVisits() {
        List<VisitTable> visitTableList = this.repository.findAll();
        return visitTableList.stream().map(VisitMapper::fromTable).toList();
    }

    public Visit findVisit(Long id) {
        if (id == null) {
            return null;
        }
        VisitTable visitTable = this.repository.findById(id).orElse(null);
        if (visitTable == null) return null;
        return VisitMapper.fromTable(visitTable);
    }

    public Visit registerVisit(Visit visit) {
        if (visit == null) {
            return null;
        }
        VisitTable visitTable = this.repository.save(VisitMapper.toTable(visit));
        return VisitMapper.fromTable(visitTable);
    }

    @Transactional
    public Visit registerVisitFromAppointment(long appointment_id) {
        Visit visit = Visit.createVisitFromAppointment(appointmentService.findAppointment(appointment_id));
        if(visit == null) return null;
        appointmentService.cancelAppointment(appointment_id);
        return this.registerVisit(visit);
    }


    public void cancelVisit(Long id) {
        if (id != null && this.repository.existsById(id)) {
            this.repository.deleteById(id);
        }
    }

    public Visit updateVisit(Visit visit) {
        if (visit == null) {
            return null;
        }
        VisitTable visitTable = this.repository.save(VisitMapper.toTable(visit));
        return VisitMapper.fromTable(visitTable);
    }
}
