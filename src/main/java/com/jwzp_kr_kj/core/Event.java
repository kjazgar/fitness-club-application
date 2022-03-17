package com.jwzp_kr_kj.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.Duration;


@Entity(name="events")
public class Event {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private final int id;

    private final String title;

    @JsonProperty("day")
    private final DayOfTheWeek dayOfTheWeek;
    private final String time;
    private final Duration duration;
    private final int coachId;
    private final int clubId;

    public Event() {
        this.id = 0;
        this.title = "";
        this.dayOfTheWeek = null;
        this.time = null;
        this.duration = Duration.ZERO;
        this.coachId = 0;
        this.clubId = 0;
    }

    public Event(int id, String name, DayOfTheWeek day, String time, Duration duration, int coachId, int clubId) {
        this.id = id;
        this.title = name;
        this.dayOfTheWeek = day;
        this.time = time;
        this.duration = duration;
        this.coachId = coachId;
        this.clubId = clubId;
    }

    public int getClubId(){
        return this.clubId;
    }

    public int getCoachId(){
        return this.coachId;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public DayOfTheWeek getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public Duration getDuration() {
        return duration;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString(){
        String string = "{ \n \"title\": \"" + this.title +"\",\n \"day\": \""+this.dayOfTheWeek +"\",\n \"time\": \"" + this.time + "\",\n \"duration\": \""+this.duration + "\", \n \"clubId\":\""+this.clubId + "\",\n \" coachId \": \"" + this.coachId + "\" \n }";
        return string;
    }
}