package com.shopbangiay.models;

public class Account {
    private int account_id;
    private String email, full_name, phone, account_role, hashed_password;

    public void setAccountId(int account_id) {
        this.account_id = account_id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFullName(String full_name) {
        this.full_name = full_name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAccountRole(String account_role) {
        this.account_role = account_role;
    }

    public void setHashedPassword(String hashed_password) {
        this.hashed_password = hashed_password;
    }

    public int getAccountId() {
        return account_id;
    }

    public String getAccountRole() {
        return account_role;
    }
    
    
    
}
