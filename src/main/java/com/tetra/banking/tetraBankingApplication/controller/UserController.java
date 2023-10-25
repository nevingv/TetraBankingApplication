package com.tetra.banking.tetraBankingApplication.controller;


import com.tetra.banking.tetraBankingApplication.dto.BankResponse;
import com.tetra.banking.tetraBankingApplication.dto.UserRequest;
import com.tetra.banking.tetraBankingApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    public BankResponse createAccount(@RequestBody UserRequest userRequest){
        System.out.println("Response"+userRequest.getFirstName()+userRequest.getLastName());
        return userService.createAccount(userRequest);
    }
}
