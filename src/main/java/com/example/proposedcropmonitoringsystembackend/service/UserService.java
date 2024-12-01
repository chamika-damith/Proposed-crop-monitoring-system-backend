package com.example.proposedcropmonitoringsystembackend.service;

import com.example.proposedcropmonitoringsystembackend.dto.impl.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends CRUDUtil<UserDTO>{
    UserDetailsService userDetailsService();
    UserDTO getActiveUsers();
}
