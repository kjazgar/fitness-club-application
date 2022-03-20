package com.jwzp_kr_kj.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

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
