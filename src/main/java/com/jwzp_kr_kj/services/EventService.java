package com.jwzp_kr_kj.services;

import com.jwzp_kr_kj.core.Event;
import com.jwzp_kr_kj.repos.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    public EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    public void addEvent(Event event){
        eventRepository.save(event);
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
