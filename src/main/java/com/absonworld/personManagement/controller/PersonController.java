package com.absonworld.personManagement.controller;

import com.absonworld.personManagement.entity.Person;
import com.absonworld.personManagement.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PersonController {

    @Autowired
    PersonService service;

    /**
     * Get all users list.
     *
     * @return the list
     */
    @GetMapping("/persons")
    public List<String> getAllPersons() {
        List<String> userList = new ArrayList<>();
        userList.add("Abhishek");
        userList.add("happy");
        userList.add("Lavy");
        return userList;
    }

    @PostMapping(path = "/addPerson", consumes = "application/json", produces = "application/json")
    public List<Person> addPerson(@RequestBody Person personDetails) {
        System.out.println("Payee is " + personDetails.getFirst_name());
        return service.addPayee(personDetails);
    }
}
