package com.jwzp_kr_kj.services;

import com.jwzp_kr_kj.models.records.ClubRecord;
import com.jwzp_kr_kj.models.DayOfTheWeek;
import com.jwzp_kr_kj.models.records.EventRecord;
import com.jwzp_kr_kj.repos.ClubRepository;
import com.jwzp_kr_kj.repos.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class ClubService {

    public ClubRepository clubRepository;
    public EventRepository eventRepository;

    @Autowired
    public ClubService(ClubRepository clubRepository, EventRepository eventRepository) {
        this.clubRepository = clubRepository;
        this.eventRepository = eventRepository;
    }

    public void addClub(ClubRecord club) {
        clubRepository.save(club);
    }

    public List<ClubRecord> getAllClubs() {
        return clubRepository.findAll();
    }

    public Optional<ClubRecord> getClub(int id) {
        return clubRepository.findById(id);
    }

    public ResponseEntity<Object> deleteClub(int id) {
        Optional<ClubRecord> club = getClub(id);
        if (club.isPresent()) {
            clubRepository.deleteById(id);
            List<EventRecord> events = eventRepository.findByClubId(id);
            for (EventRecord e : events) {
                eventRepository.deleteById(e.getId());
            }
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<Object> updateClub(int id, ClubRecord newClub){
        List<EventRecord> events = eventRepository.findByClubId(id);
        for (EventRecord e : events){
            if (colisionWithOpeningHours(newClub, e)){
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
            }
        }
        clubRepository.findById(id).map(club -> {
            club.setName(newClub.getName());
            club.setAddress(newClub.getAddress());
            club.setWhenOpen(newClub.getWhenOpen());
            return clubRepository.save(club);
        });
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public boolean colisionWithOpeningHours(ClubRecord club, EventRecord event){
        DayOfTheWeek eventDay = event.getDayOfTheWeek();
        LocalTime startEvent = event.getTime();
        LocalTime endEvent = startEvent.plus(event.getDuration());
        LocalTime clubHourFrom = club.whenOpen.get(eventDay).from;
        LocalTime clubHourTo = club.whenOpen.get(eventDay).to;
        if (startEvent.isAfter(endEvent)){
            return !clubHourFrom.equals(clubHourTo);
        }
        return !startEvent.isAfter(clubHourFrom) || !endEvent.isBefore(clubHourTo);
    }
}
