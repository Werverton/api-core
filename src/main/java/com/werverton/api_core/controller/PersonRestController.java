package com.werverton.api_core.controller;

import com.werverton.api_core.model.Person;
import com.werverton.api_core.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonRestController {

    @Autowired
    PersonRepo personRepo;

    @PostMapping("/addPerson")
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        Person person1 = personRepo.save(person);
        return ResponseEntity.ok(person1);
    }

    @GetMapping("/getPersonById/{id}")
    public Person getPersonById(@PathVariable long id) {
        return personRepo.findById(id).orElse(null);
    }

    @GetMapping("/getAllPersons")
    public List<Person> getAllPersons() {
        return personRepo.findAll();
    }
}
