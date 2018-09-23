package com.mannanlive.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/users", produces = "application/vnd.api+json")
public class UserController {
    @RequestMapping(method = RequestMethod.GET, value = "/me")
    @ApiOperation(value = "View details about yourself")
    public Authentication whoAmI(Authentication user) {
        return user;
    }
}
