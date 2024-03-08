package com.bootcamp.reviewservice.modules.useraddress.repository;

import com.bootcamp.reviewservice.modules.useraddress.model.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {

    List<UserAddress> findAllByUserId(Long userId);
}
