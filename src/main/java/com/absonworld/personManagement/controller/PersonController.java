package com.absonworld.personManagement.controller;

import com.absonworld.personManagement.entity.Person;
import com.absonworld.personManagement.entity.PersonResponse;
import com.absonworld.personManagement.service.PersonService;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
        List<Person> allPersons = service.getAllPersons();
        response.setPerson(allPersons);
        response.add(linkTo(methodOn(PersonController.class).getAllPerson()).withSelfRel());
        return response;
    }

    /**
     * Get Person Details.
     *
     * @return the string
     */
    @GetMapping(path = "/person/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, "application/hal+json"})
    public Person getPerson(@PathVariable Long id) {
        Person person = service.getPerson(id);
        person.add(linkTo(methodOn(PersonController.class).getPerson(id)).withSelfRel());
        Link deleteLink = linkTo(methodOn(PersonController.class)
                .deletePerson(id)).withRel("delete-person").withType("DELETE");
        Link updateLink = linkTo(methodOn(PersonController.class)
                .updatePerson(null, id)).withRel("update-person").withType("PUT");
        person.add(deleteLink, updateLink);
        return person;
    }

    /**
     * Add New Person.
     *
     * @return the person list
     */
    @PostMapping(path = "/person", consumes = "application/json", produces = {MediaType.APPLICATION_JSON_VALUE, "application/hal+json"})
    public List<Person> addPerson(@RequestBody Person personDetails) {
        List<Person> personList = service.addPerson(personDetails);
        System.out.println("Person Added is " + personDetails.getFirst_name());
        personList.stream().forEach(person -> {
            Link deleteLink = linkTo(methodOn(PersonController.class)
                    .deletePerson(person.getPersonId())).withRel("delete-person").withType("DELETE");
            Link updateLink = linkTo(methodOn(PersonController.class)
                    .updatePerson(null, person.getPersonId())).withRel("update-person").withType("PUT");
            person.add(deleteLink, updateLink);
            person.add(linkTo(methodOn(PersonController.class).addPerson(person)).withSelfRel());

        });
        return personList;
    }


    /**
     * Deletes a Person .
     *
     * @return the list
     */
    @DeleteMapping(path = "/person/{id}", consumes = "application/json", produces = "application/json")
    public List<Person> deletePerson(@PathVariable Long id) {
        List<Person> personList = service.deletePayee(id);
        System.out.println("Deleted Person is " + id);
        personList.stream().forEach(person -> {
            person.add(linkTo(methodOn(PersonController.class).deletePerson(id)).withSelfRel());
            Link updateLink = linkTo(methodOn(PersonController.class)
                    .updatePerson(null, person.getPersonId())).withRel("update-person").withType("PUT");
            person.add(updateLink);

        });
        return personList;
    }

    /*
     * Update the Person List.
     *
     * @return the list
     *
     */

    @PutMapping(path = "/person/{id}", consumes = "application/json", produces = "application/json")
    public List<Person> updatePerson(@RequestBody Person personDetails, @PathVariable Long id) {
        List<Person> personList;
        if (null != service.getPerson(personDetails.getPersonId())) {
            System.out.println("Updated Person is " + personDetails.getFirst_name());
            personList = service.updatePayee(personDetails);
            personList.stream().forEach(person -> {
                Link deleteLink = linkTo(methodOn(PersonController.class)
                        .deletePerson(person.getPersonId())).withRel("delete-person").withType("DELETE");
                person.add(linkTo(methodOn(PersonController.class).deletePerson(id)).withSelfRel(), deleteLink);
            });
        } else {
            personList = addPerson(personDetails);
        }

        return personList;
    }
}
