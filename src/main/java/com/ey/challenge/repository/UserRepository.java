package com.ey.challenge.repository;

import com.ey.challenge.dto.UserDTO;
import com.ey.challenge.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    //List<UserDTO> findAll();
}
