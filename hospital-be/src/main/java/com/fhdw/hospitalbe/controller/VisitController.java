package com.fhdw.hospitalbe.controller;

import com.fhdw.hospitalbe.model.Visit;
import com.fhdw.hospitalbe.service.VisitService;
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
    public ResponseEntity<Visit> getVisit(@PathVariable("visit_id") long visit_id) {
        return new ResponseEntity<Visit>(visitService.getVisit(visit_id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Visit> createVisit(@RequestBody Visit visit) {
        return new ResponseEntity<Visit>(visitService.createVisit(visit), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{visit_id}")
    public ResponseEntity<Void> deleteVisit(@PathVariable("visit_id") long visit_id) {
        visitService.deleteVisit(visit_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
