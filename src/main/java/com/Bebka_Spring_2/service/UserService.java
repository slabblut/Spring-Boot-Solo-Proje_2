package com.Bebka_Spring_2.service;

import com.Bebka_Spring_2.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User create (User user);
    List<User> readAll();
    Optional<User> readById(Long id);
    User update(User user);
    void delete(Long id);

}
