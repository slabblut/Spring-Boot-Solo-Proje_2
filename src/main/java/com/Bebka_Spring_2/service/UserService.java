package com.Bebka_Spring_2.service;

import com.Bebka_Spring_2.dto.request.UserRequest;
import com.Bebka_Spring_2.dto.response.UserResponse;
import com.Bebka_Spring_2.model.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserResponse createUser(UserRequest request);
    UserResponse updateUser(Long id, String newEmail);
    Page<UserResponse> getAllUsers(int page, int size);
    void deleteUser(Long id);
    void createUserWithException(UserRequest request);




}
