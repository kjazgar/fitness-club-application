package com.jwzp_kr_kj.models.data;

import com.jwzp_kr_kj.models.DayOfTheWeek;
import com.jwzp_kr_kj.models.OpeningHours;

import javax.persistence.ElementCollection;
import java.util.HashMap;
import java.util.Map;

public class ClubData {
    private String name;
    private String address;
    @ElementCollection
    private Map<DayOfTheWeek, OpeningHours> whenOpen;

    private ClubData() {
        name = "";
        address = "";
        whenOpen = new HashMap<>();
    }

    private ClubData(String name, String address, Map<DayOfTheWeek, OpeningHours> whenOpen) {
        this.name = name;
        this.address = address;
        this.whenOpen = whenOpen;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setWhenOpen(Map<DayOfTheWeek, OpeningHours> whenOpen){
        this.whenOpen = whenOpen;
    }

    public String getName(){
        return this.name;
    }

    public String getAddress(){
        return this.address;
    }

    public Map<DayOfTheWeek, OpeningHours> getWhenOpen(){
        return this.whenOpen;
    }


}
