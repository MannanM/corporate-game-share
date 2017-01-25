package com.mannanlive.repository;

import com.mannanlive.entity.ConsoleEntity;
import com.mannanlive.entity.GameEntity;
import com.mannanlive.entity.GameState;
import com.mannanlive.entity.LibraryEntity;
import com.mannanlive.entity.UserEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LibraryRepositoryIntegrationTest extends AbstractRepositoryIntegrationTest {

    @Autowired
    private LibraryRepository repository;

    @Test
    public void persistUserEntityGeneratesId() {
        UserEntity user = entityManager.persist(new UserEntity("something@something.com", "Test", "hahah", "something.com"));
        LibraryEntity secondGame = createAndPersistUserGame(user, "Test Game", LocalDateTime.now());
        LibraryEntity firstGame = createAndPersistUserGame(user, "Second Test Game",
                LocalDateTime.of(2014, Month.DECEMBER, 12, 12, 12));

        List<LibraryEntity> games = repository.findByUserIdOrderByCreated(user.getId());

        assertThat(games.size()).isEqualTo(2);
        assertThat(games.get(0)).isEqualTo(firstGame);
        assertThat(games.get(1)).isEqualTo(secondGame);
    }

    private LibraryEntity createAndPersistUserGame(UserEntity user, String gameName, LocalDateTime date) {
        return entityManager.persist(createUserGame(createGame(gameName), user, date));
    }

    private GameEntity createGame(String gameName) {
        return entityManager.persist(new GameEntity(gameName, new ConsoleEntity(1L)));
    }

    private LibraryEntity createUserGame(GameEntity game, UserEntity user, LocalDateTime now) {
        LibraryEntity entity = new LibraryEntity();
        entity.setGame(game);
        entity.setUser(user);
        entity.setCreated(now);
        entity.setState(GameState.AVAILABLE);
        return entity;
    }
}
