package com.absonworld.personManagement.service;

import com.absonworld.personManagement.entity.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonService {

    public List<Person> addPayee(Person person) {
        System.out.println("Person" + person.getFirst_name() + " added successfully.");
        List<Person> personList = new ArrayList<>();
        personList.add(person);
        return personList;
    }

}
