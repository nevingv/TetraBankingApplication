package com.tetra.banking.tetraBankingApplication.repository;

import com.tetra.banking.tetraBankingApplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    Boolean existsByEmail(String email);

}
