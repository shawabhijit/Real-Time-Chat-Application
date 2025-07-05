package com.example.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String full_name;
    private String email;
    private String profile_picture;
    private String password;
    private String bio = " Hello Everyone!Send me a Message";

    //@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    //private List<Notification> notifications= new ArrayList<>();
}
