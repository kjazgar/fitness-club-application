package com.jwzp_kr_kj.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Embeddable;

//@Embeddable
//public record OpeningHours(@Column(name = "\"from\"") String from, @Column(name = "\"to\"") String to) {
//    public OpeningHours(){
//        this("", "");
//    }
//}
@Embeddable
public class OpeningHours {
    @JsonProperty("from")
    public final String fromHour;
    @JsonProperty("to")
    public final String toHour;

    public OpeningHours(){
        fromHour = "";
        toHour = "";
    }

    public OpeningHours(String fromHour, String toHour){
        this.fromHour = fromHour;
        this.toHour = toHour;
    }
}
