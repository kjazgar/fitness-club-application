package com.jwzp_kr_kj.services;

import com.jwzp_kr_kj.models.records.ClubRecord;
import com.jwzp_kr_kj.models.records.CoachRecord;
import com.jwzp_kr_kj.models.records.EventRecord;
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

    public boolean checkEventConditions(EventRecord event){
        int clubId = event.getClubId();
        int coachId = event.getCoachId();
        Optional<ClubRecord> club = clubRepository.findById(clubId);
        Optional<CoachRecord> coach = coachRepository.findById(coachId);

        if(event.getDuration().toMinutes() > 1440){
            return false;
        }

        if(club.isEmpty() || coach.isEmpty()){
            return false;
        }

        return club.get().isWithinClubOpeningHours(event.getDayOfTheWeek(), event.getTime(), event.getDuration());
    }

    public ResponseEntity<Object> addEvent(EventRecord event){
        if(checkEventConditions(event)){
            eventRepository.save(event);

            return ResponseEntity.status(HttpStatus.OK).build();
        }

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    public ResponseEntity<Object> deleteEvent(int id) {
        Optional<EventRecord> event = getEvent(id);
        if (event.isPresent()) {
            eventRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<Object> updateEvent(int id, EventRecord newEvent){
        if(checkEventConditions(newEvent)){
            eventRepository.findById(id).map(event -> {
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

    public List<EventRecord> getAllEvents() {
        return eventRepository.findAll();
    }

    public Optional<EventRecord> getEvent(int id) {
        return eventRepository.findById(id);
    }

    public List<EventRecord> getEventsByCoach(int coachId){
        return eventRepository.findByCoachId(coachId);
    }

    public List<EventRecord> getEventsByClub(int clubId){
        return eventRepository.findByClubId(clubId);
    }
}
