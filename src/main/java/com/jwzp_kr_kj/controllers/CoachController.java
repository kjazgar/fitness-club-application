package com.jwzp_kr_kj.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class CoachController {
    @GetMapping("/coach")
    public String printCoaches(){
        return "coaches";
    }

    @GetMapping("/coaches/{id}")
    public String printCoachWithId(){
        return "coaches with id ";
    }
}
