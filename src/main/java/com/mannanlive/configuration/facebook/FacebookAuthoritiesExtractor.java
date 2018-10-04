package com.mannanlive.configuration.facebook;

import com.mannanlive.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class FacebookAuthoritiesExtractor implements AuthoritiesExtractor {
    public static final String FACEBOOK = "Facebook";
    @Autowired
    private UserService userService;

    @Override
    public List<GrantedAuthority> extractAuthorities(Map<String, Object> map) {
        String id = (String) map.get("id");
        return userService.getGrantedAuthorities(FACEBOOK, id);
    }
}
