package com.tetra.banking.tetraBankingApplication.service;

import com.tetra.banking.tetraBankingApplication.dto.BankResponse;
import com.tetra.banking.tetraBankingApplication.dto.UserRequest;

public interface UserService {
    BankResponse createAccount(UserRequest userRequest);
}
