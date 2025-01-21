package org.example.userservice.security.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.example.userservice.models.Role;
import org.springframework.security.core.GrantedAuthority;

@JsonDeserialize
public class CustomGrantedAuthority implements GrantedAuthority {
    private String authority;

    public CustomGrantedAuthority(Role role) {
        this.authority=role.getValue();
    }

    public CustomGrantedAuthority() {}

    @Override
    public String getAuthority() {
        return authority;
    }
}
