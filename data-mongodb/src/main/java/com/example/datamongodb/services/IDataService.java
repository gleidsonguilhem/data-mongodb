package com.example.datamongodb.services;

import com.example.datamongodb.model.Person;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IDataService {
    Person save(String id, Person person);
    Optional<Person> getPersonById(String id);
    List<Person> findPersonByNameStartsWith(String name);
    List<Person> getPersonByAgeBetween(Integer minAge, Integer maxAge);
    Page<Person> getAllPersons(Integer number, Integer size);
    void deleteById(String id);
}