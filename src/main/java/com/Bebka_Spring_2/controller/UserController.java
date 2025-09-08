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
    @PostMapping("/propagation-test")
    public String testPropagation(@RequestBody UserRequest request) {
        userService.createUser(request);
        userService.updateUser(4L, "silatest@example.com");
        userService.getAllUsers(0, 5);
        return "Propagation test completed!";
    }
    @PostMapping("/rollback-test")
    public String rollbackTest() {
        try {
            UserRequest request = new UserRequest("RollbackUser", "rollback@example.com");
            userService.createUserWithException(request);
        } catch (Exception e) {
            return "İşlem geri alındı. Hata: " + e.getMessage();
        }
        return "Bu mesaj hiçbir zaman dönmemeli";
    }


}


