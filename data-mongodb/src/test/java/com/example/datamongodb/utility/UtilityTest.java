package com.example.datamongodb.utility;

import com.example.datamongodb.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
class UtilityTest {

    private Person originalPerson;
    private Person updatedPerson;

    @BeforeEach
    void setUp() {

        originalPerson = Person.builder()
                .id("650981de30a7fe122822710a")
                .name("Sashie Cox")
                .age(36)
                .address("190, Dream Street")
                .city("Melbourne")
                .state("VIC")
                .zipCode("3142")
                .email("sachie.ccc@gmail.com")
                .build();

        updatedPerson = Person.builder()
                .name("Sashie Cox G.")
                .age(30)
                .address("883, Collins Street")
                .build();
    }

    @Test
    public void obscurerPositive() {
        String ccNumber = "1234123412346789";
        String ccNum = Utility.obscurer(ccNumber);
        assertEquals(ccNum, "XXXXXXXXXXXX6789");
    }

    @Test
    public void buildPerson() {
        Person builtPerson = Utility.buildPerson("650981de30a7fe122822710a", updatedPerson, originalPerson);

        assertEquals(builtPerson.getName(), updatedPerson.getName());
        assertEquals(builtPerson.getAge(), updatedPerson.getAge());
        assertEquals(builtPerson.getAddress(), updatedPerson.getAddress());
        assertEquals(builtPerson.getCity(), originalPerson.getCity());
        assertEquals(builtPerson.getState(), originalPerson.getState());
        assertEquals(builtPerson.getZipCode(), originalPerson.getZipCode());
        assertEquals(builtPerson.getEmail(), originalPerson.getEmail());
    }

    @Test
    public void obscurerNegative() {
        String ccNumber = "12341234";
        String ccNum = Utility.obscurer(ccNumber);
        assertEquals(ccNum, "Invalid credit card number");
    }


}