package com.jwzp_kr_kj.services;

import com.jwzp_kr_kj.core.Club;
import com.jwzp_kr_kj.core.Coach;
import com.jwzp_kr_kj.core.Event;
import com.jwzp_kr_kj.repos.ClubRepository;
import com.jwzp_kr_kj.repos.CoachRepository;
import com.jwzp_kr_kj.repos.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    public EventRepository eventRepository;
    public ClubRepository clubRepository;
    public CoachRepository coachRepository;

    @Autowired
    public EventService(EventRepository eventRepository, ClubRepository clubRepository, CoachRepository coachRepository){
        this.eventRepository = eventRepository;
        this.clubRepository = clubRepository;
        this.coachRepository = coachRepository;
    }

    public boolean checkEventConditions(Event event){
        int clubId = event.getClubId();
        int coachId = event.getCoachId();
        Optional<Club> club = clubRepository.findById(clubId);
        Optional<Coach> coach = coachRepository.findById(coachId);

        if(event.getDuration().toMinutes() > 1440){
            return false;
        }

        if(club.isEmpty() || coach.isEmpty()){
            return false;
        }

        return club.get().isWithinClubOpeningHours(event.getDayOfTheWeek(), event.getTime(), event.getDuration());
    }

    public ResponseEntity<Object> addEvent(Event event){
        if(checkEventConditions(event)){
            eventRepository.save(event);

            return ResponseEntity.status(HttpStatus.OK).build();
        }

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    public Optional<Event> findEvent(int id) {
        return eventRepository.findById(id);
    }

    public ResponseEntity<Object> deleteEvent(int id) {
        Optional<Event> event = findEvent(id);
        if (event.isPresent()) {
            eventRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<Object> updateCoach(int id, Event newEvent){
        if(checkEventConditions(newEvent)){
            Optional<Object> updatedEvent = eventRepository.findById(id).map(event -> {
                event.setTitle(newEvent.getTitle());
                event.setDayOfTheWeek(newEvent.getDayOfTheWeek());
                event.setTime(newEvent.getTime());
                event.setDuration(newEvent.getDuration());
                event.setCoachId(newEvent.getCoachId());
                event.setClubId(newEvent.getClubId());
                return eventRepository.save(event);
            });
            return ResponseEntity.status(HttpStatus.OK).build();
        }

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Optional<Event> getEvent(int id) {
        return eventRepository.findById(id);
    }

    public List<Event> getEventsByCoach(int coachId){
        return eventRepository.findByCoachId(coachId);
    }

    public List<Event> getEventsByClub(int clubId){
        return eventRepository.findByClubId(clubId);
    }
}
