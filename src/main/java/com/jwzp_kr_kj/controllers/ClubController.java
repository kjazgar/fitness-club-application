package com.jwzp_kr_kj.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class ClubController {
    @GetMapping("/clubs")
    public String printClubs(){
        return "clubs";
    }

    @GetMapping("/clubs/{id}")
    public String printClubWithId(){
        return "clubs with id ";
    }
}
