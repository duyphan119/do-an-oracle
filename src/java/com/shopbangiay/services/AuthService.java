package com.shopbangiay.services;

import com.google.gson.Gson;
import com.shopbangiay.dtos.auth.LoginDTO;
import com.shopbangiay.dtos.auth.RegisterDTO;
import com.shopbangiay.interfaces.IAuthService;
import com.shopbangiay.models.Account;
import com.shopbangiay.models.Auth;
import com.shopbangiay.utils.ConnectDB;
import com.shopbangiay.utils.Helper;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.gson.io.GsonSerializer;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AuthService implements IAuthService {

    private final String ACCESS_TOKEN_SECRET = "duyphan";
    private final String REFRESH_TOKEN_SECRET = "duy";
    Connection conn;

    public AuthService() {
        conn = ConnectDB.connect();
    }
    
    public SecretKey convertStringToSecrectKey (String key) {
        byte[] decodedKey = Base64.getDecoder().decode(key);
        SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES"); 
        return originalKey;
    }
    
    public String convertSecrectKeyToString (SecretKey key) {
        SecretKey secretKey;
        String encodedKey = "";
        try {
            secretKey = KeyGenerator.getInstance("AES").generateKey();
            encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AuthService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return encodedKey;
    }

    public String createAccessToken(Account account) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        Instant now = Instant.now();
        Gson gson = new Gson();
        String access_token = Jwts.builder().serializeToJsonWith(new GsonSerializer(gson))
                .claim("id", account.getAccountId())
                .claim("role", account.getAccountRole())
                .setIssuedAt(new Date())
                .setExpiration(Date.from(now.plus(30, ChronoUnit.MINUTES)))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        return access_token;
    }

    public String createRefreshToken(Account account) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        Instant now = Instant.now();
        Gson gson = new Gson();
        String refresh_token = Jwts.builder().serializeToJsonWith(new GsonSerializer(gson))
                .claim("id", account.getAccountId())
                .claim("role", account.getAccountRole())
                .setIssuedAt(new Date())
                .setExpiration(Date.from(now.plus(60 * 24 * 365, ChronoUnit.MINUTES)))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        return refresh_token;
    }

    @Override
    public Auth login(LoginDTO dto) {
        Auth auth = new Auth();

        String sql = "select * from accounts where email = '" + dto.getEmail() + "'";
        try {
            Account account = new Account();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                account.setAccountId(rs.getInt("account_id"));
                account.setAccountRole(rs.getString("account_role"));
                account.setEmail(rs.getString("email"));
                account.setFullName(rs.getString("full_name"));
                account.setPhone(rs.getString("phone"));
                boolean checkPassword = Helper.verifyPassword(dto.getPassword(), rs.getString("hashed_password"));
                if (checkPassword == true) {
                    auth.setAccount(account);
                    String access_token = this.createAccessToken(account);
                    String refresh_token = this.createRefreshToken(account);
                    auth.setRefreshToken(refresh_token);
                    auth.setAccessToken(access_token);
                    return auth;
                }
            }
        } catch (SQLException e) {
        }

        return null;
    }

    @Override
    public Auth register(RegisterDTO dto) {
        Auth auth = new Auth();

        String sql1 = "select * from accounts where email = '" + dto.getEmail() + "'";
        try {
            Account account = new Account();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql1);
            if (!rs.next()) {
                String sql2 = "insert into accounts(email, hashed_password, full_name, phone) "
                        + "values (?,?,?,?)";
                PreparedStatement pstmt = conn.prepareStatement(sql2);
                pstmt.setString(1, dto.getEmail());
                pstmt.setString(2, Helper.hashPassword(dto.getPassword()));
                pstmt.setString(3, dto.getPhone());
                pstmt.setString(4, dto.getFullName());
                int affectedRows = pstmt.executeUpdate();
                if(affectedRows > 0){
                    rs = stmt.executeQuery(sql1);
                    if(rs.next()){
                        account.setAccountId(rs.getInt("account_id"));
                        account.setAccountRole(rs.getString("account_role"));
                        account.setEmail(rs.getString("email"));
                        account.setFullName(rs.getString("full_name"));
                        account.setPhone(rs.getString("phone"));
                        auth.setAccount(account);
                        String access_token = this.createAccessToken(account);
                        String refresh_token = this.createRefreshToken(account);
                        auth.setRefreshToken(refresh_token);
                        auth.setAccessToken(access_token);
                        return auth;
                    }
                }
            }
        } catch (SQLException e) {
        }

        return null;
    }

    @Override
    public void logout() {

    }

}
