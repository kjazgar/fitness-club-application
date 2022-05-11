package com.jwzp_kr_kj.models.data;

import com.jwzp_kr_kj.models.DayOfTheWeek;
import com.jwzp_kr_kj.models.OpeningHours;

import javax.persistence.ElementCollection;
import java.util.HashMap;
import java.util.Map;

public class ClubData {
    public final String name;
    public final String address;
    @ElementCollection
    public Map<DayOfTheWeek, OpeningHours> whenOpen;

    public ClubData() {
        name = "";
        address = "";
        whenOpen = new HashMap<>();
    }

    public ClubData(String name, String address, Map<DayOfTheWeek, OpeningHours> whenOpen) {
        this.name = name;
        this.address = address;
        this.whenOpen = whenOpen;
    }
}
