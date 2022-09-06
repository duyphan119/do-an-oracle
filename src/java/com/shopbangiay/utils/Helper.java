/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopbangiay.utils;

import java.io.BufferedReader;
import java.io.IOException;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author ACER
 */
public class Helper {
    public static final int SALT = 10;
    
    public static String hashPassword(String rawPassword){
        return BCrypt.hashpw(rawPassword, BCrypt.gensalt(SALT));
    }
    
    public static boolean verifyPassword(String rawPassword, String hashedPassword){
        return BCrypt.checkpw(rawPassword, hashedPassword);
    }
    
    public static String getRequestBodyJSON(BufferedReader reader) throws IOException {
        StringBuilder buffer = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        return buffer.toString();
    }
}
