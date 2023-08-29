package com.fhdw.hospitalbe.repository.table;

import com.fhdw.hospitalbe.model.enums.Area;
import com.fhdw.hospitalbe.model.enums.Gender;
import com.fhdw.hospitalbe.model.enums.Position;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorTable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    Long id;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "gender")
    Gender gender;

    @Column(name = "position")
    Position position;

    @Column(name = "area")
    Area area;

    //--------------------------------------
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "doctor")
    Set<AppointmentTable> appointments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "doctor")
    Set<VisitTable> visits;

    @PreRemove
    private void preRemove() {
        for (AppointmentTable a : appointments) {
            a.setDoctor(null);
        }
        for (VisitTable v : visits) {
            v.setDoctor(null);
        }
    }
}
