package com.fhdw.hospitalbe.controller;

import com.fhdw.hospitalbe.model.Appointment;
import com.fhdw.hospitalbe.model.Doctor;
import com.fhdw.hospitalbe.service.AppointmentService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Appointment> getAppointment(@PathVariable("appointment_id") long appointment_id) {
        return new ResponseEntity<Appointment>(appointmentService.getAppointment(appointment_id), HttpStatus.OK);
    }

    @PostMapping
    @ApiResponse(responseCode = "201", description = "Created Appointment",
        content = @Content(schema = @Schema(implementation = Appointment.class)))
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
        return new ResponseEntity<Appointment>(appointmentService.createAppointment(appointment), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{appointment_id}")
    @ApiResponse(responseCode = "204", description = "Deleted Appointment",
        content = @Content(schema = @Schema(implementation = Appointment.class)))
    public ResponseEntity<Void> deleteAppointment(@PathVariable("appointment_id") long appointment_id) {
        appointmentService.deleteAppointment(appointment_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path= "{appointment_id}")
    @ApiResponse(responseCode = "200", description = "Update Appointment",
            content = @Content(schema = @Schema(implementation = Appointment.class)))
    public ResponseEntity<Appointment> updateDoctor(@RequestBody Appointment appointment) {
        return new ResponseEntity<Appointment>(appointmentService.updateAppointment(appointment), HttpStatus.OK);
    }
}
