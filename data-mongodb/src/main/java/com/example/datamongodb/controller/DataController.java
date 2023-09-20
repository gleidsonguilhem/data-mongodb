package com.example.datamongodb.controller;

import com.example.datamongodb.constants.Constants;
import com.example.datamongodb.model.Person;
import com.example.datamongodb.services.IDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/data", produces = MediaType.APPLICATION_JSON_VALUE)
public class DataController {

    @Autowired
    IDataService iDataService;

    @PostMapping()
    public ResponseEntity<?> postPerson(@RequestBody Person person) {
        try {
            Person savedPerson = iDataService.save(person);
            return ResponseEntity.status(HttpStatus.OK).body(savedPerson);
        }catch (Exception exception) {
            //System.out.println(exception.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Constants.DATA_ERROR);
        }
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getPersonById(@PathVariable String id) {
        try {
            Optional<Person> person = iDataService.getPersonById(id);
            if(person.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(person);
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.GET_DATA_ERROR);
            }
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.DATA_ERROR);
        }
    }
    @GetMapping()
    /*
    Create method getPersonNameStartsWith
    Return: ResponseEntity<?>
    Param: "name"
     */
    public ResponseEntity<?> getPersonByNameStartsWith(@RequestParam("name") String name) {
        List<Person> persons = iDataService.findPersonByNameStartsWith(name);
        try {
            if(!persons.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(persons);
            }else {
                return ResponseEntity.status(HttpStatus.OK).body(Constants.GET_DATA_ERROR);
            }
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body(Constants.DATA_ERROR);
        }
    }

    @GetMapping(value = "/age")
    public ResponseEntity<?> getPersonByAgeBetween(@RequestParam("minAge") Integer minAge,
                                                   @RequestParam("maxAge") Integer maxAge) {
        List<Person> persons = iDataService.getPersonByAgeBetween(minAge, maxAge);
        try {
            if(!persons.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(persons);
            }else {
                return ResponseEntity.status(HttpStatus.OK).body(Constants.GET_DATA_ERROR);
            }
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body(Constants.DATA_ERROR);
        }
    }

    @GetMapping(value = "/all")
    public ResponseEntity<?> getAllPersons() {
        List<Person> persons = iDataService.getAllPersons();
        try {
            if(!persons.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(persons);
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.GET_DATA_ERROR);
            }
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.DATA_ERROR);
        }
    }

}