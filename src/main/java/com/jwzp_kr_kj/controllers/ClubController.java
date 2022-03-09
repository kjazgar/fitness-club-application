package com.jwzp_kr_kj.controllers;

import com.jwzp_kr_kj.services.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class ClubController {

    ClubService clubService;

    @Autowired
    public ClubController() {
        clubService = new ClubService();
    }

    @GetMapping("/clubs")
    public ResponseEntity<String> printClubs(){
        return ResponseEntity.ok(clubService.getAllClubs().get(0).name);
    }

    @GetMapping("/clubs/{id}")
    public String printClubWithId(@PathVariable int id){
        return clubService.getClub(id);
    }
}
