package com.jwzp_kr_kj.core;

public class OpeningHours {
    private final String from;
    private final String to;

//    public OpeningHours() {
//        this.from = "";
//        this.to = "";
//    }

    public OpeningHours(DayOfTheWeek dayOfTheWeek, String from, String to) {
        this.from = from;
        this.to = to;
    }

    public OpeningHours(OpeningHours openingHours) {
        this.from = openingHours.from;
        this.to = openingHours.to;
    }

    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        OpeningHours obj = (OpeningHours) o;
        return obj.from.equals(from) && obj.to.equals(to);
    }


    @Override
    public String toString() {
        return "{ \n \"from\": \"" + this.from + "\", \"to\": \"" + this.to + "\" \n}";
    }
}
