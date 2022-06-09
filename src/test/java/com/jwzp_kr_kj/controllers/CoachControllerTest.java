package com.jwzp_kr_kj.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.jwzp_kr_kj.models.data.CoachData;
import com.jwzp_kr_kj.models.records.CoachRecord;
import com.jwzp_kr_kj.services.CoachService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CoachControllerTest {
    private List<CoachRecord> listOfCoaches;
    private CoachRecord coach;

    @Mock
    CoachService coachService;

    @BeforeEach
    private void setup(){

        coach = new CoachRecord(1,"Camille", "Claudienne", 1950);
        listOfCoaches = List.of(
                new CoachRecord(1,"Ygrek", "Iksinski", 1990),
                new CoachRecord(2,"Ktos", "Fajny", 1993)
        );
    }

    @Test
    public void getAllCoachesEmptyTest(){
        Mockito.when(coachService.getAllCoaches()).thenReturn(Collections.emptyList());
        var result = new CoachController(coachService).printCoaches();
        var expected = ResponseEntity.ok(Collections.emptyList());
        assert result.equals(expected);
        Mockito.verify(coachService, Mockito.times(1)).getAllCoaches();
    }

    @Test
    public void getAllCoachesTest(){
        List<CoachRecord> coaches = List.of(
                new CoachRecord("Ygrek", "Iksinski", 1990),
                new CoachRecord("Ktos", "Fajny", 1993)
        );
        Mockito.when(coachService.getAllCoaches()).thenReturn(coaches);
        var result = new CoachController(coachService).printCoaches();
        var expected = ResponseEntity.ok(coaches);
        assert result.equals(expected);
    }

    @Test
    public void getCoachByIdTest() throws JsonProcessingException {
        Mockito.when(coachService.getCoach(Mockito.any(Integer.class))).thenReturn(Optional.of(coach));
        var coachController = new CoachController(coachService);
        var result = coachController.printCoachWithId(1);
        assert result.equals(ResponseEntity.ok(Optional.of(coach)));
    }

    @Test
    public void updateCoachTest(){
        CoachData updatedCoach = new CoachData("Camille", "Claudienne", 1944);
        Mockito.when(coachService.updateCoach(Mockito.any(Integer.class), Mockito.any(CoachData.class))).thenReturn(ResponseEntity.ok(updatedCoach));
        Mockito.when(coachService.getCoach(Mockito.any(Integer.class))).thenReturn(Optional.of(coach));
        var coachController = new CoachController(coachService);
        var expected = ResponseEntity.ok(updatedCoach);
        var result = coachController.updateCoach(1, updatedCoach);
        assert result.equals(expected);
        Mockito.verify(coachService, Mockito.times(1)).updateCoach(1, updatedCoach);
    }
}
