package com.mannanlive.service;

import com.mannanlive.entity.UserEntity;
import com.mannanlive.model.usermodel.NewUserRequest;
import com.mannanlive.model.usermodel.User;
import com.mannanlive.model.usermodel.UserData;
import com.mannanlive.model.usermodel.Users;
import com.mannanlive.repository.ConsoleRepository;
import com.mannanlive.repository.UserRepository;
import com.mannanlive.translator.UserTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserTranslator translator;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = repository.findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException(format("User '%s' does not exist.", username));
        }
        return translator.translateToPrincipal(user);
    }

    public User registerNewUser(NewUserRequest request) {
        UserEntity userEntity = repository.findByLogin(request.getEmail());

        if (userEntity != null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,
                    format("There is an account with email address '%s'.", request.getEmail()));
        }

        return translator.translate(repository.save(translator.translate(request)));
    }

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
}
