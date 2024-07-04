package com.my.knowlodge.knowlodge01.models.dto;

import com.my.knowlodge.knowlodge01.models.Person;

import java.time.LocalDate;
import java.time.Period;

public record PersonResponse(long id, String name, String email, int age) {
    public static PersonResponse convert(Person person) {
        return new PersonResponse(person.getId(), person.getName(), person.getEmail(), age(person.getBornDate()));
    }

    public static int age(LocalDate person) {
        return Period.between(person, LocalDate.now()).getYears();
    }
}
