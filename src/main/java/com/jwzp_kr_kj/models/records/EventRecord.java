package com.jwzp_kr_kj.models.records;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jwzp_kr_kj.models.DayOfTheWeek;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalTime;


@Entity(name="events")
public class EventRecord {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private final int id;
    private String title;
    @JsonProperty("day")
    private DayOfTheWeek dayOfTheWeek;
    @JsonFormat(pattern="HH:mm")
    private LocalTime time;
    private Duration duration;
    private int coachId;
    private int clubId;

    public EventRecord() {
        this.id = 0;
        this.title = "";
        this.dayOfTheWeek = null;
        this.time = null;
        this.duration = Duration.ZERO;
        this.coachId = 0;
        this.clubId = 0;
    }

    public EventRecord(int id, String name, DayOfTheWeek day, LocalTime time, Duration duration, int coachId, int clubId) {
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

    public LocalTime getTime() {
        return time;
    }

    public void setDayOfTheWeek(DayOfTheWeek dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setClubId(int clubId){
        this.clubId = clubId;
    }

    public void setCoachId(int coachId){
        this.coachId = coachId;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }
}