package com.bootcamp.reviewservice.modules.useraddress.controller;

import com.bootcamp.reviewservice.modules.useraddress.dto.UserAddressResponse;
import com.bootcamp.reviewservice.modules.useraddress.dto.UserAddressSaveRequest;
import com.bootcamp.reviewservice.modules.useraddress.dto.UserAddressUpdateRequest;
import com.bootcamp.reviewservice.modules.useraddress.service.UserAddressService;
import com.bootcamp.reviewservice.shared.RestResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-addresses")
@RequiredArgsConstructor
public class UserAddressController {

    private final UserAddressService service;

    @PostMapping
    public ResponseEntity<RestResponse<UserAddressResponse>> save(@Valid @RequestBody UserAddressSaveRequest saveRequest) {
        UserAddressResponse userAddressResponse = service.save(saveRequest);
        return new ResponseEntity<>(RestResponse.of(userAddressResponse), HttpStatus.CREATED);
    }

    @GetMapping("/by-user-id/{userId}")
    public ResponseEntity<RestResponse<List<UserAddressResponse>>> getAll(@PathVariable Long userId) {
        List<UserAddressResponse> userAddressResponseList = service.findAllByUserId(userId);
        return new ResponseEntity<>(RestResponse.of(userAddressResponseList), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<UserAddressResponse>> getOne(@PathVariable Long id) {
        UserAddressResponse userAddressResponse = service.findById(id);
        return new ResponseEntity<>(RestResponse.of(userAddressResponse), HttpStatus.OK);
    }

    @PutMapping("/{debugId}")
    public ResponseEntity<RestResponse<UserAddressResponse>> update(@PathVariable Long debugId, @Valid @RequestBody UserAddressUpdateRequest updateRequest) {
        UserAddressResponse userAddressResponse = service.update(updateRequest);
        return new ResponseEntity<>(RestResponse.of(userAddressResponse), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
