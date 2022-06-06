package com.jwzp_kr_kj.services;

import com.jwzp_kr_kj.Logs;
import com.jwzp_kr_kj.models.data.ClubData;
import com.jwzp_kr_kj.models.records.ClubRecord;
import com.jwzp_kr_kj.models.DayOfTheWeek;
import com.jwzp_kr_kj.models.records.EventRecord;
import com.jwzp_kr_kj.repos.ClubRepository;
import com.jwzp_kr_kj.repos.EventRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class ClubService {

    private final Logger logger = LogManager.getLogger(CoachService.class);
    public ClubRepository clubRepository;
    public EventRepository eventRepository;

    @Autowired
    public ClubService(ClubRepository clubRepository, EventRepository eventRepository) {
        this.clubRepository = clubRepository;
        this.eventRepository = eventRepository;
    }

    public ResponseEntity<ClubRecord> addClub(ClubData club) {
        ClubRecord newClub = new ClubRecord(club.getName(), club.getAddress(), club.getWhenOpen());
        try {
            var savedClub = clubRepository.save(newClub);
            logger.info(Logs.logAdded(savedClub, savedClub.getId()));
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (IllegalArgumentException e) {
            logger.info(Logs.logException(e));
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }

    }

    public List<ClubRecord> getAllClubs() {
        logger.info(Logs.logGetAll(ClubRecord.class));
        return clubRepository.findAll();
    }

    public Optional<ClubRecord> getClub(int id) {
        logger.info(Logs.logGetById(ClubRecord.class, id));
        return clubRepository.findById(id);
    }

    public ResponseEntity<ClubRecord> deleteClub(int id) {
        Optional<ClubRecord> club = getClub(id);
        if (club.isPresent()) {
            ClubRecord deletedClub = club.get();
            clubRepository.deleteById(id);
            List<EventRecord> events = eventRepository.findByClubId(id);
            for (EventRecord e : events) {
                eventRepository.deleteById(e.getId());
            }
            logger.info(Logs.logDeleted(deletedClub, deletedClub.getId()));
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            logger.info(Logs.logNotFound(ClubRecord.class, id));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<Object> updateClub(int id, ClubData newClub) {
        List<EventRecord> events = eventRepository.findByClubId(id);
        for (EventRecord e : events) {
            if (colisionWithOpeningHours(newClub, e)) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
            }
        }
        Optional<ClubRecord> toUpdate = clubRepository.findById(id);
        if (toUpdate.isPresent()) {
            ClubRecord updated = new ClubRecord(id, newClub.getName(), newClub.getAddress(), newClub.getWhenOpen());
            clubRepository.save(updated);
            logger.info(Logs.logUpdated(updated, updated.getId()));
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        logger.info(Logs.logNotFound(ClubRecord.class, id));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    public Page<ClubRecord> getPage(Pageable p){
        return clubRepository.findAll(p);
    }

    public boolean colisionWithOpeningHours(ClubData club, EventRecord event){
        DayOfTheWeek eventDay = event.getDayOfTheWeek();
        LocalTime startEvent = event.getTime();
        LocalTime endEvent = startEvent.plus(event.getDuration());
        LocalTime clubHourFrom = club.getWhenOpen().get(eventDay).from;
        LocalTime clubHourTo = club.getWhenOpen().get(eventDay).to;
        if (startEvent.isAfter(endEvent)) {
            return !clubHourFrom.equals(clubHourTo);
        }
        return !startEvent.isAfter(clubHourFrom) || !endEvent.isBefore(clubHourTo);
    }
}
