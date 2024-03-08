package com.bootcamp.reviewservice.modules.useraddress.service;

import com.bootcamp.reviewservice.advice.responseexceptionhandler.ErrorMessage;
import com.bootcamp.reviewservice.advice.responseexceptionhandler.exceptions.ItemNotFoundException;
import com.bootcamp.reviewservice.modules.user.model.User;
import com.bootcamp.reviewservice.modules.user.service.UserService;
import com.bootcamp.reviewservice.modules.useraddress.dto.UserAddressMapper;
import com.bootcamp.reviewservice.modules.useraddress.dto.UserAddressResponse;
import com.bootcamp.reviewservice.modules.useraddress.dto.UserAddressSaveRequest;
import com.bootcamp.reviewservice.modules.useraddress.dto.UserAddressUpdateRequest;
import com.bootcamp.reviewservice.modules.useraddress.model.UserAddress;
import com.bootcamp.reviewservice.modules.useraddress.repository.UserAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAddressService {

    private final UserAddressRepository repository;
    private final UserService userService;


    public UserAddressResponse save(UserAddressSaveRequest saveRequest) {
        UserAddress review = repository.save(UserAddressMapper.INSTANCE.toUserAddress(saveRequest));
        return UserAddressMapper.INSTANCE.toUserAddressResponse(review);
    }

    public List<UserAddressResponse> findAllByUserId(Long userId) {
        List<UserAddress> reviews = repository.findAllByUserId(userId);
        return UserAddressMapper.INSTANCE.toUserAddressResponseList(reviews);
    }

    public UserAddressResponse findById(Long id) {
        UserAddress review = findUserAddressById(id);
        return UserAddressMapper.INSTANCE.toUserAddressResponse(review);
    }

    public void delete(Long id) {
        UserAddress review = findUserAddressById(id);
        repository.delete(review);
    }


    public UserAddressResponse update(UserAddressUpdateRequest updateRequest) {
        User foundUser = userService.findUserById(updateRequest.userId());
        UserAddress foundUserAddress = findUserAddressById(updateRequest.id());
        foundUserAddress.setUser(foundUser);
        UserAddress userAddressToUpdate = UserAddressMapper.INSTANCE.toUserAddress(updateRequest);
        BeanUtils.copyProperties(userAddressToUpdate, foundUserAddress);

        UserAddress updatedUserAddress = repository.save(foundUserAddress);
        return UserAddressMapper.INSTANCE.toUserAddressResponse(updatedUserAddress);
    }


    public UserAddress findUserAddressById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ItemNotFoundException(ErrorMessage.USER_ADDRESS_NOT_FOUND));
    }


}
