package com.jn.bktravels.Mapper;

import com.jn.bktravels.Model.User;
import com.jn.bktravels.dtos.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserToResponseUser {

    public UserResponseDto toUserResponseDto(User user){

        return UserResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                        .build();

    }
}
