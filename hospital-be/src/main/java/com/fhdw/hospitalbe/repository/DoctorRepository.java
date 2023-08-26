package com.fhdw.hospitalbe.repository;

import com.fhdw.hospitalbe.repository.table.DoctorTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorTable, Long> {

}
