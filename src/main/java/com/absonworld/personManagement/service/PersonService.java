package com.absonworld.personManagement.service;

import com.absonworld.personManagement.entity.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonService {
    H2JDBCService dbService = new H2JDBCService();
    List<Person> personList = new ArrayList<>();

    public List<Person> addPerson(Person person) {
        System.out.println("Person" + person.getFirst_name() + " added successfully.");
        int count = dbService.createPerson(person);
        return personList;
    }

    public List<Person> deletePayee(Person person) {
        System.out.println("Person" + person.getFirst_name() + " deleted successfully.");
        dbService.deletePerson(person);
        return personList;
    }

    public List<Person> updatePayee(Person person) {
        System.out.println("Person" + person.getFirst_name() + " updated successfully.");
        int status = dbService.updatePerson(person);
        return personList;
    }

    public List<Person> getAllPersons() {
        return dbService.getAllPersonDetails();
    }

    public Person getPerson(Long personId) {
        return dbService.getPersonByID(personId);
    }
}
