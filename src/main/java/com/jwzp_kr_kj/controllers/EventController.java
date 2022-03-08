package com.jwzp_kr_kj.controllers;

import com.jwzp_kr_kj.core.Event;
import com.jwzp_kr_kj.services.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping (value = "/events", consumes = "application/json")
    public void addEvent(@RequestBody Event event){
        eventService.listOfEvents.add(event);
    }

    @GetMapping("/events")
    public ResponseEntity<String> printEvents(){
        return ResponseEntity.ok(eventService.listOfEvents.toString());
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<String> printEventWithId(int id){
        return ResponseEntity.ok(eventService.getEventById(id).toString());
    }

    @GetMapping("/events?coachId={id}")
    public ResponseEntity<String> printAllEventsByTheCoach(int id){
        return ResponseEntity.ok(eventService.getEventsByCoachId(id).toString());
    }

    @GetMapping("/events?clubId={id}")
    public ResponseEntity<String> printAllEventsByTheClub(int id){
        return ResponseEntity.ok(eventService.getEventsByClubId(id).toString());
    }
}
