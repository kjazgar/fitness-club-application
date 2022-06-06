package com.jwzp_kr_kj.models.data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

public class InstanceEventData {
    private int eventId;
    private LocalDateTime date;

    public InstanceEventData(){
        eventId = 0;
        date = null;
    }

    public InstanceEventData(int eventId, LocalDateTime dateTime){
        this.eventId = eventId;
        this.date = dateTime;
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
}