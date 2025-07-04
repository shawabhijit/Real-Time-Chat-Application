package com.example.Service;

import com.example.Entity.User;
import com.example.Exception.UserException;
import com.example.RequestDTO.UpdateUserRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    public User findUserById(Long id);

    public User findUserProfile(String jwtToken);

    public User updateUser(Long userId, UpdateUserRequest req) throws UserException;

    public List<User> searchUser(String query);
}
