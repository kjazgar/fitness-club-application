package com.jwzp_kr_kj.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.jwzp_kr_kj.services.ClubService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

public class ClubControllerTest {

    @Mock
    ClubService clubService;
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    @Test
    public void getAllClubsEmptyTest() throws JsonProcessingException {
        var clubs = new ClubController(clubService);
        var result = clubs.printClubs();
        var expected = ResponseEntity.ok(ow.writeValueAsString(Collections.emptyList()));
        assert result.equals(expected);
    }
}
