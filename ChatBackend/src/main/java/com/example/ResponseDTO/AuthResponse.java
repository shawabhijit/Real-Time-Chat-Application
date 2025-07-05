package com.example.ResponseDTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AuthResponse {

    private String jwt;
    private boolean isAuth;

}
