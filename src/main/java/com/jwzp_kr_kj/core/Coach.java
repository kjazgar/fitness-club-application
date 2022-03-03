package com.jwzp_kr_kj.core;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Coach {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private final int id;

    private final String name;
    private final String surname;

    protected Coach() {
        this.id = 0;
        this.name = "";
        this.surname = "";
    }

    protected Coach(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }
}