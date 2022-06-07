package com.jwzp_kr_kj.models.data;

public class CoachData {
    private String firstName;
    private String lastName;
    private int yearOfBirth;

    public CoachData(){
        this.firstName = null;
        this.lastName = null;
        this.yearOfBirth = 0;
    }

    public CoachData(String name, String surname, int yearOfBirth) {
        this.firstName = name;
        this.lastName = surname;
        this.yearOfBirth = yearOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
