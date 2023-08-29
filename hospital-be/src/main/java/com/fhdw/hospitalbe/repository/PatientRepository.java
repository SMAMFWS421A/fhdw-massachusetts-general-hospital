package com.fhdw.hospitalbe.repository;

import com.fhdw.hospitalbe.repository.table.PatientTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<PatientTable, Long> {
}
