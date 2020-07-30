package org.example.carsellingservice.domain;

import org.springframework.security.core.GrantedAuthority;

//todo разобраться с таблицей
public enum Role implements GrantedAuthority {
    USER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
