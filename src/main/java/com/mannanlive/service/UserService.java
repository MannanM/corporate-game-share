package com.mannanlive.service;

import com.google.common.collect.ImmutableSet;
import com.mannanlive.entity.UserEntity;
import com.mannanlive.model.usermodel.User;
import com.mannanlive.model.usermodel.UserData;
import com.mannanlive.model.usermodel.Users;
import com.mannanlive.repository.RoleRepository;
import com.mannanlive.repository.UserRepository;
import com.mannanlive.translator.UserTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roles;

    @Autowired
    private UserTranslator translator;

    public void validate(Authentication authentication, String organisation) {
        User principal = (User) authentication.getPrincipal();
        String usersOrganisation = principal.getData().getAttributes().getOrganisation();

        if (!organisation.equals(usersOrganisation)) {
            throw new BadCredentialsException(format(
                    "User is trying to access '%s' but is only authorised to access '%s'.",
                    organisation, usersOrganisation));
        }
    }

    public Users findUsersInOrganisation(String organisation) {
        List<UserEntity> users = repository.findByOrganisation(organisation);
        List<UserData> userList = users.stream().map(
                entity -> translator.translate(entity).getData()
        ).collect(Collectors.toList());
        return new Users(userList);
    }

    public UserDetails registerOrLogIn(String authProvider, String authId, String name, String email, String picture) {
        UserEntity entity = persist(authProvider, authId, name, email, picture);
        return translator.translateToPrincipal(repository.save(entity));
    }

    public UserEntity persist(String authProvider, String authId, String name, String email, String picture) {
        UserEntity entity = repository.findByAuthProviderAndAuthId(authProvider, authId);
        if (entity != null) {
            entity.login();
        } else {
            entity = new UserEntity(authProvider, authId, name, email, picture);
            entity.setRoles(ImmutableSet.of(
                    roles.findByName("ROLE_USER"),
                    roles.findByName("ROLE_" + authProvider.toUpperCase())));
        }
        return entity;
    }

    public List<GrantedAuthority> getGrantedAuthorities(String authProvider, String authId) {
        UserEntity entity = repository.findByAuthProviderAndAuthId(authProvider, authId);
        return new ArrayList<>(entity.getRoles());
    }

    public User getUser(User principal, long userId) {
        Optional<UserEntity> optional = repository.findById(userId);
        if (optional.isPresent()) {
            User translate = translator.translate(optional.get());
            if (principal == null || !String.valueOf(userId).equals(principal.getData().getId())) {
                translate.getData().getAttributes().setEmail(null);
            }
            return translate;
        }
        //Todo: make this quieter
        throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "No user with id: " + userId);
    }
}
