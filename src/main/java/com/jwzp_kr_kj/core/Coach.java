package com.jwzp_kr_kj.core;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Coach {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private final int id;

    public final String firstName;
    public final String lastName;
    public final int yearOfBirth;

    public Coach() {
        this.id = 0;
        this.firstName = "";
        this.lastName = "";
        this.yearOfBirth = 0;
    }

    public Coach(int id, String name, String surname, int yearOfBirth) {
        this.id = id;
        this.firstName = name;
        this.lastName = surname;
        this.yearOfBirth = yearOfBirth;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString(){
        String string = "{ \n \"name\": \"" + this.firstName +"\",\n \"surname\": \""+this.lastName +"\",\n \"yearOfBirth\": \"" + this.yearOfBirth + "\" \n }";
        return string;
    }
}