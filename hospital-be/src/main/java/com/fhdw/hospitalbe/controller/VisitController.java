package com.fhdw.hospitalbe.controller;

import com.fhdw.hospitalbe.model.Doctor;
import com.fhdw.hospitalbe.model.Visit;
import com.fhdw.hospitalbe.service.VisitService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/visit")

public class VisitController {

    private final VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @GetMapping(path = "{visit_id}")
    @ApiResponse(responseCode = "200", description = "Found Visit",
            content = @Content(schema = @Schema(implementation = Visit.class)))
    public ResponseEntity<Visit> getVisit(@PathVariable("visit_id") long visit_id) {
        return new ResponseEntity<Visit>(visitService.getVisit(visit_id), HttpStatus.OK);
    }

    @PostMapping
    @ApiResponse(responseCode = "201", description = "Created Visit",
            content = @Content(schema = @Schema(implementation = Visit.class)))
    public ResponseEntity<Visit> createVisit(@RequestBody Visit visit) {
        return new ResponseEntity<Visit>(visitService.createVisit(visit), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{visit_id}")
    @ApiResponse(responseCode = "204", description = "Deleted Visit",
            content = @Content(schema = @Schema(implementation = Visit.class)))
    public ResponseEntity<Void> deleteVisit(@PathVariable("visit_id") long visit_id) {
        visitService.deleteVisit(visit_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path= "{visit_id}")
    @ApiResponse(responseCode = "200", description = "Update Visit",
            content = @Content(schema = @Schema(implementation = Visit.class)))
    public ResponseEntity<Visit> updateVisit(@RequestBody Visit visit) {
        return new ResponseEntity<Visit>(visitService.updateVisit(visit), HttpStatus.OK);
    }
}
