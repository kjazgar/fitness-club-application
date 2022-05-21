package com.jwzp_kr_kj.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.jwzp_kr_kj.models.data.CoachData;
import com.jwzp_kr_kj.models.records.CoachRecord;
import com.jwzp_kr_kj.services.CoachService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
public class CoachController {

    CoachService coachService;
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    public CoachController(CoachService coachService) {
        this.coachService = coachService;
    }

    @GetMapping("/coaches")
    public ResponseEntity<Object> printCoaches() throws JsonProcessingException {
        List<CoachRecord> allCoaches = coachService.getAllCoaches();
        String json = ow.writeValueAsString(allCoaches);
        return ResponseEntity.ok(json);
    }

    @GetMapping("/coaches/{id}")
    public ResponseEntity<String> printCoachWithId(@PathVariable int id) throws JsonProcessingException {
        String json = ow.writeValueAsString(coachService.getCoach(id));
        return ResponseEntity.ok(json);
    }

    @GetMapping("/coaches/page")
    public Page<CoachRecord> getAll(Pageable p){
        return coachService.getPage(p);
    }

    @PostMapping(path = "/coaches")
    public ResponseEntity<CoachRecord> addCoach(@RequestBody CoachData coach) {
        return coachService.addCoach(coach);
    }

    @PatchMapping(path = "/coaches/{id}")
    public ResponseEntity<Object> updateCoach(@PathVariable int id, @RequestBody CoachData newCoach) {
        return coachService.updateCoach(id, newCoach);
    }

    @DeleteMapping("/coaches/{id}")
    public ResponseEntity<Object> deleteCoach(@PathVariable(value = "id") int id) {
        return coachService.deleteCoach(id);
    }
}
