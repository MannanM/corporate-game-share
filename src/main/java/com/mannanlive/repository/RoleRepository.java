package com.mannanlive.repository;

import com.mannanlive.entity.RoleEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface RoleRepository extends PagingAndSortingRepository<RoleEntity, Long> {
    RoleEntity findByName(String name);
}
