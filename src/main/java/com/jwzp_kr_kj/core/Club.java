package com.jwzp_kr_kj.core;


import javax.persistence.*;
import java.util.Map;

@Entity(name="clubs")
public class Club {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private final int id;

    public final String name;
    public final String address;
    @ElementCollection
    public final Map<DayOfTheWeek, OpeningHours> whenOpen;


    public Club(int id, String name, String address, Map<DayOfTheWeek, OpeningHours> whenOpen) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.whenOpen = whenOpen;
    }
}
