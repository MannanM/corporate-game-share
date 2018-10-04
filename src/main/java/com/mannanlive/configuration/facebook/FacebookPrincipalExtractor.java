package com.mannanlive.configuration.facebook;

import com.mannanlive.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class FacebookPrincipalExtractor implements PrincipalExtractor {
    public static final String FACEBOOK = "Facebook";
    @Autowired
    private UserService userService;

    @Override
    public Object extractPrincipal(Map<String, Object> map) {
        String id = (String) map.get("id");
        String name = (String) map.get("name");
        String email = (String) map.get("email");
        String picture = ((Map<String, Map<String, String>>) map.get("picture")).get("data").get("url");

        return userService.registerOrLogIn(FACEBOOK, id, name, email, picture);
    }
}
