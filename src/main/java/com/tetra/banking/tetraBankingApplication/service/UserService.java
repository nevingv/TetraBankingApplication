package com.tetra.banking.tetraBankingApplication.service;

import com.tetra.banking.tetraBankingApplication.dto.BankResponse;
import com.tetra.banking.tetraBankingApplication.dto.TransferRequest;
import com.tetra.banking.tetraBankingApplication.dto.TransferResponse;
import com.tetra.banking.tetraBankingApplication.dto.UserRequest;

public interface UserService {
    BankResponse createAccount(UserRequest userRequest);
    TransferResponse transfer(TransferRequest transferRequest);
}
