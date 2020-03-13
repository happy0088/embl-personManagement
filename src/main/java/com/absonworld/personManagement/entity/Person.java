package com.absonworld.personManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

public class Person extends RepresentationModel<Person> {

    //@JsonIgnore
    Long personId;

    String first_name;
    String last_name;
    int age;

    String favourite_colour;
    List<String> hobby;

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFavourite_colour() {
        return favourite_colour;
    }

    public void setFavourite_colour(String favourite_colour) {
        this.favourite_colour = favourite_colour;
    }

    public List<String> getHobby() {
        return hobby;
    }

    public void setHobby(List<String> hobby) {
        this.hobby = hobby;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    @Override
    public String toString() {
        return "Person{" +
                "personId=" + personId +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", age=" + age +
                ", favourite_color='" + favourite_colour + '\'' +
                ", hobby=" + hobby +
                '}';
    }
}
