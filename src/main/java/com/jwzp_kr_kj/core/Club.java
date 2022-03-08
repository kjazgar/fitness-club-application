package com.jwzp_kr_kj.core;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Duration;
import java.time.LocalDateTime;

@Entity(name="clubs")
public class Club {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private final int id;

    private final LocalDateTime startTime;
    private final Duration duration;
    private final String name;
    private final String address;

    public Club(){
        id = 0;
        startTime = null;
        duration = Duration.ZERO;
        name = "";
        address = "";
    }

    public Club(int id, LocalDateTime startTime, Duration duration, String name, String address) {
        this.id = id;
        this.startTime = startTime;
        this.duration = duration;
        this.name = name;
        this.address = address;
    }

}
