package com.jn.bktravels.Service;


import com.jn.bktravels.Config.PasswordEncoderConfig;
import com.jn.bktravels.Model.User;
import com.jn.bktravels.Repository.UserRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserLoginService {


    private final UserRepo  userRepo;

    private final PasswordEncoderConfig passwordEncoderConfig;

    public UserLoginService(UserRepo userRepo, PasswordEncoderConfig passwordEncoderConfig) {
        this.userRepo = userRepo;
        this.passwordEncoderConfig = passwordEncoderConfig;
    }


    public ResponseEntity<?> userLogin(User user, HttpSession httpSession){
        User userExists = userRepo.findByUsername(user.getUsername());


        // Check if the user exists
        if (userExists == null) {
            return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        }

        // Check if the password matches
        if (passwordEncoderConfig.matches(user.getPassword(), userExists.getPassword())) {
            {
                var id= userExists.getId();
                HashMap<String, Object> map = new HashMap<>();
                map.put("id", id);
                map.put("username", user.getUsername());
                map.put("session", httpSession.getId());

                return new ResponseEntity<>(map, HttpStatus.OK);
            }

        } else {
            return new ResponseEntity<>("Wrong Password", HttpStatus.FORBIDDEN);
        }
    }
    }
