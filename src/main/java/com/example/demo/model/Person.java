package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private UUID id;
    @NotBlank
    @JsonProperty("name")
    private String name;


    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
