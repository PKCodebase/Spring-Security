package com.security.service;


import com.security.dto.LoginRequestDto;
import com.security.dto.LoginResponseDto;
import com.security.dto.SignupResponseDto;

public interface AuthService {

    LoginResponseDto login(LoginRequestDto loginRequestDto);

    SignupResponseDto signup(LoginRequestDto signupRequestDto);
}
