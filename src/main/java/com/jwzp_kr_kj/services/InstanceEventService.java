package com.jwzp_kr_kj.services;

import com.jwzp_kr_kj.Logs;
import com.jwzp_kr_kj.models.DayOfTheWeek;
import com.jwzp_kr_kj.models.data.InstanceEventData;
import com.jwzp_kr_kj.models.records.EventRecord;
import com.jwzp_kr_kj.models.records.InstanceEventRecord;
import com.jwzp_kr_kj.repos.ClubRepository;
import com.jwzp_kr_kj.repos.CoachRepository;
import com.jwzp_kr_kj.repos.EventRepository;
import com.jwzp_kr_kj.repos.InstanceEventRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class InstanceEventService {
    public EventRepository eventRepository;
    public InstanceEventRepository instanceEventRepository;
    private final Logger logger = LogManager.getLogger(InstanceEventService.class);

    @Autowired
    public InstanceEventService(EventRepository eventRepository, InstanceEventRepository instanceEventRepository){
        this.eventRepository = eventRepository;
        this.instanceEventRepository = instanceEventRepository;
    }

    public List<InstanceEventRecord> getAllInstanceEvents() {
        return instanceEventRepository.findAll();
    }

    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void automaticallyArchiveEvents() {
        logger.info("Run automaticallyArchiveEvents");
        LocalDateTime current = LocalDateTime.now();
        LocalDateTime monthAgoDate = current.minusDays(30);
        instanceEventRepository.archiveAllOlderThanMonth(monthAgoDate);
    }

    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void automaticallyCreateEvents() {
        logger.info("Run automaticallyCreateEvents");
        LocalDate current = LocalDate.now();
        LocalDate dateInMonth = current.plusDays(30);
        DayOfWeek dayofTheWeekInMonth = dateInMonth.getDayOfWeek();

        List<EventRecord> scheduleForADay = eventRepository.getScheduleForADay(dayOfTheWeekConverter(dayofTheWeekInMonth));
        for(EventRecord event : scheduleForADay){
            addInstanceEvent(event.getId(), LocalDateTime.of(dateInMonth, event.getTime()).toString(), 30);
        }
    }

    public InstanceEventRecord getInstanceEvent(int id) {
        return instanceEventRepository.selectInstanceEventById(id);
    }

    public ResponseEntity<Object> addInstanceEvent(int eventId, String time, int limit){
        instanceEventRepository.addData(eventId, time, limit);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Transactional
    public ResponseEntity<Object> registerForEvent(int id){
        InstanceEventRecord instanceEventRecord = getInstanceEvent(id);

        if(instanceEventRecord.getOccupied() >= instanceEventRecord.getLimitOfParticipants()){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }

        instanceEventRepository.setOccupied(instanceEventRecord.occupied + 1, id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Transactional
    public ResponseEntity<Object> cancelEvent(int id){
        instanceEventRepository.cancelEventById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Transactional
    public ResponseEntity<Object> postponeEvent(int id, LocalDateTime date){
        instanceEventRepository.setDate(date, id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public List<InstanceEventRecord> findEventByDateAndClub(LocalDateTime date, int clubId){
        return instanceEventRepository.findEventByDateAndClub(date, clubId);
    }

    private DayOfTheWeek dayOfTheWeekConverter(DayOfWeek dayOfWeek){
        switch (dayOfWeek.toString()){
            case "MONDAY":
                return DayOfTheWeek.MONDAY;
            case "TUESDAY":
                return DayOfTheWeek.TUESDAY;
            case "WEDNESDAY":
                return DayOfTheWeek.WEDNESDAY;
            case "THURSDAY":
                return DayOfTheWeek.THURSDAY;
            case "FRIDAY":
                return DayOfTheWeek.FRIDAY;
            case "SATURDAY":
                return DayOfTheWeek.SATURDAY;
            case "SUNDAY":
                return DayOfTheWeek.SUNDAY;

        }

        return null;
    }
}
