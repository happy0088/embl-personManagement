package com.absonworld.personManagement.controller;

import com.absonworld.personManagement.entity.Person;
import com.absonworld.personManagement.service.PersonService;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PersonController {

    PersonService service;

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
    @GetMapping("/getAll")
    public List<Person> getAllPersons() {
        return service.getAllPersons();
    }

    /**
     * Add New Person.
     *
     * @return the person list
     */
    @PostMapping(path = "/addPerson", consumes = "application/json", produces = "application/json")
    public List<Person> addPerson(@RequestBody Person personDetails) {
        System.out.println("Person Added is " + personDetails.getFirst_name());
        return service.addPerson(personDetails);
    }

    /**
     * Deletes a Person .
     *
     * @return the list
     */
    @PostMapping(path = "/deletePerson", consumes = "application/json", produces = "application/json")
    public List<Person> deletePerson(@RequestBody Person personDetails) {
        System.out.println("Deleted Person is " + personDetails.getFirst_name());
        return service.deletePayee(personDetails);
    }

    /**
     * Update the Person List.
     *
     * @return the list
     */
    @PostMapping(path = "/updatePerson", consumes = "application/json", produces = "application/json")
    public List<Person> updatePerson(@RequestBody Person personDetails) {
        System.out.println("Deleted Person is " + personDetails.getFirst_name());
        return service.updatePayee(personDetails);
    }

}
