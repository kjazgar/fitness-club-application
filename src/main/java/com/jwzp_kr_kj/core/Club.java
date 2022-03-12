package com.jwzp_kr_kj.core;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="clubs")
public class Club {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private final int id;

    public final String name;
    public final String address;
    public final OpeningDays whenOpen;

//    public Club(){
//        id = 0;
//        name = "";
//        address = "";
//        whenOpen = null;
//    }

    public Club(int id, String name, String address, OpeningDays whenOpen) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.whenOpen = whenOpen;
    }

    @Override
    public String toString(){
        return "{ \n \"name\": \"" + this.name +"\",\n \"address\": \""+ this.address +"\",\n \"whenOpen\": {\n" + this.whenOpen.toString() + "}";
    }

}
