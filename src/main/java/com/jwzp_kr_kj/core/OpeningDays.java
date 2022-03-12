package com.jwzp_kr_kj.core;

public class OpeningDays {
    private final OpeningHours monday;
    private final OpeningHours tuesday;
    private final OpeningHours wednesday;
    private final OpeningHours thursday;
    private final OpeningHours friday;
    private final OpeningHours saturday;
    private final OpeningHours sunday;

//    public OpeningDays() {
//        monday = new OpeningHours();
//        tuesday = new OpeningHours();
//        wednesday = new OpeningHours();
//        thursday = new OpeningHours();
//        friday = new OpeningHours();
//        saturday = new OpeningHours();
//        sunday = new OpeningHours();
//    }

    public OpeningDays(OpeningHours monday, OpeningHours tuesday, OpeningHours wednesday, OpeningHours thursday, OpeningHours friday, OpeningHours saturday, OpeningHours sunday) {
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;
    }

    @Override
    public String toString() {
        String string = "{ \n \"MONDAY\":" + this.monday.toString() + ",\n \"TUESDAY\": " + this.tuesday.toString() + ",\n \"WEDNESDAY\": " + this.wednesday.toString() + ",\n \"THURSDAY\": " + this.thursday.toString() +",\n \"FRIDAY\": " + this.friday.toString();
        if (this.saturday != null){
            string = string + ",\n \"SATURDAY\": " + this.saturday.toString();
        }
        if (this.sunday != null){
            string = string + ",\n \"SUNDAY\": " + this.sunday.toString();
        }
        string = string + "\n}";
        return string;
    }

}
