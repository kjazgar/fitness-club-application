package com.jwzp_kr_kj.models.data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

public class InstanceEventData {
    public int eventId;
    public LocalDateTime date;

    public InstanceEventData(){
        eventId = 0;
        date = null;
    }

    public InstanceEventData(int eventId, LocalDateTime dateTime){
        this.eventId = eventId;
        this.date = dateTime;
    }
}