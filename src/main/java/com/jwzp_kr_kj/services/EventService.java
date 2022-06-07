package com.jwzp_kr_kj.services;

import com.jwzp_kr_kj.models.DayOfTheWeek;
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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
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

    public boolean checkEventConditions(EventData event){
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
            logger.info(Logs.logAdded(event, event.getId()));
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        logger.error(Logs.logNotAccepted(EventRecord.class));
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    public ResponseEntity<Object> deleteEvent(int id) {
        Optional<EventRecord> event = getEvent(id);
        if (event.isPresent()) {
            logger.info(Logs.logDeleted(EventRecord.class, id));
            eventRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            logger.error(Logs.logNotFound(EventRecord.class, id));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<Object> updateEvent(int id, EventData newEvent){
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
