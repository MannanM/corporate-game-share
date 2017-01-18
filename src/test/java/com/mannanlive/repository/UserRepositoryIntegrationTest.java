package com.mannanlive.repository;

import com.mannanlive.entity.UserEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class UserRepositoryIntegrationTest extends AbstractRepositoryIntegrationTest {
    @Autowired
    private UserRepository repository;

    @Test
    public void persistUserEntityGeneratesId() {
        UserEntity expected = entityManager.persist(new UserEntity());

        UserEntity actual = repository.findOne(expected.getId());

        assertThat(actual).isEqualTo(expected);
    }
}
