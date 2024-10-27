package com.jn.bktravels.Service;

import com.jn.bktravels.Config.PasswordEncoderConfig;
import com.jn.bktravels.Model.User;
import com.jn.bktravels.Repository.UserRepo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRegisterService {
    @Autowired
    private  UserRepo userRepo;

    @Autowired
    private PasswordEncoderConfig passwordEncoderConfig;

    public ResponseEntity<?> registerUser(User user) {
        Optional<User> emailExists = Optional.ofNullable(userRepo.findByEmail(user.getEmail()));
        Optional <User> usernameExists = Optional.ofNullable(userRepo.findByUsername(user.getUsername()));
        if (emailExists.isPresent() && usernameExists.isPresent()) {
            return ResponseEntity.badRequest().body("Email and Username Already Exists");
        }
        else if (emailExists.isPresent()) {
            return ResponseEntity.badRequest().body("Email Already Exists");
        }
        else if (usernameExists.isPresent()) {
            return ResponseEntity.badRequest().body("Username Already Exists");
        }

        try {
            String plainPassword = user.getPassword();
            user.setPassword(passwordEncoderConfig.encodePassword(plainPassword));
            userRepo.save(user);

            return ResponseEntity.ok("User Registered Successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("User Registration Failed");
        }
    }
}
