package com.tetra.banking.tetraBankingApplication.repository;

import com.tetra.banking.tetraBankingApplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Boolean existsByEmail(String email);
    User findByEmail(String email);


}
