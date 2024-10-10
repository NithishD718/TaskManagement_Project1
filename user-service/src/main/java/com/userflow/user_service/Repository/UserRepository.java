package com.userflow.user_service.Repository;

import com.userflow.user_service.Entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDetail,Integer> {
    Optional<UserDetail> findByUsername(String username);
}
