package com.mannanlive.controller;

import com.mannanlive.model.usermodel.UserPrincipal;
import org.springframework.security.core.Authentication;

public class Constants {
    public static final String JSON_API = "application/vnd.api+json";

    public static UserPrincipal extractUser(Authentication authentication) {
        return authentication == null ? null : (UserPrincipal)authentication.getPrincipal();
    }
}
