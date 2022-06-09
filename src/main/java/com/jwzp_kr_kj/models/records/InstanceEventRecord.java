package com.jwzp_kr_kj.models.records;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name="instanceEvents")
@Table(name = "INSTANCEEVENTS")
public class InstanceEventRecord {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private final int id;
    @Column(name = "EVENTID")
    private int eventId;
    @Column(name = "DATE")
    private LocalDateTime date;
    @Column(name = "LIMITOFPARTICIPANTS")
    public int limitOfParticipants;
    @Column(name = "OCCUPIED")
    public int occupied;

    public InstanceEventRecord(){
        id = 0;
        eventId = 0;
        date = null;
        limitOfParticipants = 0;
        occupied = 0;
    }

    public InstanceEventRecord(int id, int eventId, LocalDateTime dateTime, int limit, int occupied){
        this.id = id;
        this.eventId = eventId;
        this.date = dateTime;
        this.limitOfParticipants = limit;
        this.occupied = occupied;

    }

    public int getId() {
        return id;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getLimitOfParticipants() {
        return limitOfParticipants;
    }

    public void setLimitOfParticipants(int limitOfParticipants) {
        this.limitOfParticipants = limitOfParticipants;
    }

    public int getOccupied() {
        return occupied;
    }

    public void setOccupied(int occupied) {
        this.occupied = occupied;
    }
}
