package com.security.dto;

import lombok.Data;

@Data
public class LoginResponseDto {

     String jwt;

     Long userId;


    public LoginResponseDto(String token, Long id) {
    }
}
