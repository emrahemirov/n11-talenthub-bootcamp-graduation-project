package com.bootcamp.reviewservice.modules.user.service;

import com.bootcamp.reviewservice.advice.responseexceptionhandler.ErrorMessage;
import com.bootcamp.reviewservice.advice.responseexceptionhandler.exceptions.ItemNotFoundException;
import com.bootcamp.reviewservice.modules.user.dto.UserMapper;
import com.bootcamp.reviewservice.modules.user.dto.UserResponse;
import com.bootcamp.reviewservice.modules.user.dto.UserSaveRequest;
import com.bootcamp.reviewservice.modules.user.dto.UserUpdateRequest;
import com.bootcamp.reviewservice.modules.user.model.User;
import com.bootcamp.reviewservice.modules.user.repository.UserRepository;
import com.bootcamp.reviewservice.shared.QueryParams;
import com.bootcamp.reviewservice.shared.WithPagination;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public WithPagination<UserResponse> findAll(QueryParams queryParams) {
        QueryParams params = queryParams != null ? queryParams : new QueryParams();


        Page<User> userPage = repository.findAll(PageRequest.of(params.getPage(), params.getSize()));
        List<UserResponse> userResponseList = UserMapper.INSTANCE.toUserResponseList(userPage.getContent());
        return WithPagination.of(userPage, userResponseList);
    }

    public void delete(Long id) {
        User user = findUserById(id);
        repository.delete(user);
    }

    public UserResponse update(UserUpdateRequest updateRequest) {
        User user = findUserById(updateRequest.id());
        UserMapper.INSTANCE.mutateUser(user, updateRequest);
        repository.save(user);

        return UserMapper.INSTANCE.toUserResponse(user);
    }


    public User findUserById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ItemNotFoundException(ErrorMessage.USER_NOT_FOUND));
    }

}