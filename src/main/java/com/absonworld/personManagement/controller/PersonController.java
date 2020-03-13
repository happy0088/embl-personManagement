package com.absonworld.personManagement.controller;

import com.absonworld.personManagement.entity.Person;
import com.absonworld.personManagement.entity.PersonResponse;
import com.absonworld.personManagement.service.PersonService;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v1")
public class PersonController {

    PersonService service = new PersonService();

    /**
     * Health Check APi.
     *
     * @return the string
     */
    @GetMapping("/health")
    public String healthStatus() {
        return "Service is UP and Running!!";
    }

    /**
     * Get All Persons Details.
     *
     * @return the string
     */
    @GetMapping("/persons")
    public PersonResponse getAllPerson() {
        PersonResponse response = new PersonResponse();
        response.setPerson(service.getAllPersons());
        return response;
    }

    /**
     * Get Person Details.
     *
     * @return the string
     */
    @GetMapping("/person/{id}")
    public Person getPerson(@PathVariable Long id) {
        Person person = service.getPerson(id);
        person.add(linkTo(methodOn(PersonController.class).getPerson(id)).withSelfRel());
        return person;
    }

    /**
     * Add New Person.
     *
     * @return the person list
     */
    @PostMapping(path = "/person", consumes = "application/json", produces = "application/json")
    public List<Person> addPerson(@RequestBody Person personDetails) {
        List<Person> personList = service.addPerson(personDetails);
        System.out.println("Person Added is " + personDetails.getFirst_name());
        personList.stream().forEach(person -> {
            person.add(linkTo(methodOn(PersonController.class).addPerson(person)).withSelfRel());

        });
        return personList;
    }

    /*
    *//**
     * Deletes a Person .
     *
     * @return the list
     *//*
    @DeleteMapping(path = "/person/{id}", consumes = "application/json", produces = "application/json")
    public List<Person> deletePerson(@PathVariable Long id) {
        System.out.println("Deleted Person is " + id);
        return service.deletePayee(id);
    }

    *//**
     * Update the Person List.
     *
     * @return the list
     *//*
    @PutMapping(path = "/person/{id}", consumes = "application/json", produces = "application/json")
    public List<Person> updatePerson(@RequestBody Person personDetails, @PathVariable Long id) {
        if (null != service.getPerson(personDetails.getPersonId())) {
            System.out.println("Updated Person is " + personDetails.getFirst_name());
            return service.updatePayee(personDetails);
        } else {
            return addPerson(personDetails);
        }
    }*/
}
