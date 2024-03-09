package com.bootcamp.reviewservice.modules.user.service;

import com.bootcamp.reviewservice.advice.responseexceptionhandler.ErrorMessage;
import com.bootcamp.reviewservice.advice.responseexceptionhandler.exceptions.ItemNotFoundException;
import com.bootcamp.reviewservice.modules.user.dto.UserMapper;
import com.bootcamp.reviewservice.modules.user.dto.UserResponse;
import com.bootcamp.reviewservice.modules.user.dto.UserSaveRequest;
import com.bootcamp.reviewservice.modules.user.dto.UserUpdateRequest;
import com.bootcamp.reviewservice.modules.user.model.User;
import com.bootcamp.reviewservice.modules.user.model.UserStatus;
import com.bootcamp.reviewservice.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;


    public UserResponse save(UserSaveRequest saveRequest) {
        User user = repository.save(UserMapper.INSTANCE.toUser(saveRequest));
        return UserMapper.INSTANCE.toUserResponse(user);
    }

    public List<UserResponse> findAll() {
        List<User> users = repository.findAll();
        return UserMapper.INSTANCE.toUserResponseList(users);
    }

    public UserResponse findById(Long id) {
        User user = findUserById(id);
        return UserMapper.INSTANCE.toUserResponse(user);
    }

    public void delete(Long id) {
        User user = findUserById(id);
        repository.delete(user);
    }

    public UserResponse update(UserUpdateRequest updateRequest) {
        User foundUser = findUserById(updateRequest.id());
        User userToUpdate = UserMapper.INSTANCE.toUser(updateRequest);
        BeanUtils.copyProperties(userToUpdate, foundUser);

        User updatedUser = repository.save(foundUser);
        return UserMapper.INSTANCE.toUserResponse(updatedUser);
    }

    public UserResponse activate(Long id) {
        User updatedUser = updateStatus(id, UserStatus.ACTIVE);

        return UserMapper.INSTANCE.toUserResponse(updatedUser);
    }

    public UserResponse deactivate(Long id) {
        User updatedUser = updateStatus(id, UserStatus.INACTIVE);

        return UserMapper.INSTANCE.toUserResponse(updatedUser);
    }

    private User updateStatus(Long id, UserStatus status) {
        User foundUser = findUserById(id);
        foundUser.setStatus(status);
        return repository.save(foundUser);
    }

    public User findUserById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ItemNotFoundException(ErrorMessage.USER_NOT_FOUND));
    }

}