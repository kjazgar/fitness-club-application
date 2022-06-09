package com.jwzp_kr_kj.models.records;


import com.jwzp_kr_kj.models.DayOfTheWeek;
import com.jwzp_kr_kj.models.OpeningHours;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@Entity(name="clubs")
public class ClubRecord extends RepresentationModel<ClubRecord> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final int id;

    private String name;
    private String address;
    @ElementCollection
    private Map<DayOfTheWeek, OpeningHours> whenOpen;

    public ClubRecord() {
        id = 0;
        name = "";
        address = "";
        whenOpen = new HashMap<>();
    }

    public ClubRecord(String name, String address, Map<DayOfTheWeek, OpeningHours> whenOpen) {
        this.id = -1;
        this.name = name;
        this.address = address;
        this.whenOpen = whenOpen;
    }

    public ClubRecord(int id, String name, String address, Map<DayOfTheWeek, OpeningHours> whenOpen) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.whenOpen = whenOpen;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public Map<DayOfTheWeek, OpeningHours> getWhenOpen(){
        return whenOpen;
    }

    public void setWhenOpen(Map<DayOfTheWeek, OpeningHours> whenOpen){
        this.whenOpen = whenOpen;
    }

    public boolean isWithinClubOpeningHours(DayOfTheWeek dayOfTheWeek, LocalTime startHour, Duration duration){
        OpeningHours clubOpeningHours = whenOpen.get(dayOfTheWeek);
        LocalTime clubStartHour = clubOpeningHours.from;
        LocalTime clubEndHour = clubOpeningHours.to;
        LocalTime endHour = startHour.plusHours(duration.toHours());

       return clubStartHour.compareTo(startHour) <= 0 && clubEndHour.compareTo(startHour) >= 0 &&
               clubEndHour.compareTo(endHour) >= 0;
    }
}
