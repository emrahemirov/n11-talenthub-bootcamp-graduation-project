package com.bootcamp.reviewservice.modules.user.repository;

import com.bootcamp.reviewservice.modules.user.model.User;
import com.bootcamp.reviewservice.modules.user.model.UserStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findAllByStatus(UserStatus status, Pageable pageable);

    Optional<User> findByIdAndStatus(Long id, UserStatus status);
}
