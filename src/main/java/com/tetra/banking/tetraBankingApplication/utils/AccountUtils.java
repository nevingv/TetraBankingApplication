package com.tetra.banking.tetraBankingApplication.utils;

import java.time.Year;

public class AccountUtils {


    public static final String ACCOUNT_EXISTS_CODE="001";
    public static final String ACCOUNT_CREATED_CODE="002";
    public static final String ACCOUNT_EXISTS_MESSAGE="User already exist!";
    public static final String ACCOUNT_CREATED_MESSAGE="User created successfully";
    public  static String generateAccountNumber(){
        Year currentYear= Year.now();
        int min =100000;
        int max=999999;
        int randomNumber= (int) Math.floor(Math.random()*( max - min +1)+min);
        String year=String.valueOf(currentYear);
        String uniqueNumber= String.valueOf(randomNumber);
        String accountNumber=year+uniqueNumber;
        return accountNumber;
    }
}
