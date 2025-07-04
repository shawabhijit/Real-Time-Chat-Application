package com.example.Service.Impl;

import com.example.Entity.User;
import com.example.Exception.UserException;
import com.example.Repository.UserRepo;
import com.example.RequestDTO.UpdateUserRequest;
import com.example.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public User findUserById(Long id) throws UserException{
        return userRepo.findById(id).orElseThrow(() -> new UserException("User Not Found with ID :" + id) );
    }

    @Override
    public User findUserProfile(String jwtToken) {
        return null;
    }

    @Override
    public User updateUser(Long userId, UpdateUserRequest req) throws UserException {
        return null;
    }

    @Override
    public List<User> searchUser(String query) {
        return List.of();
    }
}
