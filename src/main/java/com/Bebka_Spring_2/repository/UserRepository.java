package com.Bebka_Spring_2.repository;

import com.Bebka_Spring_2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
