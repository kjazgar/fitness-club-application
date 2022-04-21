package com.jwzp_kr_kj.models.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jwzp_kr_kj.models.DayOfTheWeek;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Duration;
import java.time.LocalTime;

public class EventData {
    private String title;
    private DayOfTheWeek dayOfTheWeek;
    private LocalTime time;
    private Duration duration;
    private int coachId;
    private int clubId;

    public EventData() {
        this.title = "";
        this.dayOfTheWeek = null;
        this.time = null;
        this.duration = Duration.ZERO;
        this.coachId = 0;
        this.clubId = 0;
    }

    public EventData(int id, String name, DayOfTheWeek day, LocalTime time, Duration duration, int coachId, int clubId) {
        this.title = name;
        this.dayOfTheWeek = day;
        this.time = time;
        this.duration = duration;
        this.coachId = coachId;
        this.clubId = clubId;
    }

}
