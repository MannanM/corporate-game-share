package com.mannanlive.repository;

import com.mannanlive.entity.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
    List<UserEntity> findByOrganisation(String organisation);
    UserEntity findByLogin(String login);
}
