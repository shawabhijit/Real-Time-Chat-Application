package com.example.Service;

import com.example.RequestDTO.LoginRequest;
import com.example.RequestDTO.SignupRequest;
import com.example.ResponseDTO.AuthResponse;

public interface AuthService {

    public String createUser(SignupRequest signupRequest) throws Exception;

    public AuthResponse signing(LoginRequest request);
}
