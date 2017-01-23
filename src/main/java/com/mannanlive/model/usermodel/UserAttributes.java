package com.mannanlive.model.usermodel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mannanlive.entity.RoleEntity;
import com.mannanlive.model.ResourceSummary;

import java.util.HashSet;
import java.util.Set;

public class UserAttributes {
    private String name;
    private String organisation;
    private String email;
    private Set<ResourceSummary> consoles = new HashSet<>();

    @JsonIgnore
    private String password;
    @JsonIgnore
    private Set<RoleEntity> roles = new HashSet<>();

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getOrganisation() {
        return organisation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    public Set<ResourceSummary> getConsoles() {
        return consoles;
    }

    public void setConsoles(Set<ResourceSummary> consoles) {
        this.consoles = consoles;
    }
}
