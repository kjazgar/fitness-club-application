package com.jwzp_kr_kj.controllers;

import com.jwzp_kr_kj.models.data.InstanceEventData;
import com.jwzp_kr_kj.models.records.EventRecord;
import com.jwzp_kr_kj.models.records.InstanceEventRecord;
import com.jwzp_kr_kj.services.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
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

    @Operation(summary = "show available events")
    @GetMapping("/v1/available_events")
    public ResponseEntity<?> printEvents(){
        return ResponseEntity.ok(instanceEventService.getAllInstanceEvents());
    }

    @Operation(summary = "register for a given event")
    @PostMapping("/v1/register/{id}")
    public ResponseEntity<?> registerForEvent(@PathVariable int id){
        return instanceEventService.registerForEvent(id);
    }

    @Operation(summary = "cancel event")
    @PostMapping("/v1/cancel/{id}")
    public ResponseEntity<?> cancelEvent(@PathVariable int id){
        return instanceEventService.cancelEvent(id);
    }

    @Operation(summary = "postpone event")
    @PostMapping(value = "/v1/postpone/{id}", consumes = "application/json")
    public ResponseEntity<?> postponeEvent(@PathVariable int id, @RequestParam("date") @DateTimeFormat(pattern = "yyyy-dd-MM HH:mm:ss") LocalDateTime date){
        return instanceEventService.postponeEvent(id, date);
    }

    @Operation(summary = "find event")
    @GetMapping("/v1/find")
    public ResponseEntity<?> findEvent(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-dd-MM HH:mm:ss")  LocalDateTime date, @RequestParam("clubId") int clubId){
        return ResponseEntity.ok(instanceEventService.findEventByDateAndClub(date, clubId));
    }
}
