package com.jwzp_kr_kj.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.jwzp_kr_kj.models.records.ClubRecord;
import com.jwzp_kr_kj.services.ClubService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@ExtendWith(MockitoExtension.class)
public class ClubControllerTest {

    @Mock
    ClubService clubService;
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    @Test
    public void getAllClubsEmptyTest() throws JsonProcessingException {
        List<ClubRecord> emptyClubs = Collections.emptyList();
        Mockito.when(clubService.getAllClubs()).thenReturn(emptyClubs);
        var expected = ResponseEntity.ok((ow.writeValueAsString(CollectionModel.empty().add(linkTo(ClubController.class).withSelfRel()))));
        var clubs = new ClubController(clubService);
        var result = clubs.printClubs();
        assert result.equals(expected);
    }
}
