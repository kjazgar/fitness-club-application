package com.jwzp_kr_kj.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class EventController {
    @GetMapping("/events")
    public String printEvents(){
        return "events";
    }

    @GetMapping("/events/{id}")
    public String printEventWithId(){
        return "events with id ";
    }

    @GetMapping("/events?coachId={id}")
    public String printAllEventsByTheCoach(){
        return "all events by the coach ";
    }

    @GetMapping("/events?clubId={id}")
    public String printAllEventsByTheClub(){
        return "all events by the club";
    }
}
