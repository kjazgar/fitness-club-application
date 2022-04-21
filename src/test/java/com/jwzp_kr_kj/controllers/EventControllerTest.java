package com.jwzp_kr_kj.controllers;

import com.jwzp_kr_kj.services.EventService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Collections;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@ExtendWith(MockitoExtension.class)
public class EventControllerTest {

    @Mock
    EventService eventService;

    @Test
    public void getAllEventsEmptyTest() {
        var events = new EventController(eventService);
        var result = events.printEvents();
        assert result.getBody().equals(Collections.emptyList());
        assert result.getStatusCode().equals(HttpStatus.OK);
        Mockito.verify(eventService, Mockito.times(1)).getAllEvents();
    }

}
