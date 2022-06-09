package com.jwzp_kr_kj.controllers;

import com.jwzp_kr_kj.models.data.EventData;
import com.jwzp_kr_kj.models.records.EventRecord;
import com.jwzp_kr_kj.services.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RestController
@Component
public class EventController {

    EventService eventService;

    @Autowired
    public EventController(EventService eventService){
        this.eventService = eventService;
    }

    @Operation(summary = "show all events")
    @GetMapping("/v1/events")
    public ResponseEntity<?> printEvents(){
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @Operation(summary = "add event")
    @PostMapping (value = "/v1/events", consumes = "application/json")
    public ResponseEntity<Object> addEvent(@RequestBody EventRecord event){
        return eventService.addEvent(event);
    }

    @Operation(summary = "show events on a given page")
    @GetMapping("/v1/events/page")
    public Page<EventRecord> getAll(Pageable p){
        return eventService.getPage(p);
    }

    @Operation(summary = "show event with provided id")
    @GetMapping("/v1/events/{id}")
    public ResponseEntity<?> printEventWithId(@PathVariable int id){
        Optional<EventRecord> event = eventService.getEvent(id);
        if(event.isPresent()){
            return ResponseEntity.ok(event);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "show event for a specified coach by its id")
    @GetMapping(value = "/v1/events/bycoach", params = "coachId")
    public ResponseEntity<?> printAllEventsByTheCoach(@RequestParam("coachId") int coachId){
        List<EventRecord> events = eventService.getEventsByCoach(coachId);
        return ResponseEntity.ok(events);
    }

    @Operation(summary = "show event for a specified club by its id")
    @GetMapping(value = "/v1/events/byclub", params = "clubId")
    public ResponseEntity<?> printAllEventsByTheClub(@RequestParam("clubId") int clubId){
        List<EventRecord> events = eventService.getEventsByClub(clubId);
        return ResponseEntity.ok(events);
    }

    @Operation(summary = "update event")
    @PatchMapping(path = "/v1/events/{id}")
    public ResponseEntity<Object> updateEvent(@PathVariable int id, @RequestBody EventData newEvent) {
        Optional<EventRecord> updatedEvent = eventService.getEvent(id);
        if (updatedEvent.isPresent()) {
            return eventService.updateEvent(id, newEvent);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "delete event")
    @DeleteMapping("/events/{id}")
    public ResponseEntity<Object> deleteEvent(@PathVariable(value = "id") int id) {
        return eventService.deleteEvent(id);
    }
}
