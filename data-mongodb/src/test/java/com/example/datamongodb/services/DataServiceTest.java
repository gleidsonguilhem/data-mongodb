package com.example.datamongodb.services;

import com.example.datamongodb.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class DataServiceTest {

    @MockBean
    private DataService mockService;
    private Person person;
    private List<Person> persons;
    private Page page;

    @BeforeEach
    void setUp() {
        person = Person.builder()
                .id("650981de30a7fe122822710a")
                .name("Sashie Cox")
                .age(36)
                .address("190, Dream Street")
                .city("Melbourne")
                .state("VIC")
                .zipCode("3142")
                .email("sachie.ccc@gmail.com")
                .build();

        persons = new ArrayList<>();
        persons.add(person);
    }

    @Test
    void save() {
        Mockito.when(mockService.save(person.getId(), person)).thenReturn(person);
        assertEquals(mockService.save(person.getId(), person).getEmail(), "sachie.ccc@gmail.com");
    }

    @Test
    void getPersonById() {
        if(mockService.getPersonById("650981de30a7fe122822710a").isPresent()) {
            Mockito.when(mockService.getPersonById("650981de30a7fe122822710a").get()).thenReturn(person);
            assertEquals(mockService.getPersonById("650981de30a7fe122822710a").get().getId(), "650981de30a7fe122822710a");
        }
    }

    @Test
    void findPersonByNameStartsWith() {
        Mockito.when(mockService.findPersonByNameStartsWith("Sashie Cox")).thenReturn(persons);
        assertEquals(mockService.findPersonByNameStartsWith("Sashie Cox").get(0).getName(), "Sashie Cox");
    }

    @Test
    void getPersonByAgeBetween() {
        Mockito.when(mockService.getPersonByAgeBetween(27,32)).thenReturn(persons);
        assertEquals(mockService.getPersonByAgeBetween(27,32).get(0).getAge(), 36);
    }

    @Test
    void getAllPersons() {
        page = new PageImpl<Person>(persons);
        Mockito.when(mockService.getAllPersons(0,4)).thenReturn(page);
        assertEquals(mockService.getAllPersons(0,4).getTotalElements(), 1);
    }

    @Test
    void deleteById() {
        mockService.deleteById("650981de30a7fe122822710a");
        Mockito.verify(mockService).deleteById("650981de30a7fe122822710a");
    }
}