package com.jwzp_kr_kj.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalTime;

@Embeddable
public class OpeningHours {
    @JsonFormat(pattern="HH:mm")
    @Column(name = "\"from\"", columnDefinition = "TIME")
    public final LocalTime from;
    @JsonFormat(pattern="HH:mm")
    @Column(name = "\"to\"", columnDefinition = "TIME")
    public final LocalTime to;

    public OpeningHours() {
        from = null;
        to = null;
    }

    public OpeningHours(LocalTime fromHour, LocalTime toHour) {
        this.from = fromHour;
        this.to = toHour;
    }
}
