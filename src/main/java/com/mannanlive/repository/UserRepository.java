package com.mannanlive.repository;

import com.mannanlive.entity.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {

//    List<UserEntity> findByOrganisation(String organisation);
    UserEntity findByLogin(String login);
}
