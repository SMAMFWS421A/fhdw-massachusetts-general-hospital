package com.fhdw.hospitalbe.controller;

import com.fhdw.hospitalbe.model.Visit;
import com.fhdw.hospitalbe.repository.decorator.VisitRepositoryDecorator;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/visit")
@CrossOrigin("http://localhost:8080")

public class VisitController {

    private final VisitRepositoryDecorator visitRepositoryDecorator;

    public VisitController(VisitRepositoryDecorator visitRepositoryDecorator) {
        this.visitRepositoryDecorator = visitRepositoryDecorator;
    }

    @GetMapping(path = "{visit_id}")
    @ApiResponse(responseCode = "200", description = "Found Visit",
            content = @Content(schema = @Schema(implementation = Visit.class)))
    public ResponseEntity<Visit> findVisit(@PathVariable("visit_id") long visit_id) {
        Visit visDb = visitRepositoryDecorator.findVisit(visit_id);
        if (visDb == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(visDb, HttpStatus.OK);
    }

    @GetMapping()
    @ApiResponse(responseCode = "200", description = "Found Visits",
            content = @Content(schema = @Schema(implementation = List.class)))
    public ResponseEntity<List<Visit>> findAllVisit() {
        List<Visit> visits = visitRepositoryDecorator.findAllVisits();
        if (visits == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(visits, HttpStatus.OK);
    }


    @PostMapping
    @ApiResponse(responseCode = "201", description = "Registered Visit",
            content = @Content(schema = @Schema(implementation = Visit.class)))
    public ResponseEntity<Visit> registerVisit(@RequestBody Visit visit) {
        Visit visDb = visitRepositoryDecorator.saveVisit(visit);
        if (visDb == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(visDb, HttpStatus.CREATED);
    }


    @DeleteMapping(path = "{visit_id}")
    @ApiResponse(responseCode = "204", description = "Canceled Visit",
            content = @Content(schema = @Schema(implementation = Visit.class)))
    public ResponseEntity<Void> cancelVisit(@PathVariable("visit_id") long visit_id) {
        visitRepositoryDecorator.deleteVisit(visit_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping()
    @ApiResponse(responseCode = "200", description = "Updated Visit",
            content = @Content(schema = @Schema(implementation = Visit.class)))
    public ResponseEntity<Visit> updateVisit(@RequestBody Visit visit) {
        Visit visDb = visitRepositoryDecorator.updateVisit(visit);
        if (visDb == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(visDb, HttpStatus.OK);
    }
}
