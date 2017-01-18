package com.mannanlive.controller;

import com.mannanlive.model.usermodel.User;
import com.mannanlive.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/organisations/{organisation}", produces = "application/vnd.api+json")
public class OrganisationController {
    //todo: you should only be able to access this endpoint if you are authenticated and your organisation matches this

    @Autowired
    private UserService service;

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    //todo: make this a JSON API compliant result
    public List<User> register(@PathVariable String organisation) {
        return service.findUsersInOrganisation(organisation);
    }
}
