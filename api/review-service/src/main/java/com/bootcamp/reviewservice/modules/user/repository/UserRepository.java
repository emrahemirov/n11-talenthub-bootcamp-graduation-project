package com.bootcamp.reviewservice.modules.user.repository;

import com.bootcamp.reviewservice.modules.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


}
