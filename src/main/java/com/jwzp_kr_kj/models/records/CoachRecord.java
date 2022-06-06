package com.jwzp_kr_kj.models.records;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity(name = "coaches")
public class CoachRecord {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private final int id;

    private String firstName;
    private String lastName;
    private int yearOfBirth;

    public CoachRecord() {
        this.id = 0;
        this.firstName = "";
        this.lastName = "";
        this.yearOfBirth = 0;
    }

    public CoachRecord(String name, String surname, int yearOfBirth) {
        this.id = -1;
        this.firstName = name;
        this.lastName = surname;
        this.yearOfBirth = yearOfBirth;
    }

    public CoachRecord(int id, String name, String surname, int yearOfBirth) {
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

    public int getId() {
        return id;
    }
}