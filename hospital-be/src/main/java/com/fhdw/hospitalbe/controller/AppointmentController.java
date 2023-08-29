package com.fhdw.hospitalbe.controller;

import com.fhdw.hospitalbe.model.Appointment;
import com.fhdw.hospitalbe.model.Visit;
import com.fhdw.hospitalbe.repository.decorator.AppointmentRepositoryDecorator;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Tag(name = "appointment")
@RequestMapping(path = "api/v1/appointment")
@CrossOrigin("http://localhost:8080")

public class AppointmentController {

    private final AppointmentRepositoryDecorator appointmentRepositoryDecorator;

    public AppointmentController(AppointmentRepositoryDecorator appointmentRepositoryDecorator) {
        this.appointmentRepositoryDecorator = appointmentRepositoryDecorator;
    }


    @GetMapping(path = "{appointment_id}")
    @ApiResponse(responseCode = "200", description = "Found Appointment",
            content = @Content(schema = @Schema(implementation = Appointment.class)))
    public ResponseEntity<Appointment> findAppointment(@PathVariable("appointment_id") long appointment_id) {
        Appointment appDb = appointmentRepositoryDecorator.findAppointment(appointment_id);
        if (appDb == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(appDb, HttpStatus.OK);
    }

    @GetMapping()
    @ApiResponse(responseCode = "200", description = "Found Appointments",
            content = @Content(schema = @Schema(implementation = List.class)))
    public ResponseEntity<List<Appointment>> findAllAppointments() {
        List<Appointment> appointments = appointmentRepositoryDecorator.findAllAppointments();
        if (appointments == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @PostMapping
    @ApiResponse(responseCode = "201", description = "Arranged Appointment",
            content = @Content(schema = @Schema(implementation = Appointment.class)))
    public ResponseEntity<Appointment> arrangeAppointment(@RequestBody Appointment appointment) {
        Appointment appDb = appointmentRepositoryDecorator.saveAppointment(appointment);
        if (appDb == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(appDb, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{appointment_id}/to-visit")
    @ApiResponse(responseCode = "201", description = "Converted to Visit",
            content = @Content(schema = @Schema(implementation = Visit.class)))
    public ResponseEntity<Visit> registerVisitFromAppointment(@PathVariable long appointment_id) {
        Appointment appointment = appointmentRepositoryDecorator.findAppointment(appointment_id);
        if (appointment == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Visit generatedVisit = appointment.convertToVisit(LocalDateTime.now());

        Visit visit = appointmentRepositoryDecorator.saveVisitForAppointment(appointment, generatedVisit);

        if(visit == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(visit, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{appointment_id}")
    @ApiResponse(responseCode = "204", description = "Canceled Appointment",
            content = @Content(schema = @Schema(implementation = Appointment.class)))
    public ResponseEntity<Void> cancelAppointment(@PathVariable("appointment_id") long appointment_id) {
        appointmentRepositoryDecorator.deleteAppointment(appointment_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping()
    @ApiResponse(responseCode = "200", description = "Updated Appointment",
            content = @Content(schema = @Schema(implementation = Appointment.class)))
    public ResponseEntity<Appointment> updateAppointment(@RequestBody Appointment appointment) {
        Appointment appDb = appointmentRepositoryDecorator.updateAppointment(appointment);
        if (appDb == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(appDb, HttpStatus.OK);
    }
}
