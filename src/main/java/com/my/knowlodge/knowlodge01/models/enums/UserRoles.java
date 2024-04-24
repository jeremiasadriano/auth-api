package com.my.knowlodge.knowlodge01.models.enums;

import lombok.Getter;

@Getter
public enum UserRoles {
    USER(1), ADMIN(2), MANAGER(3);
    private final Integer value;

    UserRoles(Integer roles) {
        this.value = roles;
    }
}
