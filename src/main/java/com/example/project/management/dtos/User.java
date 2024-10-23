package com.example.project.management.dtos;

import com.example.project.management.enums.Role;
import com.example.project.management.enums.Status;
import lombok.Data;

import java.util.Date;

/**
 * Dto class for user.
 */
@Data
public class User {
    private Long id;
    private String username;
    private String email;
    private String password;
    private Role role;
    private Date createdAt;
}
