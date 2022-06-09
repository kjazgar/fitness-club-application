package com.jwzp_kr_kj.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.jwzp_kr_kj.models.DayOfTheWeek;
import com.jwzp_kr_kj.models.OpeningHours;
import com.jwzp_kr_kj.models.data.CoachData;
import com.jwzp_kr_kj.models.data.EventData;
import com.jwzp_kr_kj.models.records.ClubRecord;
import com.jwzp_kr_kj.models.records.CoachRecord;
import com.jwzp_kr_kj.models.records.EventRecord;
import com.jwzp_kr_kj.services.EventService;
import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Duration;
import java.time.LocalTime;
import java.util.*;
import java.util.Collections;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@ExtendWith(MockitoExtension.class)
public class EventControllerTest {
    private EventRecord event;
    private Map<DayOfTheWeek, OpeningHours> mapDays;
    private List<EventRecord> events;

    @Mock
    EventService eventService;

    @BeforeEach
    private void setup(){

        mapDays = new HashMap<DayOfTheWeek, OpeningHours>();
        mapDays.put(DayOfTheWeek.MONDAY, new OpeningHours(LocalTime.parse("08:00:00"), LocalTime.parse("20:00")));
        mapDays.put(DayOfTheWeek.TUESDAY, new OpeningHours(LocalTime.parse("08:00:00"), LocalTime.parse("20:00")));
        mapDays.put(DayOfTheWeek.WEDNESDAY, new OpeningHours(LocalTime.parse("08:00:00"), LocalTime.parse("20:00")));
        mapDays.put(DayOfTheWeek.THURSDAY, new OpeningHours(LocalTime.parse("08:00:00"), LocalTime.parse("20:00")));
        mapDays.put(DayOfTheWeek.FRIDAY, new OpeningHours(LocalTime.parse("08:00:00"), LocalTime.parse("20:00")));
        mapDays.put(DayOfTheWeek.SATURDAY, new OpeningHours(LocalTime.parse("08:00:00"), LocalTime.parse("20:00")));
        mapDays.put(DayOfTheWeek.SUNDAY, new OpeningHours(LocalTime.parse("08:00:00"), LocalTime.parse("20:00")));

        event = new EventRecord(1, "zumba", DayOfTheWeek.MONDAY, LocalTime.parse("10:00:00"), Duration.ofHours(1), 1, 1);

        events = List.of(
                new EventRecord(2, "zumba", DayOfTheWeek.MONDAY, LocalTime.parse("10:00:00"), Duration.ofHours(1), 1, 1),
                new EventRecord(3, "pilates", DayOfTheWeek.TUESDAY, LocalTime.parse("10:00:00"), Duration.ofHours(1), 1, 1),
                new EventRecord(4, "stretching", DayOfTheWeek.WEDNESDAY, LocalTime.parse("10:00:00"), Duration.ofHours(1), 1, 1),
                new EventRecord(5, "shaper", DayOfTheWeek.FRIDAY, LocalTime.parse("10:00:00"), Duration.ofHours(1), 1, 1)
        );

    }

    @Test
    public void printAllEventsEmptyTest() {
        var eventController = new EventController(eventService);
        var result = eventController.printEvents();
        var expected = ResponseEntity.ok(Collections.emptyList());
        assert result.equals(expected);
        Mockito.verify(eventService, Mockito.times(1)).getAllEvents();
    }

    @Test
    public void printAllEventsTest(){
        Mockito.when(eventService.getAllEvents()).thenReturn(events);
        var result = new EventController(eventService).printEvents();
        var expected = ResponseEntity.ok(events);
        assert result.equals(expected);
    }

    @Test
    public void printEventByIdTest(){
        var eventController = new EventController(eventService);
        Mockito.when(eventService.getEvent(Mockito.any(Integer.class))).thenReturn(Optional.of(event));
        var result = eventController.printEventWithId(1);
        assert result.equals(ResponseEntity.ok(Optional.of(event)));
    }

    @Test
    public void printAllEventsByTheClubTest(){
        Mockito.when(eventService.getEventsByClub(Mockito.any(Integer.class))).thenReturn(events);
        var eventController = new EventController(eventService);
        var result = eventController.printAllEventsByTheClub(1);
        assert result.equals(ResponseEntity.ok(events));
    }

    @Test
    public void updateEventTest(){
        EventData updatedEvent = new EventData("zumba", DayOfTheWeek.MONDAY, LocalTime.parse("11:00:00"), Duration.ofHours(1), 1, 1);
        Mockito.when(eventService.updateEvent(Mockito.any(Integer.class), Mockito.any(EventData.class))).thenReturn(ResponseEntity.ok(updatedEvent));
        Mockito.when(eventService.getEvent(Mockito.any(Integer.class))).thenReturn(Optional.of(event));
        var eventController = new EventController(eventService);
        var expected = ResponseEntity.ok(updatedEvent);
        var result = eventController.updateEvent(1, updatedEvent);
        assert result.equals(expected);
        Mockito.verify(eventService, Mockito.times(1)).updateEvent(1, updatedEvent);
    }
}
