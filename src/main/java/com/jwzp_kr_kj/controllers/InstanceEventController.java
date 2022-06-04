package com.jwzp_kr_kj.controllers;

import com.jwzp_kr_kj.models.records.EventRecord;
import com.jwzp_kr_kj.models.records.InstanceEventRecord;
import com.jwzp_kr_kj.services.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@Component
public class InstanceEventController {
    InstanceEventService instanceEventService;

    @Autowired
    public InstanceEventController(InstanceEventService instanceEventService){
        this.instanceEventService = instanceEventService;
    }

    @GetMapping("/available_events")
    public ResponseEntity<?> printEvents(){
        return ResponseEntity.ok(instanceEventService.getAllInstanceEvents());
    }

    @PostMapping("/register/{id}")
    public ResponseEntity<?> registerForEvent(@PathVariable int id){
        return instanceEventService.registerForEvent(id);
    }

    @PostMapping("/cancel/{id}")
    public ResponseEntity<?> cancelEvent(@PathVariable int id){
        return instanceEventService.cancelEvent(id);
    }

    @PostMapping("/postpone/{id}")
    public ResponseEntity<?> cancelEvent(@PathVariable int id, LocalDateTime date){
        return instanceEventService.postponeEvent(id, date);
    }

    @GetMapping("/find")
    public ResponseEntity<?> cancelEvent(@RequestParam LocalDateTime date, @RequestParam int clubId){
        return ResponseEntity.ok(instanceEventService.findEventByDateAndClub(date, clubId));
    }

//    @PostMapping (value = "/events/assign", consumes = "application/json")
//    public ResponseEntity<Object> assignForEvent(@RequestBody EventRecord event){
//        InstanceEventRecord instanceEventRecord = instanceEventService.getInstanceEvent(event.getId());
//
//    }
}
