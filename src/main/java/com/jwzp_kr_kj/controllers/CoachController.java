package com.jwzp_kr_kj.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.jwzp_kr_kj.models.data.CoachData;
import com.jwzp_kr_kj.models.records.CoachRecord;
import com.jwzp_kr_kj.models.records.EventRecord;
import com.jwzp_kr_kj.services.CoachService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class CoachController {

    CoachService coachService;
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    @Autowired
    public CoachController(CoachService coachService) {
        this.coachService = coachService;
    }

    @Operation(summary = "show all coaches")
    @GetMapping("/v1/coaches")
    public ResponseEntity<?> printCoaches(){
        return ResponseEntity.ok(coachService.getAllCoaches());
    }

    @Operation(summary = "get coach with provided id")
    @GetMapping("/v1/coaches/{id}")
    public ResponseEntity<?> printCoachWithId(@PathVariable int id) throws JsonProcessingException {
        Optional<CoachRecord> coach = coachService.getCoach(id);
        if(coach.isPresent()){
            return ResponseEntity.ok(coach);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "show coaches on a given page")
    @GetMapping("/v1/coaches/page")
    public Page<CoachRecord> getAll(Pageable p){
        return coachService.getPage(p);
    }

    @Operation(summary = "add coach")
    @PostMapping(path = "/v1/coaches")
    public ResponseEntity<CoachRecord> addCoach(@RequestBody CoachData coach) {
        return coachService.addCoach(coach);
    }

    @Operation(summary = "update coach")
    @PatchMapping(path = "/v1/coaches/{id}")
    public ResponseEntity<Object> updateCoach(@PathVariable int id, @RequestBody CoachData newCoach) {
        Optional<CoachRecord> updatedCoach = coachService.getCoach(id);
        if (updatedCoach.isPresent()) {
            return coachService.updateCoach(id, newCoach);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "delete coach")
    @DeleteMapping("/v1/coaches/{id}")
    public ResponseEntity<Object> deleteCoach(@PathVariable(value = "id") int id) {
        return coachService.deleteCoach(id);
    }
}
