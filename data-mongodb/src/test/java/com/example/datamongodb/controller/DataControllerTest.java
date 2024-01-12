package com.example.datamongodb.controller;

import com.example.datamongodb.model.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(DataController.class)
class DataControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DataController mockController;
    private Person person;
    private List<Person> persons;
    private String strPerson;

    @BeforeEach
    void setUp() throws JsonProcessingException {
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

        ObjectMapper objectMapper = new ObjectMapper();
        strPerson = objectMapper.writeValueAsString(person);
    }

    @Test
    void postPerson() throws Exception {
        Mockito.when(mockController.postPerson(person)).thenReturn(ResponseEntity.status(200).build());
        this.mockMvc.perform(post("/data").contentType(MediaType.APPLICATION_JSON).content(strPerson).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().is2xxSuccessful());
    }

    @Test
    void getPersonById() throws Exception {
        Mockito.when(mockController.getPersonById("650981de30a7fe122822710a")).thenReturn(ResponseEntity.status(200).build());
        this.mockMvc.perform(get("/data/id=650981de30a7fe122822710a").accept(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful());
    }

    @Test
    void getPersonByNameStartsWith() throws Exception {
        Mockito.when(mockController.getPersonByNameStartsWith("Sashie Cox")).thenReturn(ResponseEntity.status(200).build());
        this.mockMvc.perform(get("/data?name=Sashie Cox").accept(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful());
    }

    @Test
    void getPersonByAgeBetween() throws Exception {
        Mockito.when(mockController.getPersonByAgeBetween(32,37)).thenReturn(ResponseEntity.status(200).build());
        this.mockMvc.perform(get("/data/age?minAge=32&maxAge=36").contentType(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful());
    }

    @Test
    void getAllPersons() throws Exception{
        Mockito.when(mockController.getAllPersons(0,4)).thenReturn(ResponseEntity.status(200).build());
        this.mockMvc.perform(get("/data/all&number=0&size=4").contentType(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful());
    }

    @Test
    void updatePersonById() throws Exception {
        Mockito.when(mockController.updatePersonById("650981de30a7fe122822710a", person)).thenReturn(ResponseEntity.status(200).build());
        this.mockMvc.perform(put("/data/{id}","650981de30a7fe122822710a").contentType(MediaType.APPLICATION_JSON).content(strPerson).accept(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful());

    }

    @Test
    void deletePersonById() throws Exception{
        Mockito.when(mockController.deletePersonById("650981de30a7fe122822710a")).thenReturn(ResponseEntity.status(200).build());
        this.mockMvc.perform(delete("/data/{id}","650981de30a7fe122822710a")).andExpect(status().is2xxSuccessful());
    }
}