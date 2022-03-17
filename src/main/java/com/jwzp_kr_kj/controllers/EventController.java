package com.jwzp_kr_kj.controllers;

import com.jwzp_kr_kj.core.Event;
import com.jwzp_kr_kj.services.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

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
        Optional<Event> event = eventService.getEvent(id);
        if(event.isPresent()){
            return ResponseEntity.ok(eventService.getEvent(id));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
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

    @PatchMapping(path = "/events/{id}")
    public ResponseEntity<Object> updateEvent(@PathVariable int id, @RequestBody Event newEvent) {
        Optional<Event> updatedEvent = eventService.findEvent(id);
        if (updatedEvent.isPresent()) {
            return eventService.updateCoach(id, newEvent);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/events/{id}")
    public ResponseEntity<Object> deleteCoach(@PathVariable(value = "id") int id) {
        return eventService.deleteEvent(id);
    }
}
