package com.mannanlive.controller;

import com.mannanlive.model.usermodel.UserPrincipal;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/users", produces = "application/vnd.api+json")
public class UserController {

    @Secured("ROLE_USER")
    @RequestMapping(method = RequestMethod.GET, value = "/me")
    @ApiOperation(value = "View details about yourself")
    public UserPrincipal whoAmI(Authentication user) {
        return (UserPrincipal)user.getPrincipal();
    }
}
