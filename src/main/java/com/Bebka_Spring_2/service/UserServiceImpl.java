package com.Bebka_Spring_2.service;

import com.Bebka_Spring_2.model.User;
import com.Bebka_Spring_2.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public List<User> readAll() {
        return List.of();
    }

    @Override
    public Optional<User> readById(Long id) {
        return Optional.empty();
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
