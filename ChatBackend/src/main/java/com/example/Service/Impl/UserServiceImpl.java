package com.example.Service.Impl;

import com.example.Entity.User;
import com.example.Exception.UserException;
import com.example.RequestDTO.UpdateUserRequest;
import com.example.Service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    @Override
    public User findUserById(Long id) {
        return null;
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
