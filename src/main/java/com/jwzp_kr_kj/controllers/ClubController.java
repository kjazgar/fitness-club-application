package com.jwzp_kr_kj.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.jwzp_kr_kj.core.Club;
import com.jwzp_kr_kj.services.ClubService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class ClubController {

    ClubService clubService;
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping("/clubs")
    public ResponseEntity<String> printClubs() throws JsonProcessingException {
        List<Club> allClubs = clubService.getAllClubs();
        String json = ow.writeValueAsString(allClubs);
        return ResponseEntity.ok(json);
    }

    @GetMapping("/clubs/{id}")
    public ResponseEntity<String> printClubWithId(@PathVariable int id) throws JsonProcessingException {
        String json = ow.writeValueAsString(clubService.getClub(id));
        return ResponseEntity.ok(json);
    }

    @PostMapping(path = "/clubs")
    public ResponseEntity<HttpStatus> addClub(@RequestBody Club club) {
        clubService.addClub(club);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping(path = "/clubs/{id}")
    public ResponseEntity<Object> updateClub(@PathVariable int id, @RequestBody Club newClub) {
        Optional<Club> updatedClub = clubService.getClub(id);
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
