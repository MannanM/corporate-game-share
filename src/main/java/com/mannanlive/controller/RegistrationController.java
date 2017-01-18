package com.mannanlive.controller;

import com.mannanlive.model.usermodel.NewUserRequest;
import com.mannanlive.model.usermodel.User;
import com.mannanlive.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/", method = RequestMethod.POST, produces = "application/vnd.api+json")
public class RegistrationController {

    @Autowired
    private UserService service;

    @RequestMapping(path = "/register")
    public User register(@RequestBody NewUserRequest newUserRequest) {
        return service.registerNewUser(newUserRequest);
    }

    @RequestMapping(path = "/forgot-password")
    public void forgotPasword() {
    }
}
