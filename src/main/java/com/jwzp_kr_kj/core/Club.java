package com.jwzp_kr_kj.core;


import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity(name="clubs")
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public final int id;

    public String name;
    public String address;
    @ElementCollection
    public Map<DayOfTheWeek, OpeningHours> whenOpen;

    public Club() {
        id = 0;
        name = "";
        address = "";
        whenOpen = new HashMap<>();
    }

    public Club(int id, String name, String address, Map<DayOfTheWeek, OpeningHours> whenOpen) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.whenOpen = whenOpen;
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
}
