package com.library.LibraryProject.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;


class Address{
    private String houseNo;
    private String city;
    private String state;

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Address(String houseNo, String city, String state) {
        this.houseNo = houseNo;
        this.city = city;
        this.state = state;
    }

    @Override
    public String toString(){
        return "House No: " + houseNo + "    " + "City: " + city + "    " + "State: " + state + "\n";
    }
}


@Document(collection = "Authors")
public class Author {
    private String id;
    private String name;
    private Address address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public Author(String id, String name, Address address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
