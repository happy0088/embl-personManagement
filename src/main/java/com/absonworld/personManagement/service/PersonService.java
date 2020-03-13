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
        return getAllPersons();
    }

    public List<Person> deletePayee(Long id) {
        System.out.println("Person" + id + " deleted successfully.");
        dbService.deletePerson(id);
        return getAllPersons();
    }

    public List<Person> updatePayee(Person person) {
        System.out.println("Person" + person.getFirst_name() + " updated successfully.");
        int status = dbService.updatePerson(person);
        return getAllPersons();
    }

    public List<Person> getAllPersons() {
        return dbService.getAllPersonDetails();
    }

    public Person getPerson(Long personId) {
        return dbService.getPersonByID(personId);
    }
}
