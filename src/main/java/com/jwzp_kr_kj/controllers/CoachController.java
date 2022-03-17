package com.jwzp_kr_kj.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.jwzp_kr_kj.core.Coach;
import com.jwzp_kr_kj.services.CoachService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class CoachController {

    CoachService coachService;
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    public CoachController(CoachService coachService) {
        this.coachService = coachService;
    }

    @GetMapping("/coaches")
    public ResponseEntity<?> printCoaches() throws JsonProcessingException {
        List<Coach> allCoaches = coachService.getAllCoaches();
        String json = ow.writeValueAsString(allCoaches);
        return ResponseEntity.ok(json);
    }

    @GetMapping("/coaches/{id}")
    public ResponseEntity<String> printCoachWithId(@PathVariable int id) throws JsonProcessingException {
        String json = ow.writeValueAsString(coachService.getCoach(id));
        return ResponseEntity.ok(json);
    }

    @PostMapping(path = "/coaches")
    public ResponseEntity<HttpStatus> addCoach(@RequestBody Coach coach) {
        coachService.addCoach(coach);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/coaches/{id}")
    public ResponseEntity<Object> deleteCoach(@PathVariable(value = "id") int id){
        return coachService.deleteCoach(id);
    }
}
