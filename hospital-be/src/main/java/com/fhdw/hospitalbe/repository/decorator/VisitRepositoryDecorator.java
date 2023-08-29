package com.fhdw.hospitalbe.repository.decorator;

import com.fhdw.hospitalbe.model.Visit;
import com.fhdw.hospitalbe.model.mapper.VisitMapper;
import com.fhdw.hospitalbe.repository.VisitRepository;
import com.fhdw.hospitalbe.repository.table.VisitTable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitRepositoryDecorator {

    private final VisitRepository repository;

    public VisitRepositoryDecorator(VisitRepository repository) {
        this.repository = repository;
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

    public Visit saveVisit(Visit visit) {
        if (visit == null) {
            return null;
        }
        VisitTable visitTable = this.repository.save(VisitMapper.toTable(visit));
        return VisitMapper.fromTable(visitTable);
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
        VisitTable visitTable = this.repository.save(VisitMapper.toTable(visit));
        return VisitMapper.fromTable(visitTable);
    }
}
