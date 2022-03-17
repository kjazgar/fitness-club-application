package com.jwzp_kr_kj.core;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity(name = "coaches")
public class Coach {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public final int id;

    public String firstName;
    public String lastName;
    public int yearOfBirth;

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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }
}