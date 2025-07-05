package com.example.Repository;

import com.example.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

    public User findByEmail(String username);

    @Query("SELECT u FROM User u WHERE u.full_name LIKE %:name% OR u.email LIKE %:name%")
    public List<User> searchUsers(@Param("name") String name);
}
