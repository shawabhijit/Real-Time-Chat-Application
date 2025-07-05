package com.example.Controller;

import com.example.Entity.User;
import com.example.Repository.UserRepo;
import com.example.RequestDTO.LoginRequest;
import com.example.RequestDTO.SignupRequest;
import com.example.ResponseDTO.AuthResponse;
import com.example.Service.AuthService;
import com.example.Service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUser(@RequestBody SignupRequest request , HttpServletResponse res) throws Exception {
        String token = authService.createUser(request);

        ResponseCookie cookie = ResponseCookie.from("token", token)
                .httpOnly(true)
                .secure(true)
                .sameSite("None")
                .path("/")
                .maxAge(Duration.ofDays(5))
                .build();

        res.addHeader(HttpHeaders.SET_COOKIE , cookie.toString());

        AuthResponse response = new AuthResponse();
        response.setJwt(token);
        response.setAuth(true);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> userLogin(@RequestBody LoginRequest request , HttpServletResponse res) throws Exception {
        AuthResponse response = authService.signing(request);

        ResponseCookie cookie = ResponseCookie.from("token", response.getJwt())
                .httpOnly(true)
                .secure(true)
                .sameSite("None")
                .path("/")
                .maxAge(Duration.ofDays(5))
                .build();

        res.addHeader(HttpHeaders.SET_COOKIE , cookie.toString());

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> userLogout (@CookieValue(name = "token" , required = false) String jwt , HttpServletResponse response) throws Exception {
        if (jwt != null) {
            ResponseCookie cookie = ResponseCookie.from("token", jwt)
                    .httpOnly(true)
                    .secure(true)
                    .sameSite("None")
                    .path("/")
                    .maxAge(0)
                    .build();

            response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        }

        return ResponseEntity.ok().body("Logged out successfully");
    }
}
