package com.platform.user.dto;

import com.platform.user.model.UserRole;
import jakarta.validation.constraints.NotEmpty;

import java.util.Set;

public class RoleSelectionRequest {

    @NotEmpty
    private Set<UserRole> roles;

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }
}

