package com.fhdw.hospitalbe.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    Long id;

    @OneToOne
    @JoinColumn(name = "appointment_id")
    Appointment appointment;

    @Column(name = "anamnesis")
    String anamnesis;

    @Column(name = "measurement")
    String measurement;

    @Column(name = "diagnosis")
    String diagnosis;

    @Column(name = "plan_of_actions")
    String planOfActions;

}
