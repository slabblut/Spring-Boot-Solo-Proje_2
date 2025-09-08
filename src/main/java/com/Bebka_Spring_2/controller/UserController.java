package com.Bebka_Spring_2.controller;

import com.Bebka_Spring_2.dto.request.UserRequest;
import com.Bebka_Spring_2.dto.response.UserResponse;
import com.Bebka_Spring_2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest request) {
        return userService.createUser(request);
    }

    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable Long id, @RequestParam String email) {
        return userService.updateUser(id, email);
    }

    @GetMapping
    public Page<UserResponse> getAllUsers(@RequestParam int page, @RequestParam int size) {
        return userService.getAllUsers(page, size);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}


