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
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAddressService {

    private final UserAddressRepository repository;
    private final UserService userService;


    public UserAddressResponse save(UserAddressSaveRequest saveRequest) {
        User user = userService.findUserById(saveRequest.userId());
        UserAddress userAddressWithUser = UserAddressMapper.INSTANCE.toUserAddress(saveRequest);
        userAddressWithUser.setUser(user);
        UserAddress userAddress = repository.save(userAddressWithUser);

        changePreferredUserAddress(saveRequest.userId(), userAddress.getId());

        return UserAddressMapper.INSTANCE.toUserAddressResponse(userAddress);
    }

    public List<UserAddressResponse> findAllByUserId(Long userId) {
        List<UserAddress> userAddressList = repository.findAllByUserId(userId);
        return UserAddressMapper.INSTANCE.toUserAddressResponseList(userAddressList);
    }


    public void delete(Long id) {
        UserAddress userAddress = findUserAddressById(id);
        repository.delete(userAddress);
    }


    public UserAddressResponse update(UserAddressUpdateRequest updateRequest) {
        UserAddress userAddress = findUserAddressById(updateRequest.id());
        UserAddressMapper.INSTANCE.mutateUserAddress(userAddress, updateRequest);
        repository.save(userAddress);

        return UserAddressMapper.INSTANCE.toUserAddressResponse(userAddress);
    }

    public void changePreferredUserAddress(Long userId, Long userAddressId) {
        List<UserAddress> userAddressList = repository.findAllByUserId(userId);
        userAddressList.forEach(userAddress -> userAddress.setIsPreferred(userAddress.getId().equals(userAddressId)));
        repository.saveAll(userAddressList);
    }


    public UserAddress findUserAddressById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ItemNotFoundException(ErrorMessage.USER_ADDRESS_NOT_FOUND));
    }


}
