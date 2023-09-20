package com.example.datamongodb.utility;

import com.example.datamongodb.model.Person;

public class Utility {

    public static Person buildPerson(String id, Person updatedPerson, Person dbPerson) {
        return Person.builder()
                .id(id)
                .name(updatedPerson.getName() != null ? updatedPerson.getName() : dbPerson.getName())
                .address(updatedPerson.getAddress() != null ? updatedPerson.getAddress() : dbPerson.getAddress())
                .city(updatedPerson.getCity() != null ? updatedPerson.getCity() : dbPerson.getCity())
                .state(updatedPerson.getState() != null ? updatedPerson.getState() : dbPerson.getState())
                .age(updatedPerson.getAge() != null ? updatedPerson.getAge() : dbPerson.getAge())
                .zipCode(updatedPerson.getZipCode() != null ? updatedPerson.getZipCode() : dbPerson.getZipCode())
                .email(updatedPerson.getEmail() != null ? updatedPerson.getEmail() : dbPerson.getEmail())
                .build();
    }

}
