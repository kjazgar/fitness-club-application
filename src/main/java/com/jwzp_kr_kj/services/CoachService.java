package com.jwzp_kr_kj.services;

import com.jwzp_kr_kj.models.data.CoachData;
import com.jwzp_kr_kj.models.records.CoachRecord;
import com.jwzp_kr_kj.models.records.EventRecord;
import com.jwzp_kr_kj.repos.CoachRepository;
import com.jwzp_kr_kj.repos.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class CoachService {

    public CoachRepository coachRepository;
    public EventRepository eventRepository;

    @Autowired
    public CoachService(CoachRepository coachRepository, EventRepository eventRepository) {
        this.coachRepository = coachRepository;
        this.eventRepository = eventRepository;
    }

    public void addCoach(CoachData coach) {
        CoachRecord newCoach = new CoachRecord(coach.firstName, coach.lastName, coach.yearOfBirth);
        coachRepository.save(newCoach);
    }

    public ResponseEntity<Object> deleteCoach(int id) {
        Optional<CoachRecord> coach = getCoach(id);
        if (coach.isPresent()) {
            coachRepository.deleteById(id);
            List<EventRecord> events = eventRepository.findByCoachId(id);
            for (EventRecord e : events) {
                eventRepository.deleteById(e.getId());
            }
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<Object> updateCoach(int id, CoachData newCoach) {
        Optional<CoachRecord> toUpdate = coachRepository.findById(id);
        if (toUpdate.isPresent()) {
            CoachRecord updated = new CoachRecord(id, newCoach.firstName, newCoach.lastName, newCoach.yearOfBirth);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
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
        return coachRepository.findAll();
    }

    public Optional<CoachRecord> getCoach(int id) {
        return coachRepository.findById(id);
    }
}
