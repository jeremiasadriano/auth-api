package com.my.knowlodge.knowlodge01.models.dto;

import com.my.knowlodge.knowlodge01.models.Person;

import java.time.LocalDate;
import java.time.Period;

public record PersonResponse(long id, String name, String email, int age) {
    public static PersonResponse convert(Person person) {
        return new PersonResponse(person.getId(), person.getName(), person.getEmail(), age(person.getBornDate()));
    }

    public static int age(LocalDate person) {
        int year = Integer.parseInt(person.toString().substring(0, 4));
        int month = Integer.parseInt(person.toString().substring(5, 7));
        int day = Integer.parseInt(person.toString().substring(8, 10));
        LocalDate birthYear = LocalDate.of(year, month, day);
        LocalDate today = LocalDate.now();
        return Period.between(birthYear, today).getYears();
    }
}
