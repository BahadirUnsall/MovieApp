package com.bahadir.movieapp.convertor;

import com.bahadir.movieapp.dto.request.RegisterRequestDto;
import com.bahadir.movieapp.entity.User;

public class UserConvertor {
    public User dtoToUser(RegisterRequestDto dto){
        if (dto == null){
            return null;
        }

        User user = User.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .build();
        return user;
    }
}
