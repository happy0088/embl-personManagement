package com.absonworld.personManagement.service;

import com.absonworld.personManagement.entity.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonService {
    List<Person> personList = new ArrayList<>();

    public List<Person> addPayee(Person person) {
        System.out.println("Person" + person.getFirst_name() + " added successfully.");
        personList.add(person);
        return personList;
    }

    public List<Person> deletePayee(Person person) {
        System.out.println("Person" + person.getFirst_name() + " deleted successfully.");
        personList.remove(person);
        return personList;
    }

    public List<Person> updatePayee(Person person) {
        System.out.println("Person" + person.getFirst_name() + " updated successfully.");
        personList.add(person);
        return personList;
    }
}
