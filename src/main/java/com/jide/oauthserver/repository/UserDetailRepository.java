package com.jide.oauthserver.repository;

import com.jide.oauthserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailRepository extends JpaRepository<User, Integer > {

    Optional<User> findByUsername(String username);
}
