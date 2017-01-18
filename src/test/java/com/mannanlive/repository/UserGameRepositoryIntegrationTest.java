package com.mannanlive.repository;

import com.mannanlive.entity.GameEntity;
import com.mannanlive.entity.GameState;
import com.mannanlive.entity.UserEntity;
import com.mannanlive.entity.UserGameEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UserGameRepositoryIntegrationTest extends AbstractRepositoryIntegrationTest {

    @Autowired
    private UserGameRepository repository;

    @Test
    public void persistUserEntityGeneratesId() {
        UserEntity user = entityManager.persist(new UserEntity());
        UserGameEntity secondGame = createAndPersistUserGame(user, "Test Game", LocalDateTime.now());
        UserGameEntity firstGame = createAndPersistUserGame(user, "Second Test Game",
                LocalDateTime.of(2014, Month.DECEMBER, 12, 12, 12));

        List<UserGameEntity> games = repository.findByUserIdOrderByDateAdded(user.getId());

        assertThat(games.size()).isEqualTo(2);
        assertThat(games.get(0)).isEqualTo(firstGame);
        assertThat(games.get(1)).isEqualTo(secondGame);
    }

    private UserGameEntity createAndPersistUserGame(UserEntity user, String gameName, LocalDateTime date) {
        return entityManager.persist(createUserGame(createGame(gameName), user, date));
    }

    private GameEntity createGame(String gameName) {
        return entityManager.persist(new GameEntity(gameName, "Test Console"));
    }

    private UserGameEntity createUserGame(GameEntity game, UserEntity user, LocalDateTime now) {
        UserGameEntity entity = new UserGameEntity();
        entity.setGame(game);
        entity.setUser(user);
        entity.setDateAdded(now);
        entity.setState(GameState.AVAILABLE);
        return entity;
    }
}
