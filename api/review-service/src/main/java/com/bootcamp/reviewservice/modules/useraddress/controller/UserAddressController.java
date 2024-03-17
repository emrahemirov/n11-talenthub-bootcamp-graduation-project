package com.bootcamp.reviewservice.modules.useraddress.controller;

import com.bootcamp.reviewservice.modules.useraddress.dto.UserAddressResponse;
import com.bootcamp.reviewservice.modules.useraddress.dto.UserAddressSaveRequest;
import com.bootcamp.reviewservice.modules.useraddress.dto.UserAddressUpdateRequest;
import com.bootcamp.reviewservice.modules.useraddress.service.UserAddressService;
import com.bootcamp.reviewservice.shared.RestResponse;
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

import java.util.List;

@RestController
@RequestMapping("/api/v1/user-addresses")
@RequiredArgsConstructor
@Tag(name = "UserAddressController", description = "Address Management")
public class UserAddressController {

    private final UserAddressService service;

    @PostMapping
    @Operation(summary = "Save a new address",
            description = "Saves a new address into the system. Requires address details.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Address created",
                            content = @Content(schema = @Schema(implementation = UserAddressResponse.class)))
            })
    public ResponseEntity<RestResponse<UserAddressResponse>> save(@Valid @RequestBody UserAddressSaveRequest saveRequest) {
        UserAddressResponse userAddressResponse = service.save(saveRequest);
        return new ResponseEntity<>(RestResponse.of(userAddressResponse), HttpStatus.CREATED);
    }

    @GetMapping("/by-user-id/{userId}")
    @Operation(summary = "Get addresses by user ID",
            description = "Fetches a list of addresses associated with a given user ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Addresses got successfully",
                            content = @Content(schema = @Schema(implementation = UserAddressSaveRequest.class)))
            })
    public ResponseEntity<RestResponse<List<UserAddressResponse>>> getAll(@PathVariable Long userId) {
        List<UserAddressResponse> userAddressResponseList = service.findAllByUserId(userId);
        return new ResponseEntity<>(RestResponse.of(userAddressResponseList), HttpStatus.OK);
    }

    @GetMapping("/preferred")
    @Operation(summary = "Get all addresses",
            description = "Fetches all addresses stored in the system.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "All addresses got successfully",
                            content = @Content(schema = @Schema(implementation = UserAddressSaveRequest.class)))
            })
    public ResponseEntity<RestResponse<UserAddressResponse>> getPreferredUserAddress(@RequestParam Long userId) {
        UserAddressResponse userAddressResponse = service.getPreferredUserAddress(userId);
        return new ResponseEntity<>(RestResponse.of(userAddressResponse), HttpStatus.OK);
    }


    @PutMapping("/{debugId}")
    @Operation(summary = "Updates an address by its ID",
            description = "Updates address details for a given address ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Address updated successfully",
                            content = @Content(schema = @Schema(implementation = UserAddressSaveRequest.class))),
            })
    public ResponseEntity<RestResponse<UserAddressResponse>> update(@PathVariable Long debugId, @Valid @RequestBody UserAddressUpdateRequest updateRequest) {
        UserAddressResponse userAddressResponse = service.update(updateRequest);
        return new ResponseEntity<>(RestResponse.of(userAddressResponse), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes an address by its ID",
            description = "Removes an address from the system based on the address ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Address deleted successfully"),

            })
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}/preferred")
    public ResponseEntity<HttpStatus> changePreferredUserAddress(@PathVariable Long id, @RequestParam Long userId) {
        service.changePreferredUserAddress(userId, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
