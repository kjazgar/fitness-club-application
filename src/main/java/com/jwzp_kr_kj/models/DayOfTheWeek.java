package com.jwzp_kr_kj.models;

public enum DayOfTheWeek {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;

    public DayOfTheWeek next(){
        return values()[(ordinal()+1)% values().length];
    }
}




