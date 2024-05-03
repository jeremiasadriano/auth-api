package com.my.knowlodge.knowlodge01.models.enums;

import lombok.Getter;

@Getter
public enum UserRoles {
    USER("user"), ADMIN("admin"), MANAGER("manager");
    private final String value;

    UserRoles(String roles) {
        this.value = roles;
    }
}
