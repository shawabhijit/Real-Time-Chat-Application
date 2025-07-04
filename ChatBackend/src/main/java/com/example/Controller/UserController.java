package com.example.Controller;

import com.example.Entity.User;
import com.example.Exception.UserException;
import com.example.RequestDTO.UpdateUserRequest;
import com.example.ResponseDTO.ApiResponse;
import com.example.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfile (@CookieValue(name = "jwt" , required = false) String token) throws UserException {
        User user = userService.findUserProfile(token);
        return ResponseEntity.accepted().body(user);
    }

    @GetMapping("/{query}")
    public ResponseEntity<List<User>> searchUserHandler (@PathVariable("query") String query) {
        return ResponseEntity.ok().body(userService.searchUser(query));
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateUserHandler (@RequestBody UpdateUserRequest req ,
                                                          @CookieValue(name = "jwt" , required = false) String token) throws UserException {
        User user = getUserProfile(token).getBody();

        assert user != null;
        userService.updateUser(user.getId(), req);
        return ResponseEntity.accepted().body(new ApiResponse("user updated successfully",true));
    }
}
