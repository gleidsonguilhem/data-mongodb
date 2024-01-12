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

    public static String obscurer(String ccNumber) {
        if(ccNumber.length() > 16 || ccNumber.length() < 12) {
            return "Invalid credit card number";
        }
        String ccObscured = "";
        for(int x = 0; x < ccNumber.length()-4; x++) {
            ccObscured += "X";
        }
        ccObscured += ccNumber.substring(ccNumber.length()-4);
        return ccObscured;
    }

}