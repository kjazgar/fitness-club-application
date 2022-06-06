package com.jwzp_kr_kj.services;

import com.jwzp_kr_kj.Logs;
import com.jwzp_kr_kj.models.data.CoachData;
import com.jwzp_kr_kj.models.records.ClubRecord;
import com.jwzp_kr_kj.models.records.CoachRecord;
import com.jwzp_kr_kj.models.records.EventRecord;
import com.jwzp_kr_kj.repos.CoachRepository;
import com.jwzp_kr_kj.repos.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class CoachService {

    private final Logger logger = LogManager.getLogger(CoachService.class);
    public CoachRepository coachRepository;
    public EventRepository eventRepository;

    @Autowired
    public CoachService(CoachRepository coachRepository, EventRepository eventRepository) {
        this.coachRepository = coachRepository;
        this.eventRepository = eventRepository;
    }

    public ResponseEntity<CoachRecord> addCoach(CoachData coach) {
        CoachRecord newCoach = new CoachRecord(coach.getFirstName(), coach.getLastName(), coach.getYearOfBirth());
        try{
            var savedCoach = coachRepository.save(newCoach);
            logger.info(Logs.logSaved(savedCoach, savedCoach.getId()));
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (IllegalArgumentException e) {
            logger.info(Logs.logException(e));
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }

    }

    public ResponseEntity<Object> deleteCoach(int id) {
        Optional<CoachRecord> coach = getCoach(id);
        if (coach.isPresent()) {
            CoachRecord deletedCoach = coach.get();
            coachRepository.deleteById(id);
            List<EventRecord> events = eventRepository.findByCoachId(id);
            for (EventRecord e : events) {
                eventRepository.deleteById(e.getId());
            }
            logger.info(Logs.logDeleted(deletedCoach, deletedCoach.getId()));
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            logger.info(Logs.logNotFound(CoachRecord.class, id));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<Object> updateCoach(int id, CoachData newCoach) {
        Optional<CoachRecord> toUpdate = coachRepository.findById(id);
        if (toUpdate.isPresent()) {
            CoachRecord updated = new CoachRecord(id, newCoach.getFirstName(), newCoach.getLastName(), newCoach.getYearOfBirth());
            coachRepository.save(updated);
            logger.info(Logs.logUpdated(updated, updated.getId()));
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        logger.info(Logs.logNotFound(CoachRecord.class, id));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    public Page<CoachRecord> getPage(Pageable p){
        return coachRepository.findAll(p);
    }

    public boolean coachIsAvailable(int id, EventRecord newEvent) {
        List<EventRecord> coachEvents = eventRepository.findByCoachId(id);
        LocalTime startNewEvent = newEvent.getTime();
        LocalTime endNewEvent = startNewEvent.plus(newEvent.getDuration());
        for (EventRecord e : coachEvents) {
            LocalTime startEvent = e.getTime();
            LocalTime endEvent = startEvent.plus(e.getDuration());
            if (startNewEvent.isAfter(endNewEvent) && startEvent.isAfter(endEvent) && e.getDayOfTheWeek().equals(newEvent.getDayOfTheWeek())) {
                return false;
            } else if (startNewEvent.isAfter(endNewEvent) && ((endEvent.isAfter(startNewEvent) && e.getDayOfTheWeek().equals(newEvent.getDayOfTheWeek()) || (startEvent.isBefore(endNewEvent) && e.getDayOfTheWeek().equals(newEvent.getDayOfTheWeek().next()))))) {
                return false;
            } else if (startEvent.isAfter(endNewEvent) && ((endNewEvent.isAfter(startEvent) && e.getDayOfTheWeek().equals(newEvent.getDayOfTheWeek()) || (startNewEvent.isBefore(endEvent) && newEvent.getDayOfTheWeek().equals(e.getDayOfTheWeek().next()))))) {
                return false;
            } else if (endEvent.isAfter(startEvent) && endNewEvent.isAfter(startNewEvent) && e.getDayOfTheWeek().equals(newEvent.getDayOfTheWeek())) {
                return (startEvent.isAfter(endNewEvent) || endEvent.isBefore(startNewEvent));
            }
        }
        return true;
    }

    public List<CoachRecord> getAllCoaches() {
        logger.info(Logs.logGetAll(CoachRecord.class));
        return coachRepository.findAll();
    }

    public Optional<CoachRecord> getCoach(int id) {
        logger.info(Logs.logGetById(CoachRecord.class, id));
        return coachRepository.findById(id);
    }
}
