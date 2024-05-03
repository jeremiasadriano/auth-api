package com.my.knowlodge.knowlodge01.models.dto;

import java.time.LocalDate;

public record PersonRequest(String name, String email, String password, LocalDate age) {
}
