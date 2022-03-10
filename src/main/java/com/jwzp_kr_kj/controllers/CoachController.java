package com.jwzp_kr_kj.controllers;

import com.jwzp_kr_kj.core.Coach;
import com.jwzp_kr_kj.services.CoachService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CoachController {

    CoachService coachService;

    public CoachController() {
        coachService = new CoachService();
    }

    @GetMapping("/coach")
    public ResponseEntity<?> printCoaches(){
        return ResponseEntity.ok(coachService.getAllCoaches().toString());
    }

    @GetMapping("/coaches/{id}")
    public String printCoachWithId(@PathVariable int id){
        return coachService.getCoach(id);
    }

    @PostMapping(path = "/coach")
    public void addCoach(@RequestBody Coach coach) {
        coachService.addCoach(coach);
    }
}
