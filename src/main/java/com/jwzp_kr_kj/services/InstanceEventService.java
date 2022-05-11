package com.jwzp_kr_kj.services;

import com.jwzp_kr_kj.models.data.InstanceEventData;
import com.jwzp_kr_kj.models.records.EventRecord;
import com.jwzp_kr_kj.models.records.InstanceEventRecord;
import com.jwzp_kr_kj.repos.ClubRepository;
import com.jwzp_kr_kj.repos.CoachRepository;
import com.jwzp_kr_kj.repos.EventRepository;
import com.jwzp_kr_kj.repos.InstanceEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class InstanceEventService {
    public EventRepository eventRepository;
    public InstanceEventRepository instanceEventRepository;

    @Autowired
    public InstanceEventService(EventRepository eventRepository, InstanceEventRepository instanceEventRepository){
        this.eventRepository = eventRepository;
        this.instanceEventRepository = instanceEventRepository;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void automaticallyArchiveEvents() {
        LocalDate current = LocalDate.now();
        LocalDate monthAgoDate = current.minusDays(30);
        instanceEventRepository.archiveAllOlderThanMonth(monthAgoDate);
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void automaticallyCreateEvents() {
        LocalDate current = LocalDate.now();
        LocalDate dateInMonth = current.plusDays(30);
        DayOfWeek dayofTheWeekInMonth = dateInMonth.getDayOfWeek();

        List<EventRecord> scheduleForADay = eventRepository.getScheduleForADay(dayofTheWeekInMonth.toString());
        for(EventRecord event : scheduleForADay){
            addInstanceEvent(event.getId(), LocalDateTime.of(dateInMonth, event.getTime()).toString());
        }
    }

    public ResponseEntity<Object> addInstanceEvent(int eventId, String time){
        instanceEventRepository.addData(eventId, time);
        return ResponseEntity.status(HttpStatus.OK).build();

    }
}
