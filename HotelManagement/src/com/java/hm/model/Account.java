package com.java.hm.model;

import com.java.hm.enums.AccountStatus;

public class Account {
    private String userId;
    private String password;
    private AccountStatus accountStatus;
    public boolean resetPassword(){
        return true;
    }
}
