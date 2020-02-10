package com.absonworld.personManagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class personController {

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
}
