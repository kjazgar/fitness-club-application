package com.jwzp_kr_kj.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.jwzp_kr_kj.models.records.CoachRecord;
import com.jwzp_kr_kj.services.CoachService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CoachControllerTest {

    @Mock
    CoachService coachService;
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    @Test
    public void getAllCoachesEmptyTest() throws JsonProcessingException {
        Mockito.when(coachService.getAllCoaches()).thenReturn(Collections.emptyList());
        var result = new CoachController(coachService).printCoaches();
        var expected = ResponseEntity.ok(ow.writeValueAsString(Collections.emptyList()));
        assert result.equals(expected);
        Mockito.verify(coachService, Mockito.times(1)).getAllCoaches();
    }

    @Test
    public void getAllCoachesTest() throws JsonProcessingException {
        List<CoachRecord> coaches = List.of(
                new CoachRecord(0, "Ygrek", "Iksinski", 1990),
                new CoachRecord(1, "Ktos", "Fajny", 1993)
        );
        Mockito.when(coachService.getAllCoaches()).thenReturn(coaches);
        var result = new CoachController(coachService).printCoaches();
        var expected = ResponseEntity.ok(ow.writeValueAsString(coaches));
        assert result.equals(expected);
    }
}
