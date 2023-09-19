package com.example.datamongodb.repositories;

import com.example.datamongodb.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface DataRepository extends MongoRepository<Person, String>  {
    List<Person> findPersonByNameStartsWith(String name);

    @Query(value = "{'age':{$gte: ?0, $lte: ?1}}", fields = "{id: 1, name: 1, age: 1}")
    List<Person> getPersonByAgeBetween(Integer minAge, Integer maxAge);
}