package com.example.Service.Impl;

import com.example.Entity.User;
import com.example.Repository.UserRepo;
import com.example.RequestDTO.LoginRequest;
import com.example.RequestDTO.SignupRequest;
import com.example.ResponseDTO.AuthResponse;
import com.example.Service.AuthService;
import com.example.config.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final CustomUserService customUserService;


    @Override
    public String createUser(SignupRequest signupRequest) throws Exception {
        User user = userRepo.findByEmail(signupRequest.getEmail());

        if (user != null) {
            throw new Exception("Email already in use");
        }
        User newUser = new User();
        newUser.setEmail(signupRequest.getEmail());
        newUser.setFull_name(signupRequest.getFull_name());
        newUser.setPassword(passwordEncoder.encode(signupRequest.getPassword()));

        user = userRepo.save(newUser);

        Authentication auth = new UsernamePasswordAuthenticationToken(signupRequest.getEmail(), signupRequest.getPassword());
        SecurityContextHolder.getContext().setAuthentication(auth);


        return tokenProvider.generateToken(auth);
    }

    @Override
    public AuthResponse signing(LoginRequest request) {
        UserDetails userDetails = customUserService.loadUserByUsername(request.getEmail());

        if (userDetails == null) {
            throw new BadCredentialsException("Invalid email :" + request.getEmail());
        }
        if (!passwordEncoder.matches(request.getPassword(), userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password :" + request.getPassword());
        }
        Authentication auth = new UsernamePasswordAuthenticationToken(request.getEmail(), null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        String token = tokenProvider.generateToken(auth);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setAuth(true);
        authResponse.setJwt(token);

        return authResponse;
    }


}
