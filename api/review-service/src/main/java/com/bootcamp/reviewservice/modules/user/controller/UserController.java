package com.bootcamp.reviewservice.modules.user.controller;


import com.bootcamp.reviewservice.modules.user.dto.UserResponse;
import com.bootcamp.reviewservice.modules.user.dto.UserSaveRequest;
import com.bootcamp.reviewservice.modules.user.dto.UserUpdateRequest;
import com.bootcamp.reviewservice.modules.user.service.UserService;
import com.bootcamp.reviewservice.shared.RestResponse;
import com.bootcamp.reviewservice.shared.WithPagination;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<RestResponse<WithPagination<UserResponse>>> getAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "20") Integer size
    ) {
        WithPagination<UserResponse> userResponseWithPagination = service.findAll(page, size);
        return new ResponseEntity<>(RestResponse.of(userResponseWithPagination), HttpStatus.OK);
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
