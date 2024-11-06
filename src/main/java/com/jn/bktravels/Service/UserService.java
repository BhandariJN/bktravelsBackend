package com.jn.bktravels.Service;


import com.jn.bktravels.Mapper.UserToResponseUser;
import com.jn.bktravels.Model.User;
import com.jn.bktravels.Repository.UserRepo;
import com.jn.bktravels.dtos.UserResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final UserToResponseUser userToResponseUser;

    public UserService(UserRepo userRepo, UserToResponseUser userToResponseUser) {
        this.userRepo = userRepo;
        this.userToResponseUser = userToResponseUser;
    }

    public List<UserResponseDto> getAllUsers() {

        List<User> users = userRepo.findAll();
        return users.stream()
                .map(userToResponseUser::toUserResponseDto)
                .collect(Collectors.toList());
    }

}
