package com.my.knowlodge.knowlodge01.models;

import com.my.knowlodge.knowlodge01.models.infra.UserRoles;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

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
    @Email(message = "The inputted email is not allowed")
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "age", nullable = false)
    private LocalDate bornDate;
    @Column(name = "userRoles", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoles roles;

    public Person(String name, String email, String password, LocalDate bornDate, UserRoles roles) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.bornDate = bornDate;
        this.roles = roles;
    }
}
