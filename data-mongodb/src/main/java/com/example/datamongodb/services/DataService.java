package com.example.datamongodb.services;

import com.example.datamongodb.model.Person;
import com.example.datamongodb.repositories.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DataService implements IDataService {

    @Autowired
    DataRepository dataRepository;
    @Override
    public Person save(Person person) {
        return dataRepository.save(person);
    }
    @Override
    public Optional<Person> getPersonById(String id) {
        return dataRepository.findById(id);
    }
    @Override
    public List<Person> findPersonByNameStartsWith(String name) {
        //Return method findAll from dataRepository
        return dataRepository.findPersonByNameStartsWith(name);
    }
    @Override
    public List<Person> getPersonByAgeBetween(Integer minAge, Integer maxAge) {
        return dataRepository.getPersonByAgeBetween(minAge, maxAge);
    }

    @Override
    public List<Person> getAllPersons() {
        return dataRepository.findAll();
    }

}