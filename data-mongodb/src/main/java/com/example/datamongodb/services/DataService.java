package com.example.datamongodb.services;

import com.example.datamongodb.model.Person;
import com.example.datamongodb.repositories.DataRepository;
import com.example.datamongodb.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DataService implements IDataService {

    @Autowired
    DataRepository dataRepository;
    @Override
    public Person save(String id, Person person) {
        if(id != null) {
            Optional<Person> dbPerson = dataRepository.findById(id);
            if(dbPerson.isPresent()) {
                Person builtPerson = Utility.buildPerson(id, person, dbPerson.get());
                return dataRepository.save(builtPerson);
            }
        }else {
            return dataRepository.insert(person);
        }
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