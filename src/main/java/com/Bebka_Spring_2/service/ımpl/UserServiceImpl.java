package com.Bebka_Spring_2.service.ımpl;

import com.Bebka_Spring_2.dto.request.UserRequest;
import com.Bebka_Spring_2.dto.response.UserResponse;
import com.Bebka_Spring_2.model.User;
import com.Bebka_Spring_2.repository.UserRepository;
import com.Bebka_Spring_2.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public UserResponse createUser(UserRequest request) {
        log.info("createUser metodu çağrıldı: username={}", request.getName());
        User user = modelMapper.map(request, User.class);
        userRepository.save(user);
        log.info("Kullanıcı oluşturuldu: id={}, username={}", user.getId(), user.getName());
        return new UserResponse(user.getId(), "User created", user.getName(), user.getEmail());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public UserResponse updateUser(Long id, String newEmail) {
        log.info("updateUser metodu çağrıldı: id={}, yeniEmail={}", id, newEmail);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setEmail(newEmail);
        userRepository.save(user);
        log.info("Kullanıcı güncellendi: id={}, email={}", user.getId(), user.getEmail());
        return new UserResponse(user.getId(), "User updated", user.getName(), user.getEmail());
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Page<UserResponse> getAllUsers(int page, int size) {
        log.info("getAllUsers metodu çağrıldı: page={}, size={}", page, size);
        PageRequest pageable = PageRequest.of(page, size);
        Page<UserResponse> users = userRepository.findAll(pageable)
                .map(user -> new UserResponse(user.getId(), "Fetched", user.getName(), user.getEmail()));
        log.info("Toplam kullanıcı getirildi: {}", users.getTotalElements());
        return users;
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        log.info("deleteUser metodu çağrıldı: id={}", id);
        userRepository.deleteById(id);
        log.info("Kullanıcı silindi: id={}", id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void createUserWithException(UserRequest request) {
        log.info("createUserWithException metodu çağrıldı: username={}", request.getName());
        User user = modelMapper.map(request, User.class);
        userRepository.save(user);
        log.error("Geri alma testi için hata fırlatılıyor");
        throw new RuntimeException("Geri alma testi için oluşturulmuş hata");
    }
}









