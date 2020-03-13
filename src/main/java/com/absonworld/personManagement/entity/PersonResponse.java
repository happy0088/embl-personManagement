package com.absonworld.personManagement.entity;

import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;

public class PersonResponse extends RepresentationModel<PersonResponse> {

    List<Person> Person = new ArrayList<>();

    public List<com.absonworld.personManagement.entity.Person> getPerson() {
        return Person;
    }

    public void setPerson(List<com.absonworld.personManagement.entity.Person> person) {
        Person = person;
    }

    @Override
    public String toString() {
        return "PersonResponse{" +
                "Person=" + Person +
                '}';
    }
}
