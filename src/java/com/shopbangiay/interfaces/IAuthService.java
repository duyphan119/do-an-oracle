package com.shopbangiay.interfaces;

import com.shopbangiay.dtos.auth.LoginDTO;
import com.shopbangiay.dtos.auth.RegisterDTO;
import com.shopbangiay.models.Auth;

public interface IAuthService {
    Auth login(LoginDTO dto);
    
    Auth register(RegisterDTO dto);
    
    void logout();
}
