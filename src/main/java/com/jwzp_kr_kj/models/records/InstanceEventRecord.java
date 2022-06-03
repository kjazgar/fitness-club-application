package com.jwzp_kr_kj.models.records;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name="instanceEvents")
@Table(name = "INSTANCEEVENTS")
public class InstanceEventRecord {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private final int id;
    public int eventId;
    public LocalDateTime date;
    public int limitOfParticipants;
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
