package com.jwzp_kr_kj.core;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Duration;
import java.time.LocalDateTime;

public class Event {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private final int id;

    private final String name;
    private final LocalDateTime date;
    private final Duration duration;
    private final Coach coach;
    private final Club club;

    public Event() {
        this.id = 0;
        this.name = "";
        this.date = null;
        this.duration = Duration.ZERO;
        this.coach = new Coach();
        this.club = new Club();
    }

    public Event(int id, String name, LocalDateTime date, Duration duration, Coach coach, Club club) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.duration = duration;
        this.coach = coach;
        this.club = club;
    }
}