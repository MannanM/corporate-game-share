package com.mannanlive.translator;

import com.mannanlive.entity.GameEntity;
import com.mannanlive.entity.GameState;
import com.mannanlive.entity.LibraryEntity;
import com.mannanlive.entity.UserEntity;
import com.mannanlive.model.ResourceSummary;
import com.mannanlive.model.library.LibraryGameData;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LibraryTranslator {
    public LibraryGameData translate(LibraryEntity entity) {
        LibraryGameData data = new LibraryGameData();
        data.setId(entity.getId().toString());
        data.getAttributes().setAdded(entity.getAdded());
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
        entity.setAdded(LocalDateTime.now());
        entity.setGame(new GameEntity(Long.parseLong(data.getAttributes().getGame().getId())));
        entity.setUser(new UserEntity(userId));
        return entity;
    }
}
