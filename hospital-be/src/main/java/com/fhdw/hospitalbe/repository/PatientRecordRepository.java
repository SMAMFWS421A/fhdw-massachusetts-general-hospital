package com.fhdw.hospitalbe.repository;

import com.fhdw.hospitalbe.repository.table.PatientRecordTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRecordRepository extends JpaRepository<PatientRecordTable, Long> {
}
