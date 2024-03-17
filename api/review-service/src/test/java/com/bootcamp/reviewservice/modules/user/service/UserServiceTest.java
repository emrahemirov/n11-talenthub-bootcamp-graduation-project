package com.bootcamp.reviewservice.modules.user.service;

import com.bootcamp.reviewservice.advice.responseexceptionhandler.exceptions.ItemNotFoundException;
import com.bootcamp.reviewservice.modules.user.dto.UserResponse;
import com.bootcamp.reviewservice.modules.user.dto.UserUpdateRequest;
import com.bootcamp.reviewservice.modules.user.model.User;
import com.bootcamp.reviewservice.modules.user.repository.UserRepository;
import com.bootcamp.reviewservice.shared.QueryParams;
import com.bootcamp.reviewservice.shared.WithPagination;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository mockRepository;

    private UserService userServiceUnderTest;

    @BeforeEach
    void setUp() {
        userServiceUnderTest = new UserService(mockRepository);
    }

    @Test
    void shouldFindAll() {
        QueryParams queryParams = new QueryParams();
        queryParams.setPage(1);
        queryParams.setSize(1);

        Page<User> users = new PageImpl<>(List.of(new User(1L, "username", "name", "surname")));
        when(mockRepository.findAll(PageRequest.of(1, 1))).thenReturn(users);

        WithPagination<UserResponse> result = userServiceUnderTest.findAll(queryParams);

    }

    @Test
    void shouldFindAll_UserRepositoryReturnsNoItems() {

        QueryParams queryParams = new QueryParams();
        queryParams.setPage(1);
        queryParams.setSize(1);

        when(mockRepository.findAll(PageRequest.of(1, 1))).thenReturn(new PageImpl<>(Collections.emptyList()));

        WithPagination<UserResponse> result = userServiceUnderTest.findAll(queryParams);

    }

    @Test
    void shouldFindById() {
        UserResponse expectedResult = new UserResponse(0L, "username", "name", "surname",
                null, null);

        Optional<User> user = Optional.of(new User(0L, "username", "name", "surname"));
        when(mockRepository.findById(0L)).thenReturn(user);

        UserResponse result = userServiceUnderTest.findById(0L);

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void shouldFindById_UserRepositoryReturnsAbsent() {
        when(mockRepository.findById(0L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userServiceUnderTest.findById(0L)).isInstanceOf(ItemNotFoundException.class);
    }

    @Test
    void shouldDelete() {

        Optional<User> user = Optional.of(new User(0L, "username", "name", "surname"));
        when(mockRepository.findById(0L)).thenReturn(user);

        userServiceUnderTest.delete(0L);

        verify(mockRepository).delete(any(User.class));
    }

    @Test
    void shouldDelete_UserRepositoryFindByIdReturnsAbsent() {
        // Setup
        when(mockRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the should
        assertThatThrownBy(() -> userServiceUnderTest.delete(0L)).isInstanceOf(ItemNotFoundException.class);
    }

    @Test
    void shouldUpdate() {
        // Setup
        UserUpdateRequest updateRequest = new UserUpdateRequest(0L, "username", "name", "surname");
        UserResponse expectedResult = new UserResponse(0L, "username", "name", "surname",
                null, null);

        // Configure UserRepository.findById(...).
        Optional<User> user = Optional.of(new User(0L, "username", "name", "surname"));
        when(mockRepository.findById(0L)).thenReturn(user);

        // Run the should
        UserResponse result = userServiceUnderTest.update(updateRequest);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
        verify(mockRepository).save(any(User.class));
    }

    @Test
    void shouldUpdate_UserRepositoryFindByIdReturnsAbsent() {
        // Setup
        UserUpdateRequest updateRequest = new UserUpdateRequest(0L, "username", "name", "surname");
        when(mockRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the should
        assertThatThrownBy(() -> userServiceUnderTest.update(updateRequest)).isInstanceOf(ItemNotFoundException.class);
    }

    @Test
    void shouldFindUserById() {
        // Setup
        // Configure UserRepository.findById(...).
        Optional<User> user = Optional.of(new User(0L, "username", "name", "surname"));
        when(mockRepository.findById(0L)).thenReturn(user);

        // Run the should
        User result = userServiceUnderTest.findUserById(0L);

        // Verify the results
    }

    @Test
    void shouldFindUserById_UserRepositoryReturnsAbsent() {
        // Setup
        when(mockRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the should
        assertThatThrownBy(() -> userServiceUnderTest.findUserById(0L)).isInstanceOf(ItemNotFoundException.class);
    }
}