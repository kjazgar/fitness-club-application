package com.jwzp_kr_kj.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jwzp_kr_kj.models.data.ClubData;
import com.jwzp_kr_kj.models.records.ClubRecord;
import com.jwzp_kr_kj.services.ClubService;
import com.jwzp_kr_kj.services.InstanceEventService;
import com.jwzp_kr_kj.services.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.Link;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@RestController
@Component
public class PersonController {

    PersonService personService;

    @Autowired
    public PersonController(PersonService personService){
        this.personService = personService;
    }

    @Operation(summary = "show all people")
    @GetMapping("/v1/persons")
    public ResponseEntity<?> printPersons(){
        return ResponseEntity.ok(personService.getAllPeople());
    }

    @Operation(summary = "show all people assign to event")
    @GetMapping("/v1/persons/{id}")
    public ResponseEntity<?> printPersonsAssignToEvent(@PathVariable int id){
        return ResponseEntity.ok(personService.getAllPeopleOnEvent(id));
    }
}