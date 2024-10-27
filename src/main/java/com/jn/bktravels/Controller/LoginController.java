package com.jn.bktravels.Controller;


import com.jn.bktravels.Service.AdminLoginService;
import com.jn.bktravels.Service.UserLoginService;
import com.jn.bktravels.dtos.UserDto;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@CrossOrigin
public class LoginController {

    private final UserLoginService userLoginService;

    private final AdminLoginService adminLoginService;
    private final HttpSession httpSession;

    public LoginController(UserLoginService userLoginService, AdminLoginService adminLoginService, HttpSession httpSession) {
        this.userLoginService = userLoginService;
        this.adminLoginService = adminLoginService;
        this.httpSession = httpSession;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login( @RequestBody @Valid UserDto user, HttpSession session) {
        System.out.println("User: " + user);





        // Check if the user is an admin or superadmin
        ResponseEntity<?> response;
        if (user.getUsername().equals("admin") || user.getUsername().equals("superadmin")) {

            response = adminLoginService.adminLogin(user.toEntity());
        } else {
            response = userLoginService

                    .userLogin(user.toEntity(), session );
        }

        if (response.getStatusCode().is2xxSuccessful()) {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", "Login Successful");
            responseBody.put("username", user.getUsername());
            responseBody.put("session", session.getId());
            return ResponseEntity.ok(responseBody);
        }

        return response;
    }
}
