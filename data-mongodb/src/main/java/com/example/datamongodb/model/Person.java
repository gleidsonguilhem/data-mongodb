package com.example.datamongodb.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Builder
@ToString
@Document
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Person {
    @Id
    private String id;

    private String name;
    private String address;
    private String city;
    private String state;
    private Integer age;

    private String zipCode;

    private String email;
}