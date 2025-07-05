package com.example.RequestDTO;

import lombok.Data;

@Data
public class SignupRequest {
    private String full_name;
    private String email;
    private String password;
}
