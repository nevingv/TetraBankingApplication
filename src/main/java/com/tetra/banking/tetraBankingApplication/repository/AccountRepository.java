package com.tetra.banking.tetraBankingApplication.repository;

import com.tetra.banking.tetraBankingApplication.entity.Account;
import com.tetra.banking.tetraBankingApplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {

}
