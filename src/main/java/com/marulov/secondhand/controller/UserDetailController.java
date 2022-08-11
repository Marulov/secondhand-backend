package com.marulov.secondhand.controller;

import com.marulov.secondhand.dto.userDetails.CreateUserDetailsRequest;
import com.marulov.secondhand.dto.userDetails.UpdateUserDetailsRequest;
import com.marulov.secondhand.dto.userDetails.UserDetailsDto;
import com.marulov.secondhand.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/userDetails")
@RequiredArgsConstructor
public class UserDetailController {

    private final UserDetailService userDetailService;

    @GetMapping
    public ResponseEntity<List<UserDetailsDto>> getAll() {
        return ResponseEntity.ok(userDetailService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailsDto> getUserDetailsById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userDetailService.getUserDetailsById(id));
    }

    @PostMapping
    public ResponseEntity<UserDetailsDto> createUserDetails(@RequestBody CreateUserDetailsRequest createUserDetailsRequest) {
        userDetailService.createUserDetails(createUserDetailsRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDetailsDto> updateUserDetails(@PathVariable("id") Long id, @RequestBody UpdateUserDetailsRequest updateUserDetailsRequest) {
        return ResponseEntity.ok(userDetailService.updateUserDetails(id, updateUserDetailsRequest));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUserDetails(@PathVariable("id") Long id) {
        userDetailService.deleteUserDetails(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}