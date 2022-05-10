package com.jwzp_kr_kj.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jwzp_kr_kj.models.records.ClubRecord;
import com.jwzp_kr_kj.models.records.EventRecord;
import com.jwzp_kr_kj.services.ClubService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


@RestController
public class ClubController {

    ClubService clubService;
    ObjectWriter ow = new ObjectMapper().findAndRegisterModules().registerModule(new JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false).writer().withDefaultPrettyPrinter();

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping("/clubs")
    public ResponseEntity<String> printClubs() throws JsonProcessingException {
        List<ClubRecord> allClubs = clubService.getAllClubs();
        String json = ow.writeValueAsString(allClubs);
        return ResponseEntity.ok(json);
    }

    @GetMapping("/clubs/{id}")
    public ResponseEntity<String> printClubWithId(@PathVariable int id) throws JsonProcessingException {
        String json = ow.writeValueAsString(clubService.getClub(id));
        return ResponseEntity.ok(json);
    }

    @GetMapping("/clubs/page")
    public Page<ClubRecord> getAll(Pageable p){
        return clubService.getPage(p);
    }

    @PostMapping(path = "/clubs")
    public ResponseEntity<HttpStatus> addClub(@RequestBody ClubRecord club) {
        clubService.addClub(club);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping(path = "/clubs/{id}")
    public ResponseEntity<Object> updateClub(@PathVariable int id, @RequestBody ClubRecord newClub) {
        Optional<ClubRecord> updatedClub = clubService.getClub(id);
        if (updatedClub.isPresent()) {
            return clubService.updateClub(id, newClub);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping(path = "/clubs/{id}")
    public ResponseEntity<Object> deleteClub(@PathVariable(value = "id") int id) {
        return clubService.deleteClub(id);
    }
}
