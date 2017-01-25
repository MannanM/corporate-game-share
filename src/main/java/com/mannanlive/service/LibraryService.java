package com.mannanlive.service;

import com.mannanlive.entity.GameState;
import com.mannanlive.entity.LibraryEntity;
import com.mannanlive.model.library.Library;
import com.mannanlive.model.library.LibraryGameData;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class LibraryService extends AbstractGameService {
    public Library getUsersLibrary(Authentication user, Long userId) {
        validateUserHasAccess(user, userId);

        List<LibraryEntity> entities = libraryRepository.findByUserIdOrderByCreated(userId);
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
            libraryRepository.save(libraryEntity);
        } catch (DataIntegrityViolationException exception) {
            throw new HttpClientErrorException(BAD_REQUEST,
                    "You have already created this game to your library.");
        }
    }

    public void updateGameInLibrary(Authentication user, Long libraryId, LibraryGameData data) {
        LibraryEntity entity = getAndValidateGameStateChange(user, libraryId, data);

        //if state has changed
        if (data.getAttributes().getState() != entity.getState()) {
            entity.setState(data.getAttributes().getState());
            entity.setRemoved(entity.getState() == GameState.SOLD ? LocalDateTime.now() : null);
            libraryRepository.save(entity);
        }
    }

    private LibraryEntity getAndValidateGameStateChange(Authentication user, Long libraryId, LibraryGameData data) {
        LibraryEntity entity = libraryRepository.findOne(libraryId);
        if (entity == null) {
            throw new HttpClientErrorException(NOT_FOUND, format("No library game exists with id '%d'.", libraryId));
        }
        validateLibraryIsUsers(user, entity.getUser().getId());

        if (entity.getState() == GameState.ON_LOAN) {
            throw new HttpClientErrorException(BAD_REQUEST,
                    format("You can't modify '%s' while it is loaned to '%s'.",
                            entity.getGame().getName(),
                            entity.getBorrower().getName()));
        }

        if (data.getAttributes().getState() == GameState.ON_LOAN) {
            throw new HttpClientErrorException(BAD_REQUEST,
                    format("You can't set '%s' to an on-loan state.", entity.getGame().getName()));
        }
        return entity;
    }
}
