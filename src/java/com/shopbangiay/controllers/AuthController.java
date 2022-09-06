package com.shopbangiay.controllers;

import com.google.gson.Gson;
import com.shopbangiay.dtos.auth.LoginDTO;
import com.shopbangiay.dtos.auth.RegisterDTO;
import com.shopbangiay.models.Auth;
import com.shopbangiay.services.AuthService;
import com.shopbangiay.utils.Helper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpCookie;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.CookieValue;

@Controller
public class AuthController {

    Gson gson = new Gson();

    AuthService auth_service = new AuthService();
   
    @RequestMapping(value = "/auth/login", method = RequestMethod.POST)
//    @CookieValue
    public ResponseEntity<String> login(HttpServletRequest req, HttpServletResponse res) throws IOException {
        LoginDTO dto = gson.fromJson(Helper.getRequestBodyJSON(req.getReader()), LoginDTO.class);
        Auth auth = auth_service.login(dto);
        if(auth == null){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Cookie cookie = new Cookie("refresh_token",auth.getRefreshToken());
        cookie.setHttpOnly(true);
        cookie.setMaxAge(365 * 24 * 60 * 60 * 1000);
        cookie.setSecure(false);
        res.addCookie(cookie);
        return new ResponseEntity<>(gson.toJson(auth), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/auth/register", method = RequestMethod.POST)
//    @CookieValue
    public ResponseEntity<String> register(HttpServletRequest req, HttpServletResponse res) throws IOException {
        RegisterDTO dto = gson.fromJson(Helper.getRequestBodyJSON(req.getReader()), RegisterDTO.class);
        Auth auth = auth_service.register(dto);
        if(auth == null){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Cookie cookie = new Cookie("refresh_token",auth.getRefreshToken());
        cookie.setHttpOnly(true);
        cookie.setMaxAge(365 * 24 * 60 * 60 * 1000);
        cookie.setSecure(false);
        res.addCookie(cookie);
        return new ResponseEntity<>(gson.toJson(auth), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/auth/refresh-token", method = RequestMethod.POST)
//    @CookieValue
    public ResponseEntity<String> refreshToken(HttpServletRequest req, HttpServletResponse res) throws IOException {
        RegisterDTO dto = gson.fromJson(Helper.getRequestBodyJSON(req.getReader()), RegisterDTO.class);
        Auth auth = auth_service.register(dto);
        if(auth == null){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Cookie cookie = new Cookie("refresh_token",auth.getRefreshToken());
        cookie.setHttpOnly(true);
        cookie.setMaxAge(365 * 24 * 60 * 60 * 1000);
        cookie.setSecure(false);
        res.addCookie(cookie);
        return new ResponseEntity<>(gson.toJson(auth), HttpStatus.OK);
    }
}
