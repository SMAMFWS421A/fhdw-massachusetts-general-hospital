package com.fhdw.hospitalbe.controller;

import com.fhdw.hospitalbe.model.Appointment;
import com.fhdw.hospitalbe.service.AppointmentService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "appointment")
@RequestMapping(path = "api/v1/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }


    @GetMapping(path = "{appointment_id}")
    @ApiResponse(responseCode = "200", description = "Found Appointment",
            content = @Content(schema = @Schema(implementation = Appointment.class)))
    public ResponseEntity<Appointment> findAppointment(@PathVariable("appointment_id") long appointment_id) {
        Appointment appDb = appointmentService.findAppointment(appointment_id);
        if (appDb == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(appDb, HttpStatus.OK);
    }

    @GetMapping()
    @ApiResponse(responseCode = "200", description = "Found Appointments",
            content = @Content(schema = @Schema(implementation = List.class)))
    public ResponseEntity<List<Appointment>> findAllAppointments() {
        List<Appointment> appointments = appointmentService.findAllAppointments();
        if (appointments == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @PostMapping
    @ApiResponse(responseCode = "201", description = "Arranged Appointment",
            content = @Content(schema = @Schema(implementation = Appointment.class)))
    public ResponseEntity<Appointment> arrangeAppointment(@RequestBody Appointment appointment) {
        Appointment appDb = appointmentService.arrangeAppointment(appointment);
        if (appDb == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(appDb, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{appointment_id}")
    @ApiResponse(responseCode = "204", description = "Canceled Appointment",
            content = @Content(schema = @Schema(implementation = Appointment.class)))
    public ResponseEntity<Void> cancelAppointment(@PathVariable("appointment_id") long appointment_id) {
        appointmentService.cancelAppointment(appointment_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "{appointment_id}") //TODO
    @ApiResponse(responseCode = "200", description = "Updated Appointment",
            content = @Content(schema = @Schema(implementation = Appointment.class)))
    public ResponseEntity<Appointment> updateAppointment(@RequestBody Appointment appointment) {
        Appointment appDb = appointmentService.updateAppointment(appointment);
        if (appDb == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(appDb, HttpStatus.OK);
    }
}
