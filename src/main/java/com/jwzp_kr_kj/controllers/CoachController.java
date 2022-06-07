package com.jwzp_kr_kj.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.jwzp_kr_kj.models.data.CoachData;
import com.jwzp_kr_kj.models.records.CoachRecord;
import com.jwzp_kr_kj.services.CoachService;
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

    public CoachController(CoachService coachService) {
        this.coachService = coachService;
    }

    @GetMapping("/v1/coaches")
    public ResponseEntity<Object> printCoaches() throws JsonProcessingException {
        List<CoachRecord> allCoaches = coachService.getAllCoaches();
        String json = ow.writeValueAsString(allCoaches);
        return ResponseEntity.ok(json);
    }

    @GetMapping("/v1/coaches/{id}")
    public ResponseEntity<?> printCoachWithId(@PathVariable int id){
        Optional<CoachRecord> coach = coachService.getCoach(id);
        if(coach.isPresent()){
            return ResponseEntity.ok(coach);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/v1/coaches/page")
    public Page<CoachRecord> getAll(Pageable p){
        return coachService.getPage(p);
    }

    @PostMapping(path = "/v1/coaches")
    public ResponseEntity<CoachRecord> addCoach(@RequestBody CoachData coach) {
        return coachService.addCoach(coach);
    }

    @PatchMapping(path = "/v1/coaches/{id}")
    public ResponseEntity<Object> updateCoach(@PathVariable int id, @RequestBody CoachData newCoach) {
        Optional<CoachRecord> updatedCoach = coachService.getCoach(id);
        if (updatedCoach.isPresent()) {
            return coachService.updateCoach(id, newCoach);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/v1/coaches/{id}")
    public ResponseEntity<Object> deleteCoach(@PathVariable(value = "id") int id) {
        return coachService.deleteCoach(id);
    }
}
