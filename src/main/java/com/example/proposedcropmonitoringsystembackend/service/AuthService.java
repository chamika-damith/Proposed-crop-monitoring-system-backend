package com.example.proposedcropmonitoringsystembackend.service;


import com.example.proposedcropmonitoringsystembackend.dto.impl.UserDTO;
import com.example.proposedcropmonitoringsystembackend.secure.JWTAuthResponse;
import com.example.proposedcropmonitoringsystembackend.secure.SignIn;

public interface AuthService {
   JWTAuthResponse signIn(SignIn signIn);
   JWTAuthResponse signUp(UserDTO userDTO);
   JWTAuthResponse refreshToken(String accessToken);
}
