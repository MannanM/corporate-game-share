package com.mannanlive.controller;

import com.mannanlive.model.usermodel.User;
import com.mannanlive.model.usermodel.UserPrincipal;
import com.mannanlive.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.mannanlive.controller.Constants.JSON_API;
import static com.mannanlive.controller.Constants.extractUser;

@RestController
@RequestMapping(path = "/v1/users", produces = JSON_API)
public class UserController {

    @Autowired
    private UserService userService;

    @Secured("ROLE_USER")
    @RequestMapping(method = RequestMethod.GET, value = "/me")
    @ApiOperation(value = "View details about yourself")
    public UserPrincipal whoAmI(Authentication user) {
        return extractUser(user);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    @ApiOperation(value = "View details about someone else")
    public User getUser(Authentication user, @PathVariable long userId) {
        return userService.getUser(extractUser(user), userId);
    }
}
