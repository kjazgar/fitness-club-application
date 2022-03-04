package com.jwzp_kr_kj.controllers;

import com.jwzp_kr_kj.services.EventService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class EventController {

    EventService eventService;

    @Autowired
    public EventController(){
        eventService = new EventService();
    }

    @GetMapping("/events")
    public ResponseEntity<String> printEvents(){
        return ResponseEntity.ok("events");
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<String> printEventWithId(int id){
        return ResponseEntity.ok(eventService.getEvent(id));
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
