package com.jwzp_kr_kj.models.records;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.jwzp_kr_kj.models.DayOfTheWeek;
import com.jwzp_kr_kj.models.OpeningHours;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@Entity(name="persons")
public class PersonRecord extends RepresentationModel<PersonRecord> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final int id;

    private String name;
    private String surname;
    @JsonProperty("instance_id")
    private int instanceEventId;

    public PersonRecord() {
        id = 0;
        name = "";
        surname = "";
        instanceEventId = 0;
    }

    public PersonRecord(String name, String surname, int instanceEventId) {
        this.id = -1;
        this.name = name;
        this.surname = surname;
        this.instanceEventId = instanceEventId;
    }

    public PersonRecord(int id, String name, String surname, int instanceEventId) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.instanceEventId = instanceEventId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getInstanceEventId() {
        return instanceEventId;
    }
};