package com.bootcamp.reviewservice.modules.user.controller;


import com.bootcamp.reviewservice.modules.user.dto.UserResponse;
import com.bootcamp.reviewservice.modules.user.dto.UserSaveRequest;
import com.bootcamp.reviewservice.modules.user.dto.UserUpdateRequest;
import com.bootcamp.reviewservice.modules.user.service.UserService;
import com.bootcamp.reviewservice.shared.RestResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    public ResponseEntity<RestResponse<UserResponse>> save(@Valid @RequestBody UserSaveRequest saveRequest) {
        UserResponse userResponse = service.save(saveRequest);
        return new ResponseEntity<>(RestResponse.of(userResponse), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<RestResponse<List<UserResponse>>> getAll() {
        List<UserResponse> userResponseList = service.findAll();
        return new ResponseEntity<>(RestResponse.of(userResponseList), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<UserResponse>> getOne(@PathVariable Long id) {
        UserResponse userResponse = service.findById(id);
        return new ResponseEntity<>(RestResponse.of(userResponse), HttpStatus.OK);
    }

    @PutMapping("/{debugId}")
    public ResponseEntity<RestResponse<UserResponse>> update(@PathVariable Long debugId, @Valid @RequestBody UserUpdateRequest updateRequest) {
        UserResponse userResponse = service.update(updateRequest);
        return new ResponseEntity<>(RestResponse.of(userResponse), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<RestResponse<UserResponse>> activate(@PathVariable Long id) {
        UserResponse userResponse = service.activate(id);
        return new ResponseEntity<>(RestResponse.of(userResponse), HttpStatus.OK);
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<RestResponse<UserResponse>> deactivate(@PathVariable Long id) {
        UserResponse userResponse = service.deactivate(id);
        return new ResponseEntity<>(RestResponse.of(userResponse), HttpStatus.OK);
    }
}
