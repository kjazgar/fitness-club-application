package com.jwzp_kr_kj.controllers;

import com.jwzp_kr_kj.services.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
        return ResponseEntity.ok("clubs");
    }

    @GetMapping("/clubs/{id}")
    public String printClubWithId(){
        return "clubs with id ";
    }
}
