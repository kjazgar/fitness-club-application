package com.jwzp_kr_kj.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.jwzp_kr_kj.services.CoachService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.Collections;

public class CoachControllerTest {

    @Mock
    CoachService coachService;
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    @Test
    public void getAllCoachesEmptyTest() throws JsonProcessingException {
        var coaches = new CoachController(coachService);
        var result = coaches.printCoaches();
        var expected = ResponseEntity.ok(ow.writeValueAsString(Collections.emptyList()));
        assert result.equals(expected);
    }
}
