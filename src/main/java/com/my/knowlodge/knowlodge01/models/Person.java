package com.my.knowlodge.knowlodge01.models;

import com.my.knowlodge.knowlodge01.models.enums.UserRoles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "person_table")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(sequenceName = "person_seq", name = "person_gen", allocationSize = 1, initialValue = 2)
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_gen")
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "age", nullable = false)
    private LocalDate bornDate;
    @Column(name = "userRoles", nullable = false)
    private UserRoles roles;

    public Person(String name, String email, String password, LocalDate bornDate, UserRoles role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.bornDate = bornDate;
        this.roles = role;
    }
}
