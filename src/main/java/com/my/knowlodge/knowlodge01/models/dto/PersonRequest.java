package com.my.knowlodge.knowlodge01.models.dto;

import java.util.Date;

public record PersonRequest(String name, String email, String password, Date bornDate) {
}
