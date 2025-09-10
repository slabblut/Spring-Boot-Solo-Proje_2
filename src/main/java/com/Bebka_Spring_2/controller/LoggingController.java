package com.Bebka_Spring_2.controller;

import com.Bebka_Spring_2.dto.request.UserRequest;
import com.Bebka_Spring_2.dto.response.UserResponse;
import com.Bebka_Spring_2.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/log")
@Slf4j
public class LoggingController {

    private final UserService userService;

    @PostMapping
    public UserResponse createUser(@Valid @RequestBody UserRequest request) {
        log.info("CREATE /users çağrıldı: username={}", request.getName());
        UserResponse response = userService.createUser(request);
        log.info("Kullanıcı oluşturuldu: id={}, username={}", response.getId(), response.getName());
        return response;
    }

    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable Long id, @RequestParam String email) {
        log.info("UPDATE /users/{} çağrıldı: yeni email={}", id, email);
        UserResponse response = userService.updateUser(id, email);
        log.info("Kullanıcı güncellendi: id={}, email={}", response.getId(), response.getEmail());
        return response;
    }

    @GetMapping
    public Page<UserResponse> getAllUsers(@RequestParam int page, @RequestParam int size) {
        log.info("GET /users çağrıldı: page={}, size={}", page, size);
        Page<UserResponse> users = userService.getAllUsers(page, size);
        log.info("Kullanıcılar getirildi: toplam kayıt={}", users.getTotalElements());
        return users;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        log.info("DELETE /users/{} çağrıldı", id);
        userService.deleteUser(id);
        log.info("Kullanıcı silindi: id={}", id);
    }
}
