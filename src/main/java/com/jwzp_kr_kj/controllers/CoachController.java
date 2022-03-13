package com.jwzp_kr_kj.controllers;

import com.jwzp_kr_kj.core.Coach;
import com.jwzp_kr_kj.services.CoachService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CoachController {

    CoachService coachService;

    public CoachController() {
        coachService = new CoachService();
    }

    @GetMapping("/coaches")
    public ResponseEntity<?> printCoaches(){
        return ResponseEntity.ok(coachService.getAllCoaches().toString());
    }

    @GetMapping("/coaches/{id}")
    public String printCoachWithId(@PathVariable int id){
        return coachService.getCoach(id);
    }

    @PostMapping(path = "/coaches")
    public void addCoach(@RequestBody Coach coach) {
        coachService.addCoach(coach);
    }

    @DeleteMapping("/coaches/{id}")
    public void deleteCoach(@PathVariable(value = "id") int id){
        coachService.deleteCoach(id);
    }
}
