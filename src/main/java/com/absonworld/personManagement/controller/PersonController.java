package com.absonworld.personManagement.controller;

import com.absonworld.personManagement.entity.Person;
import com.absonworld.personManagement.entity.PersonResponse;
import com.absonworld.personManagement.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        response.setPersons(service.getAllPersons());
        return response;
    }

    /**
     * Get Person Details.
     *
     * @return the string
     */
    @GetMapping("/person/{id}")
    public Person getPerson(@PathVariable Long id) {
        return service.getPerson(id);
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
    @PutMapping(path = "/updatePerson", consumes = "application/json", produces = "application/json")
    public List<Person> updatePerson(@RequestBody Person personDetails) {
        if (null != service.getPerson(personDetails.getPersonId())) {
            System.out.println("Updated Person is " + personDetails.getFirst_name());
            return service.updatePayee(personDetails);
        } else {
            return addPerson(personDetails);
        }
    }

}
