package com.jwzp_kr_kj.models.records;

import com.jwzp_kr_kj.models.data.InstanceEventData;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name="instanceEvents")
public class InstanceEventRecord {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private final int id;
    public int eventId;
    public LocalDateTime date;
    public int limit;

    public InstanceEventRecord(){
        id = 0;
        eventId = 0;
        date = null;
        limit = 0;
    }

    public InstanceEventRecord(int id, int eventId, LocalDateTime dateTime, int limit){
        this.id = id;
        this.eventId = eventId;
        this.date = dateTime;
        this.limit = limit;
    }
}
