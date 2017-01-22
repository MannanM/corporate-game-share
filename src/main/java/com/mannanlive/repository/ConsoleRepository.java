package com.mannanlive.repository;

import com.mannanlive.entity.ConsoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsoleRepository extends JpaRepository<ConsoleEntity, Long> {
}
