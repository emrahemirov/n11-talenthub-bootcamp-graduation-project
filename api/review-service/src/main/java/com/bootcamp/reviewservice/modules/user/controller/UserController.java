package com.bootcamp.reviewservice.modules.user.controller;


import com.bootcamp.reviewservice.modules.user.dto.UserResponse;
import com.bootcamp.reviewservice.modules.user.dto.UserSaveRequest;
import com.bootcamp.reviewservice.modules.user.dto.UserUpdateRequest;
import com.bootcamp.reviewservice.modules.user.service.UserService;
import com.bootcamp.reviewservice.shared.QueryParams;
import com.bootcamp.reviewservice.shared.RestResponse;
import com.bootcamp.reviewservice.shared.WithPagination;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "User Controller", description = "User Management")
public class UserController {

    private final UserService service;

    @PostMapping
    @Operation(summary = "Saves a new user",
            description = "Saves a new user into the system. Requires user details.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "User created successfully",
                            content = @Content(schema = @Schema(implementation = UserResponse.class)))
            })
    public ResponseEntity<RestResponse<UserResponse>> save(@Valid @RequestBody UserSaveRequest saveRequest) {
        UserResponse userResponse = service.save(saveRequest);
        return new ResponseEntity<>(RestResponse.of(userResponse), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Fetches all users",
            description = "Get all users stored in the system.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "All users got successfully",
                            content = @Content(schema = @Schema(implementation = UserResponse.class))),
            })
    public ResponseEntity<RestResponse<WithPagination<UserResponse>>> findAll(QueryParams queryParams) {
        WithPagination<UserResponse> userResponseWithPagination = service.findAll(queryParams);
        return new ResponseEntity<>(RestResponse.of(userResponseWithPagination), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Fetches a user by their ID",
            description = "Get user details for a given user ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User got successfully",
                            content = @Content(schema = @Schema(implementation = UserResponse.class)))
            })
    public ResponseEntity<RestResponse<UserResponse>> findById(@PathVariable Long id) {
        UserResponse userResponse = service.findById(id);
        return new ResponseEntity<>(RestResponse.of(userResponse), HttpStatus.OK);
    }


    @PutMapping("/{debugId}")
    @Operation(summary = "Updates a user's information by their ID",
            description = "Updates user details for a given user ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User updated successfully",
                            content = @Content(schema = @Schema(implementation = UserResponse.class)))
            })
    public ResponseEntity<RestResponse<UserResponse>> update(@PathVariable Long debugId, @Valid @RequestBody UserUpdateRequest updateRequest) {
        UserResponse userResponse = service.update(updateRequest);
        return new ResponseEntity<>(RestResponse.of(userResponse), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes a user by their ID",
            description = "Removes a user from the system based on the user ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User deleted successfully")
            })
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
