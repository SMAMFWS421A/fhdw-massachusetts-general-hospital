package com.fhdw.hospitalbe.repository;

import com.fhdw.hospitalbe.repository.table.AppointmentTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentTable, Long> {
}
