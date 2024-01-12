package com.example.datamongodb.repositories;

import com.example.datamongodb.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

@DataMongoTest
class DataRepositoryTest {

    @MockBean
    private DataRepository mockRepository;

    private Person person;
    private List<Person> persons;

    private Page<Person> page;

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
    void findPersonByNameStartsWith() {
        Mockito.when(mockRepository.findPersonByNameStartsWith("Sashie Cox")).thenReturn(persons);

        assertEquals(mockRepository.findPersonByNameStartsWith("Sashie Cox").get(0).getName(), "Sashie Cox");
        assertEquals(mockRepository.findPersonByNameStartsWith("Sashie Cox").get(0).getCity(), "Melbourne");
        assertEquals(mockRepository.findPersonByNameStartsWith("Sashie Cox").get(0).getState(), "VIC");
        assertEquals(mockRepository.findPersonByNameStartsWith("Sashie Cox").get(0).getZipCode(), "3142");
        assertEquals(mockRepository.findPersonByNameStartsWith("Sashie Cox").get(0).getEmail(), "sachie.ccc@gmail.com");
        assertEquals(mockRepository.findPersonByNameStartsWith("Sashie Cox").get(0).getAge(), 36);
        assertEquals(mockRepository.findPersonByNameStartsWith("Sashie Cox").get(0).getAddress(), "190, Dream Street");
    }

    @Test
    void getPersonByAgeBetween() {
        Mockito.when(mockRepository.getPersonByAgeBetween(34,37)).thenReturn(persons);
        assertEquals(mockRepository.getPersonByAgeBetween(34,37).get(0).getAge(), 36);
    }

    @Test
    void findById() {
        if(mockRepository.findById("650981de30a7fe122822710a").isPresent()) {
            Mockito.when(mockRepository.findById("650981de30a7fe122822710a").get()).thenReturn(person);
            assertEquals(mockRepository.findById("650981de30a7fe122822710a").get().getId(), "650981de30a7fe122822710a");
        }
    }

    @Test
    void updatePerson() {
        Mockito.when(mockRepository.save(person)).thenReturn(person);
        assertEquals(mockRepository.save(person).getEmail(), "sachie.ccc@gmail.com");
    }

    @Test
    void insertPerson() {
        Mockito.when(mockRepository.insert(person)).thenReturn(person);
        assertEquals(mockRepository.insert(person).getState(), "VIC");
    }

    @Test
    void deletePerson() {
        mockRepository.deleteById("650981de30a7fe122822710a");
        Mockito.verify(mockRepository).deleteById("650981de30a7fe122822710a");
    }

    @Test
    void getAllPersons() {
        Mockito.when(mockRepository.findAll()).thenReturn(persons);
        assertEquals(mockRepository.findAll().get(0).getName(), "Sashie Cox");
    }

    @Test
    void findAll() {
        Mockito.when(mockRepository.findAll()).thenReturn(persons);

        assertEquals(mockRepository.findAll().get(0).getName(), "Sashie Cox");
        assertEquals(mockRepository.findAll().get(0).getEmail(), "sachie.ccc@gmail.com");
    }

}