package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RestController
public class PersonContreoller {
    private final PersonService personService;

    @Autowired
    public PersonContreoller(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public void addPerson(@NotNull @RequestBody Person person) {
        personService.addPerson(person);
    }

    @GetMapping
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable UUID id) {
        return personService.getPersonById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deletePersonById(@PathVariable UUID id) {
        personService.deletePerson(id);
    }

    @PutMapping("/{id}")
    public void updatePerson(@PathVariable("id") UUID id,@NotNull @RequestBody Person personToUpdate) {
        personService.updatePerson(id, personToUpdate);
    }
}
