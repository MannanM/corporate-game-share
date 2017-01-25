package com.mannanlive.service;

import com.mannanlive.entity.UserEntity;
import com.mannanlive.model.usermodel.User;
import com.mannanlive.model.usermodel.UserData;
import com.mannanlive.repository.LibraryRepository;
import com.mannanlive.repository.UserRepository;
import com.mannanlive.translator.LibraryTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.client.HttpClientErrorException;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.NOT_FOUND;

public class AbstractGameService {
    @Autowired
    protected LibraryTranslator translator;

    @Autowired
    protected LibraryRepository libraryRepository;

    @Autowired
    private UserRepository userRepository;

    protected void validateLibraryIsUsers(Authentication user, Long userId) {
        String id = ((User) user.getPrincipal()).getData().getId();
        if (!id.equals(userId.toString())) {
            throw new BadCredentialsException(format(
                    "User is trying to modify user id '%d' library but they are only authorised to modify their own.",
                    userId));
        }
    }

    protected UserData extract(Authentication user) {
        return ((User) user.getPrincipal()).getData();
    }

    protected UserEntity validateUserHasAccess(Authentication user, Long userId) {
        UserEntity entity = findUser(userId);
        String organisation = extract(user).getAttributes().getOrganisation();
        if (!entity.getOrganisation().equals(organisation)) {
            throw new BadCredentialsException(format(
                    "User is trying to access user id '%d' but is only authorised to access users from organisation '%s'.",
                    userId, organisation));
        }
        return entity;
    }

    private UserEntity findUser(Long userId) {
        UserEntity userEntity = userRepository.findOne(userId);
        if (userEntity == null) {
            throw new HttpClientErrorException(NOT_FOUND, format("No user exists with id '%d'.", userId));
        }
        return userEntity;
    }
}
