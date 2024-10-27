package com.jn.bktravels.Service;


import com.jn.bktravels.Config.PasswordEncoderConfig;
import com.jn.bktravels.Model.User;
import com.jn.bktravels.Repository.UserRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
            return ResponseEntity.badRequest().body("User Not Found");
        }

        // Check if the password matches
        if (passwordEncoderConfig.matches(user.getPassword(), userExists.getPassword())) {
            {

                return ResponseEntity.ok().header("Access-Control-Allow-Origin", "*").body(httpSession.getId());
            }

        } else {
            return ResponseEntity.badRequest().body("Invalid Credentials");
        }
    }
    }
