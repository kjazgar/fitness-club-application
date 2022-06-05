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
    public int eventId;
    @Column(name = "DATE")
    public LocalDateTime date;
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

    public void setCoachOccupied(int occupied){
        this.occupied = occupied;
    }

}
