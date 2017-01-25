package com.mannanlive.repository;

import com.mannanlive.entity.BorrowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRepository extends JpaRepository<BorrowEntity, Long> {
    @Query("select b from borrow b " +
            "where (b.requester.id = ?1 or b.library.user.id = ?1)" +
            "and b.completed = null " +
            "order by b.start")
    List<BorrowEntity> findActiveByUser(Long userId);
}
