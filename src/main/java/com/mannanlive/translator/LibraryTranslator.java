package com.mannanlive.translator;

import com.mannanlive.entity.BorrowEntity;
import com.mannanlive.entity.BorrowState;
import com.mannanlive.entity.GameEntity;
import com.mannanlive.entity.GameState;
import com.mannanlive.entity.LibraryEntity;
import com.mannanlive.entity.UserEntity;
import com.mannanlive.model.ResourceSummary;
import com.mannanlive.model.borrow.BorrowData;
import com.mannanlive.model.library.LibraryGameData;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LibraryTranslator {
    public LibraryGameData translate(LibraryEntity entity) {
        LibraryGameData data = new LibraryGameData();
        data.setId(entity.getId().toString());
        data.getAttributes().setCreated(entity.getCreated());
        data.getAttributes().setRemoved(entity.getRemoved());
        data.getAttributes().setState(entity.getState());
        data.getAttributes().setGame(new ResourceSummary("games", entity.getGame().getId().toString(),
                entity.getGame().getName()));
        data.getAttributes().setOwner(new ResourceSummary("users", entity.getUser().getId().toString(),
                entity.getUser().getName()));
        if (entity.getBorrower() != null) {
            data.getAttributes().setOwner(new ResourceSummary("users", entity.getBorrower().getId().toString(),
                    entity.getBorrower().getName()));
        }
        return data;
    }

    public LibraryEntity translateNew(Long userId, LibraryGameData data) {
        LibraryEntity entity = new LibraryEntity();
        entity.setState(GameState.AVAILABLE);
        entity.setCreated(LocalDateTime.now());
        entity.setGame(new GameEntity(Long.parseLong(data.getAttributes().getGame().getId())));
        entity.setUser(new UserEntity(userId));
        return entity;
    }

    public BorrowEntity translateNew(Long requestor, BorrowData data, LibraryEntity library) {
        BorrowEntity entity = new BorrowEntity();
        entity.setState(BorrowState.PENDING);
        entity.setCreated(LocalDateTime.now());
        entity.setLibrary(library);
        entity.setRequester(new UserEntity(requestor));
        entity.setStart(data.getAttributes().getStartDate());
        entity.setEnd(data.getAttributes().getEndDate());
        return entity;
    }

    public BorrowData translate(BorrowEntity entity) {
        BorrowData data = new BorrowData();
        data.setId(entity.getId().toString());
        data.getAttributes().setRequested(entity.getCreated());
        data.getAttributes().setCompleted(entity.getCompleted());
        data.getAttributes().setStartDate(entity.getStart());
        data.getAttributes().setEndDate(entity.getEnd());
        data.getAttributes().setState(entity.getState());
        data.getAttributes().setOwner(new ResourceSummary("users", entity.getLibrary().getUser().getId().toString(),
                entity.getLibrary().getUser().getName()));
        data.getAttributes().setGame(new ResourceSummary("games", entity.getLibrary().getGame().getId().toString(),
                entity.getLibrary().getGame().getName()));
        data.getAttributes().setBorrower(new ResourceSummary("users", entity.getRequester().getId().toString(),
                entity.getRequester().getName()));
        return data;
    }
}
