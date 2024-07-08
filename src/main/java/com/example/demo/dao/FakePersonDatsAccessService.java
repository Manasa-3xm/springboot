package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.IntStream;

@Repository("fakeDao")
class FakePersonDataAccessService implements PersonDao {
    private static List<Person> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id,person.getName()));
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return DB;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return DB.stream().filter(person -> person.getId().equals(id)).findFirst();
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person>  personMayBe = selectPersonById(id);
        if(personMayBe.isEmpty()){
            return 0;

        }
        DB.remove(personMayBe.get());
        return 1;
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        // Find the index of the person in DB based on the UUID
        OptionalInt indexOptional = IntStream.range(0, DB.size())
                .filter(i -> DB.get(i).getId().equals(id))
                .findFirst();

        // If index is found, update the person at that index
        if (indexOptional.isPresent()) {
            int index = indexOptional.getAsInt();
            DB.set(index, person); // Replace the existing person with the updated person
            return 1; // Return 1 to indicate success
        }

        return 0; // Return 0 to indicate failure (person with given ID not found)
    }

}
