package com.jwzp_kr_kj.services;

import com.jwzp_kr_kj.Logs;
import com.jwzp_kr_kj.models.data.EventData;
import com.jwzp_kr_kj.models.records.ClubRecord;
import com.jwzp_kr_kj.models.records.CoachRecord;
import com.jwzp_kr_kj.models.records.EventRecord;
import com.jwzp_kr_kj.repos.ClubRepository;
import com.jwzp_kr_kj.repos.CoachRepository;
import com.jwzp_kr_kj.repos.EventRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final Logger logger = LogManager.getLogger(EventService.class);
    public EventRepository eventRepository;
    public ClubRepository clubRepository;
    public CoachRepository coachRepository;

    @Autowired
    public EventService(EventRepository eventRepository, ClubRepository clubRepository, CoachRepository coachRepository){
        this.eventRepository = eventRepository;
        this.clubRepository = clubRepository;
        this.coachRepository = coachRepository;
    }

    public ResponseEntity<Object> checkEventConditions(ClubRecord club, CoachRecord coach, EventData event){
        if(checkIfCoachIsAlreadyAssign(coach.getId(), event)){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("coach is already assigned");
        }

        if(event.getDuration().toMinutes() > 1440){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("event cannot be longer than 24 hours");
        }

        if(!club.isWithinClubOpeningHours(event.getDayOfTheWeek(), event.getTime(), event.getDuration())){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("event has to be within club's opening hours");

        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public boolean checkIfHoursOverlap(EventData event1, EventData event2){
        if(event1.getDayOfTheWeek().equals(event2.getDayOfTheWeek())){
            if(event1.getTime().equals(event2.getTime()))
                return true;

            if(event1.getTime().compareTo(event2.getTime()) < 0
                    && event1.getTime().plus(event1.getDuration()).compareTo(event2.getTime()) > 0){
                return true;
            }

            return event1.getTime().compareTo(event2.getTime()) > 0
                    && event2.getTime().plus(event2.getDuration()).compareTo(event1.getTime()) > 0;

        }

        return false;
    }

    public boolean checkIfCoachIsAlreadyAssign(int coachId, EventData eventData){
        List<EventRecord> eventsPerCoach = eventRepository.findByCoachId(coachId);

        for(var eventPerCoach : eventsPerCoach){
            EventData eventDataPerCoach = new EventData(eventPerCoach);
            if(checkIfHoursOverlap(eventData, eventDataPerCoach)){
                return true;
            }
        }

        return false;
    }

    public ResponseEntity<Object> addEvent(EventRecord event){
        int clubId = event.getClubId();
        int coachId = event.getCoachId();
        Optional<ClubRecord> club = clubRepository.findById(clubId);
        Optional<CoachRecord> coach = coachRepository.findById(coachId);

        if(club.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("provided club does not exist");
        }

        if(coach.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("provided coach does not exist");
        }

        EventData eventData = new EventData(event);

        if(checkEventConditions(club.get(), coach.get(), eventData).getStatusCode().equals(HttpStatus.OK)){
            eventRepository.save(event);
            logger.info(Logs.logAdded(event, event.getId()));
            return ResponseEntity.status(HttpStatus.OK).build();
        }

        logger.error(Logs.logNotAccepted(EventRecord.class));
        return checkEventConditions(club.get(), coach.get(), eventData);
    }

    public ResponseEntity<Object> deleteEvent(int id) {
        Optional<EventRecord> event = getEvent(id);
        if (event.isPresent()) {
            logger.info(Logs.logDeleted(EventRecord.class, id));
            eventRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }

        logger.error(Logs.logNotFound(EventRecord.class, id));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    public ResponseEntity<Object> updateEvent(int id, EventData newEvent){
        int clubId = newEvent.getClubId();
        int coachId = newEvent.getCoachId();
        Optional<ClubRecord> club = clubRepository.findById(clubId);
        Optional<CoachRecord> coach = coachRepository.findById(coachId);

        if(club.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("provided club does not exist");
        }

        if(coach.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("provided coach does not exist");
        }

        if(checkEventConditions(club.get(), coach.get(), newEvent).getStatusCode().equals(HttpStatus.OK)){
            eventRepository.findById(id).map(event -> {
                event.setTitle(newEvent.getTitle());
                event.setDayOfTheWeek(newEvent.getDayOfTheWeek());
                event.setTime(newEvent.getTime());
                event.setDuration(newEvent.getDuration());
                event.setCoachId(newEvent.getCoachId());
                event.setClubId(newEvent.getClubId());
                return eventRepository.save(event);
            });
            logger.info(Logs.logUpdated(EventRecord.class, id));
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        logger.error(Logs.logNotAccepted(ClubRecord.class));
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

    public Page<EventRecord> getPage(Pageable p){
        return eventRepository.findAll(p);
    }
}
