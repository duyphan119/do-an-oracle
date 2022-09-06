package com.shopbangiay.services;

import com.shopbangiay.interfaces.IAccountService;
import com.shopbangiay.models.Account;
import com.shopbangiay.utils.ConnectDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AccountService implements IAccountService {
    private Connection conn;
        
    public AccountService(){
        conn = ConnectDB.connect();
    }

    @Override
    public List<Account> findAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        String sql = "select * from accounts";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                int account_id = rs.getInt("account_id");
                String full_name = rs.getString("full_name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String account_role = rs.getString("account_role");
                
                Account account = new Account();
                account.setAccountId(account_id);
                account.setFullName(full_name);
                account.setEmail(email);
                account.setPhone(phone);
                account.setAccountRole(account_role);
                
                accounts.add(account);
                
            }
        } catch (SQLException e) {
        }
        return accounts;
    }

    @Override
    public Account findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Account save(Account t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Account update(Account t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
