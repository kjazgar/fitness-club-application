package com.jwzp_kr_kj.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jwzp_kr_kj.models.data.ClubData;
import com.jwzp_kr_kj.models.records.ClubRecord;
import com.jwzp_kr_kj.services.ClubService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.Link;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@RestController
public class ClubController {

    ClubService clubService;
    ObjectWriter ow = new ObjectMapper().findAndRegisterModules().registerModule(new JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false).writer().withDefaultPrettyPrinter();

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping("/v1/clubs")
    public ResponseEntity<String> printClubs() throws JsonProcessingException {
        List<ClubRecord> allClubs = clubService.getAllClubs();
        for(ClubRecord club : allClubs){
            club.add(linkTo(ClubController.class).slash(club.getId()).withSelfRel());
        }
        Link link = linkTo(ClubController.class).withSelfRel();
        CollectionModel<ClubRecord> result = CollectionModel.of(allClubs,link);
        String json = ow.writeValueAsString(result);
        return ResponseEntity.ok(json);
    }

    @GetMapping("/v1/clubs/{id}")
    public ResponseEntity<String> printClubWithId(@PathVariable int id) throws JsonProcessingException {
        String json = ow.writeValueAsString(clubService.getClub(id));
        return ResponseEntity.ok(json);
    }

    @GetMapping("/v1/clubs/page")
    public Page<ClubRecord> getAll(Pageable p){
        return clubService.getPage(p);
    }

    @PostMapping(path = "/v1/clubs")
    public ResponseEntity<ClubRecord> addClub(@RequestBody ClubData club) {
        return clubService.addClub(club);
    }

    @PatchMapping(path = "/v1/clubs/{id}")
    public ResponseEntity<Object> updateClub(@PathVariable int id, @RequestBody ClubData newClub) {
        return clubService.updateClub(id, newClub);
    }

    @DeleteMapping(path = "/v1/clubs/{id}")
    public ResponseEntity<ClubRecord> deleteClub(@PathVariable(value = "id") int id) {
        return clubService.deleteClub(id);
    }
}
