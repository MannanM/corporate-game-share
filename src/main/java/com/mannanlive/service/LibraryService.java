package com.mannanlive.service;

import com.mannanlive.entity.GameState;
import com.mannanlive.entity.LibraryEntity;
import com.mannanlive.entity.UserEntity;
import com.mannanlive.model.library.Library;
import com.mannanlive.model.library.LibraryGameData;
import com.mannanlive.model.usermodel.User;
import com.mannanlive.repository.LibraryRepository;
import com.mannanlive.repository.UserRepository;
import com.mannanlive.translator.LibraryTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class LibraryService {

    @Autowired
    private LibraryRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LibraryTranslator translator;

    public Library getUsersLibrary(Authentication user, Long userId) {
        UserEntity userEntity = findUser(userId);
        validateUserHasAccess(user, userId, userEntity);

        List<LibraryEntity> entities = repository.findByUserIdOrderByAdded(userId);
        List<LibraryGameData> collect = entities
                .stream()
                .map(entity -> translator.translate(entity))
                .collect(Collectors.toList());

        return new Library(collect);
    }

    public void addGameToLibrary(Authentication user, Long userId, LibraryGameData data) {
        validateLibraryIsUsers(user, userId);

        LibraryEntity libraryEntity = translator.translateNew(userId, data);
        try {
            repository.save(libraryEntity);
        } catch (DataIntegrityViolationException exception) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,
                    "You have already added this game to your library.");
        }
    }

    public void updateGameInLibrary(Authentication user, Long libraryId, LibraryGameData data) {
        LibraryEntity entity = getAndValidateGameStateChange(user, libraryId, data);

        //if state has changed
        if (data.getAttributes().getState() != entity.getState()) {
            entity.setState(data.getAttributes().getState());
            entity.setRemoved(entity.getState() == GameState.SOLD ? LocalDateTime.now() : null);
            repository.save(entity);
        }
    }

    private LibraryEntity getAndValidateGameStateChange(Authentication user, Long libraryId, LibraryGameData data) {
        LibraryEntity entity = repository.findOne(libraryId);
        if (entity == null) {
            throw new HttpClientErrorException(NOT_FOUND, format("No library game exists with id '%d'.", libraryId));
        }
        validateLibraryIsUsers(user, entity.getUser().getId());

        if (entity.getState() == GameState.ON_LOAN) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,
                    format("You can't modify '%s' while it is loaned to '%s'.",
                            entity.getGame().getName(),
                            entity.getBorrower().getName()));
        }

        if (data.getAttributes().getState() == GameState.ON_LOAN) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,
                    format("You can't set '%s' to an on-loan state.", entity.getGame().getName()));
        }
        return entity;
    }

    private void validateLibraryIsUsers(Authentication user, Long userId) {
        String id = ((User) user.getPrincipal()).getData().getId();
        if (!id.equals(userId.toString())) {
            throw new BadCredentialsException(format(
                    "User is trying to modify user id '%d' library but they are only authorised to modify their own.",
                    userId));
        }
    }

    private void validateUserHasAccess(Authentication user, Long userId, UserEntity userEntity) {
        String organisation = ((User) user.getPrincipal()).getData().getAttributes().getOrganisation();
        if (!userEntity.getOrganisation().equals(organisation)) {
            throw new BadCredentialsException(format(
                "User is trying to access user id '%d' but is only authorised to access users from organisation '%s'.",
                userId, organisation));
        }
    }

    private UserEntity findUser(Long userId) {
        UserEntity userEntity = userRepository.findOne(userId);
        if (userEntity == null) {
            throw new HttpClientErrorException(NOT_FOUND, format("No user exists with id '%d'.", userId));
        }
        return userEntity;
    }
}
