package com.security.service.impl;

import com.security.dto.LoginRequestDto;
import com.security.dto.LoginResponseDto;
import com.security.dto.SignupRequestDto;
import com.security.dto.SignupResponseDto;
import com.security.service.AuthService;
import com.security.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import com.security.entity.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(),loginRequestDto.getPassword())
        );
        User user = (User) authentication.getPrincipal();  //Type casting to User
        String token = authUtil.generateAccessToken(user);
        return new LoginResponseDto(token, user.getId());

    }

    @Override
    public SignupResponseDto signup(SignupRequestDto signupRequestDto) {
        return null;
    }
}
