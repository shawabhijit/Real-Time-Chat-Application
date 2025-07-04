package com.example.Service;

import com.example.Entity.User;
import com.example.Exception.UserException;
import com.example.RequestDTO.UpdateUserRequest;

import java.util.List;


public interface UserService {

    public User findUserById(Long id) throws UserException;

    public User findUserProfile(String jwtToken) throws UserException;

    public void updateUser(Long userId, UpdateUserRequest req) throws UserException;

    public List<User> searchUser(String query);
}
