package com.jwzp_kr_kj.core;

import javax.persistence.*;
import java.io.Serializable;

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




