package com.fhdw.hospitalbe.controller;

import com.fhdw.hospitalbe.model.Appointment;
import com.fhdw.hospitalbe.service.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/appointment")

public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping(path = "{appointment_id}")
    public ResponseEntity<Appointment> getAppointment(@PathVariable("appointment_id") long appointment_id) {
        return new ResponseEntity<Appointment>(appointmentService.getAppointment(appointment_id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(Appointment appointment) {
        return new ResponseEntity<Appointment>(appointmentService.createAppointment(appointment), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{appointment_id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable("appointment_id") long appointment_id) {
        appointmentService.deleteAppointment(appointment_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
