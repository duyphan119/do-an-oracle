package com.shopbangiay.models;

public class Auth {
    
    
    private Account account;
    private String access_token, refresh_token;

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setAccessToken(String access_token) {
        this.access_token = access_token;
    }

    public void setRefreshToken(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getRefreshToken() {
        return refresh_token;
    }

    
    
}
