package com.marulov.secondhand.controller;

import com.marulov.secondhand.dto.user.CreateUserRequest;
import com.marulov.secondhand.dto.user.UpdateUserRequest;
import com.marulov.secondhand.dto.user.UserDto;
import com.marulov.secondhand.model.User;
import com.marulov.secondhand.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("{mail}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("mail") String mail) {
        return ResponseEntity.ok(userService.getUserById(mail));
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserRequest createUserRequest) {
        return ResponseEntity.ok(userService.createUser(createUserRequest));
    }

    @PutMapping("{mail}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("mail") String mail, @RequestBody UpdateUserRequest updateUserRequest) {
        return ResponseEntity.ok(userService.updateUser(mail ,updateUserRequest));
    }

    @PatchMapping("{mail}")
    public ResponseEntity<Void> deactiveUser(@PathVariable("mail") String mail) {
        userService.deactiveUser(mail);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{mail}")
    public ResponseEntity<Void> deleteUser(@PathVariable("mail") String mail) {
        userService.deleteUser(mail);
        return ResponseEntity.ok().build();
    }
}
