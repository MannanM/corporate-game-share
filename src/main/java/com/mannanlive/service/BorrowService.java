package com.mannanlive.service;

import com.mannanlive.entity.BorrowEntity;
import com.mannanlive.entity.LibraryEntity;
import com.mannanlive.entity.UserEntity;
import com.mannanlive.model.borrow.BorrowData;
import com.mannanlive.model.borrow.Borrowings;
import com.mannanlive.repository.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class BorrowService extends AbstractGameService {
    @Autowired
    private BorrowRepository borrowRepository;

    public Borrowings getBorrowings(Authentication user, Long userId) {
        validateLibraryIsUsers(user, userId);

        List<BorrowEntity> entities = borrowRepository.findActiveByUser(userId);
        List<BorrowData> collect = entities
                .stream()
                .map(entity -> translator.translate(entity))
                .collect(Collectors.toList());
        return new Borrowings(collect);
    }

    public void requestToBorrowGame(Authentication user, Long userId, Long gameId, BorrowData data) {
        validateDates(data.getAttributes().getStartDate(), data.getAttributes().getEndDate());
        long requestorId = validateNigelNoFriends(user, userId);
        UserEntity userEntity = validateUserHasAccess(user, userId);

        LibraryEntity library = libraryRepository.findByUserIdAndGameId(userId, gameId);
        if (library == null) {
            throw new HttpClientErrorException(BAD_REQUEST,
                    format("User '%s' does not have the game '%d' in their library.", userEntity.getName(), gameId));
        }

        BorrowEntity borrowEntity = translator.translateNew(requestorId, data, library);
        borrowRepository.save(borrowEntity);
    }

    private long validateNigelNoFriends(Authentication user, Long userId) {
        long requestorId = Long.parseLong(extract(user).getId());
        if (requestorId == userId) {
            throw new HttpClientErrorException(BAD_REQUEST, "You cant request to borrow a game from yourself.");
        }
        return requestorId;
    }

    private void validateDates(LocalDate startDate, LocalDate endDate) {
        if (startDate.isBefore(LocalDate.now())) {
            throw new HttpClientErrorException(BAD_REQUEST, format(
                    "You must request a start date in the future, not '%s'.", startDate));
        }
        if (!startDate.isBefore(endDate)) {
            throw new HttpClientErrorException(BAD_REQUEST, format(
                    "You must request a duration of time of at least one day, not '%s' to '%s'.", startDate, endDate));
        }
    }
}
