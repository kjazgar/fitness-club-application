package com.jwzp_kr_kj.core;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Coach {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private final int id;

    public final String name;
    public final String surname;
    public final int yearOfBirth;

    public Coach() {
        this.id = 0;
        this.name = "";
        this.surname = "";
        this.yearOfBirth = 0;
    }

    public Coach(int id, String name, String surname, int yearOfBirth) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.yearOfBirth = yearOfBirth;
    }
}