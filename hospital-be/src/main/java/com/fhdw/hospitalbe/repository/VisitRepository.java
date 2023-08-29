package com.fhdw.hospitalbe.repository;

import com.fhdw.hospitalbe.repository.table.VisitTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitRepository extends JpaRepository<VisitTable, Long> {
}
