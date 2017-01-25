package com.mannanlive.controller;

import com.mannanlive.model.borrow.BorrowData;
import com.mannanlive.model.borrow.Borrowings;
import com.mannanlive.model.library.Library;
import com.mannanlive.model.library.LibraryGameData;
import com.mannanlive.service.BorrowService;
import com.mannanlive.service.LibraryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/users", produces = "application/vnd.api+json")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @Autowired
    private BorrowService borrowService;

    @RequestMapping(method = RequestMethod.GET, value = "/{userId}/games")
    @ApiOperation(value = "View a user's library", notes = "User must be in the same organisation as you.")
    public Library getUsersLibrary(Authentication user, @PathVariable Long userId) {
        return libraryService.getUsersLibrary(user, userId);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, value = "/{userId}/games")
    @ApiOperation(value = "Add a game to your library",
                  notes = "Example Minimum Payload: {\"attributes\": {\"game\": {\"id\": \"123\"}}}")
    public void addGameToLibrary(Authentication user, @PathVariable Long userId, @RequestBody LibraryGameData data) {
        libraryService.addGameToLibrary(user, userId, data);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/{userId}/games/{libraryId}")
    @ApiOperation(value = "Update a game in your library",
                  notes = "Example Minimum Payload: {\"attributes\": {\"state\": \"SOLD\"}}")
    public void updateGameInLibrary(Authentication user, @PathVariable Long userId, @PathVariable Long libraryId,
                                      @RequestBody LibraryGameData data) {
        libraryService.updateGameInLibrary(user, libraryId, data);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{userId}/requests")
    @ApiOperation(value = "View your active requests",
            notes = "View your active requests, both made by you or to you")
    public Borrowings requestToBorrowGame(Authentication user, @PathVariable Long userId) {
        return borrowService.getBorrowings(user, userId);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, value = "/{userId}/requests/{gameId}")
    @ApiOperation(value = "Request to borrow a game for another user",
            notes = "Example Minimum Payload: " +
                    "{\"attributes\": {\"startDate\": \"2014-09-13\", \"endDate\": \"2014-10-01\"}}")
    public void requestToBorrowGame(Authentication user, @PathVariable Long userId, @PathVariable Long gameId,
                                    @RequestBody BorrowData data) {
        borrowService.requestToBorrowGame(user, userId, gameId, data);
    }

}
