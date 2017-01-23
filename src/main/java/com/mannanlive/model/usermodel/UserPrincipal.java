package com.mannanlive.model.usermodel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserPrincipal extends User implements UserDetails {

    private static final long serialVersionUID = 1L;

    public UserPrincipal(User user) {
        super(user);
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getData().getAttributes().getRoles();
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return getData().getAttributes().getPassword();
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return getData().getAttributes().getEmail();
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

}
