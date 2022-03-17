package com.jwzp_kr_kj.controllers;

import com.jwzp_kr_kj.core.Event;
import com.jwzp_kr_kj.services.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
public class EventController {

    EventService eventService;

    @Autowired
    public EventController(EventService eventService){
        this.eventService = eventService;
    }

    @PostMapping (value = "/events", consumes = "application/json")
    public void addEvent(@RequestBody Event event){
        eventService.addEvent(event);
    }

    @GetMapping("/events")
    public ResponseEntity<?> printEvents(){
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<?> printEventWithId(@PathVariable int id){
        return ResponseEntity.ok(eventService.getEvent(id));
    }

    @GetMapping(value = "/events", params = "coachId")
    public ResponseEntity<?> printAllEventsByTheCoach(@RequestParam("coachId") int coachId){
        List<Event> events = eventService.getEventsByCoach(coachId);
        return ResponseEntity.ok(events);
    }

    @GetMapping(value = "/events", params = "clubId")
    public ResponseEntity<?> printAllEventsByTheClub(@RequestParam("clubId") int clubId){
        List<Event> events = eventService.getEventsByClub(clubId);
        return ResponseEntity.ok(events);
    }
}
