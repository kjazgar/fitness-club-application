package com.jwzp_kr_kj.models.data;

public class CoachData {
    public String firstName;
    public String lastName;
    public int yearOfBirth;

    public CoachData(){
        this.firstName = null;
        this.lastName = null;
        this.yearOfBirth = 0;
    }

    public CoachData(int id, String name, String surname, int yearOfBirth) {
        this.firstName = name;
        this.lastName = surname;
        this.yearOfBirth = yearOfBirth;
    }
}
