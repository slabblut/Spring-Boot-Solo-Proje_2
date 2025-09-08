package com.Bebka_Spring_2.service.ımpl;

import com.Bebka_Spring_2.dto.request.UserRequest;
import com.Bebka_Spring_2.dto.response.UserResponse;
import com.Bebka_Spring_2.model.User;
import com.Bebka_Spring_2.repository.UserRepository;
import com.Bebka_Spring_2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public UserResponse createUser(UserRequest request) {
        User user = modelMapper.map(request, User.class);
        userRepository.save(user);
        return new UserResponse(user.getId(), "User created", user.getName());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public UserResponse updateUser(Long id, String newEmail) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setEmail(newEmail);
        userRepository.save(user);
        return new UserResponse(user.getId(), "User updated", user.getName());
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Page<UserResponse> getAllUsers(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable)
                .map(user -> new UserResponse(user.getId(), "Fetched", user.getName()));
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}





