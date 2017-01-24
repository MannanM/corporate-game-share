package com.mannanlive.controller;

import com.mannanlive.model.usermodel.Users;
import com.mannanlive.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/organisations", produces = "application/vnd.api+json")
public class OrganisationController {
    @Autowired
    private UserService service;

    @RequestMapping(path = "/{organisation}/users", method = RequestMethod.GET)
    public Users findUsersInOrganisation(Authentication user, @PathVariable String organisation) {
        organisation = organisation.toLowerCase();
        service.validate(user, organisation);
        return service.findUsersInOrganisation(organisation);
    }
}
