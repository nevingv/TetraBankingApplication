package com.tetra.banking.tetraBankingApplication.service;

import com.tetra.banking.tetraBankingApplication.dto.*;
import com.tetra.banking.tetraBankingApplication.entity.Account;
import com.tetra.banking.tetraBankingApplication.entity.User;
import com.tetra.banking.tetraBankingApplication.repository.AccountRepository;
import com.tetra.banking.tetraBankingApplication.repository.UserRepository;
import com.tetra.banking.tetraBankingApplication.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

import static org.aspectj.runtime.internal.Conversions.intValue;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountRepository accountRepository;

    @Override
    public BankResponse createAccount(UserRequest userRequest) {
        //check if useralready exist

        if(userRepository.existsByEmail(userRequest.getEmail())){
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_EXISTS_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_EXISTS_MESSAGE)
                    .accountInfo(null)
                    .build();

        }


        User newUser = User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .gender(userRequest.getGender())
                .address(userRequest.getAddress())
                .stateOfOrigin(userRequest.getStateOfOrigin())
                .accountNumber(AccountUtils.generateAccountNumber())
                .email(userRequest.getEmail())
                .accountBalance(BigDecimal.valueOf(0))
                .phoneNumber(userRequest.getPhoneNumber())
                .status("ACTIVE")
                .build();
        User savedUser = userRepository.save(newUser);
        return BankResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_CREATED_CODE)
                .responseMessage(AccountUtils.ACCOUNT_CREATED_MESSAGE)
                .accountInfo(AccountInfo.builder()
                        .accountNumber(savedUser.getAccountNumber())
                        .accountBalance(savedUser.getAccountBalance())
                        .accountName(savedUser.getFirstName()+" "+savedUser.getLastName())
                        .build())
                .build();
    }

    @Override
    public TransferResponse transfer(TransferRequest transferRequest) {
        if(!userRepository.existsByEmail(transferRequest.getSenderEmail())){
            return TransferResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_DOESNT_EXISTS_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_DOESNT_EXIST_MESSAGE)
                    .accountInfo(null)
                    .build();

        }else {
            User newUser = userRepository.findByEmail(transferRequest.getSenderEmail());
            if(intValue(newUser.getAccountBalance()) < intValue(transferRequest.getAmount())){
                return TransferResponse.builder()
                        .responseCode(AccountUtils.INSUFFICIENT_FUNDS)
                        .responseMessage(AccountUtils.INSUFFICIENT_FUNDS_MESSAGE)
                        .accountInfo(AccountInfo.builder()
                                .accountNumber(newUser.getAccountNumber())
                                .accountBalance(newUser.getAccountBalance())
                                .accountName(newUser.getFirstName()+" "+newUser.getLastName())
                                .build())
                        .build();

            }else{
                newUser.setAccountBalance(newUser.getAccountBalance().subtract(transferRequest.getAmount()));
                userRepository.save(newUser);
                Account newTransaction = Account.builder().transactionId(AccountUtils.generateTransactionId())
                                .senderAccountNumber(transferRequest.getSenderAccountNumber())
                                        .senderEmail(transferRequest.getSenderEmail())
                                                .recipientName(transferRequest.getRecipientName())
                                                        .recipientAccountNumber(transferRequest.getRecipientAccountNumber())
                                                                .recipientEmail(transferRequest.getRecipientEmail())
                                                                        .amount(transferRequest.getAmount())
                                                                                .transactionStatus("REQUEST_INITIATED")
                                                                                        .build();
                accountRepository.save(newTransaction);
                return TransferResponse.builder()
                        .responseCode(AccountUtils.TRANSFER_REQUEST_CREATED)
                        .responseMessage(AccountUtils.TRANSFER_REQUEST_CREATED_MESSAGE)
                        .accountInfo(AccountInfo.builder()
                                .accountNumber(newUser.getAccountNumber())
                                .accountBalance(newUser.getAccountBalance().subtract(transferRequest.getAmount()))
                                .accountName(newUser.getFirstName()+" "+newUser.getLastName())
                                .build())
                        .build();


            }


        }
    }
}
