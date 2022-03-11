package com.jwzp_kr_kj.core;

import java.time.LocalTime;

public class OpeningTimes {
    private DayOfTheWeek dayOfTheWeek;
    private LocalTime from;
    private LocalTime to;

    public DayOfTheWeek getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    @Override
    public String toString(){
        return "\"" + this.dayOfTheWeek +  "\": { \n \"from\": \"" + this.from + "\", \"to\": \"" + this.to + "\" \n}";
    }
}
