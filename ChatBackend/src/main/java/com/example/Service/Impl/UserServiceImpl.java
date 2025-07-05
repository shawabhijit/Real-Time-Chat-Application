package com.example.Service.Impl;

import com.example.Entity.User;
import com.example.Exception.UserException;
import com.example.Repository.UserRepo;
import com.example.RequestDTO.UpdateUserRequest;
import com.example.Service.UserService;
import com.example.config.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final TokenProvider tokenProvider;

    @Override
    public User findUserById(Long id) throws UserException{
        return userRepo.findById(id).orElseThrow(() -> new UserException("User Not Found with ID :" + id) );
    }

    @Override
    public User findUserProfile(String jwtToken) throws UserException {
        String email = tokenProvider.getEmailFromJwtToken(jwtToken);
        if (email != null) {
            return userRepo.findByEmail(email);
        }
        else {
            throw new BadCredentialsException("Bad Credentials , email not found.");
        }
    }

    @Override
    public void updateUser(Long userId, UpdateUserRequest req) throws UserException {
        User user = findUserById(userId);

        if (req.getFull_name() != null) {
            user.setFull_name(req.getFull_name());
        }
        if (req.getBio() != null) {
            user.setBio(req.getBio());
        }
        if (req.getProfile_picture() != null) {
            user.setProfile_picture(req.getProfile_picture());
        }
        userRepo.save(user);
    }

    @Override
    public List<User> searchUser(String name) {
        return userRepo.searchUsers(name);
    }
}
