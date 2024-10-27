package com.jn.bktravels.Controller;

import com.jn.bktravels.Service.UserRegisterService;
import com.jn.bktravels.dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class RegisterController {

    @Autowired
    private UserRegisterService userRegisterService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Validated @RequestBody UserDto user) {
        Map<String, String> response = new HashMap<>();
        try {

         return  new ResponseEntity<>(userRegisterService.registerUser(user.toEntity()), HttpStatus.CREATED);


        } catch (Exception e) {
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
