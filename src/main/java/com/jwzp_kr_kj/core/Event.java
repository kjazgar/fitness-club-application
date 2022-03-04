package com.jwzp_kr_kj.core;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Duration;
import java.time.LocalDateTime;
import java.lang.StringBuilder;

@Entity(name="events")
public class Event {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private final int id;

    private final String title;
    private final DayOfTheWeek day;
    private final Duration duration;
    private final int coachId;
    private final int clubId;

    public Event() {
        this.id = 0;
        this.title = "";
        this.day = null;
        this.duration = Duration.ZERO;
        this.coachId = 0;
        this.clubId = 0;
    }

    public Event(int id, String name, DayOfTheWeek day, Duration duration, int coachId, int clubId) {
        this.id = id;
        this.title = name;
        this.day = day;
        this.duration = duration;
        this.coachId = coachId;
        this.clubId = clubId;
    }

    public String toString(){
        String string = "{ \n \"title\" : \"" + this.title +"\",\n \"day\" : \""+this.day +"\",\n \"duration\": \""+this.duration + "\", \n \"clubId\":\""+this.clubId + "\",\n \" coachId \": \"" + this.coachId + "\" \n }";
        return string;
    }
}