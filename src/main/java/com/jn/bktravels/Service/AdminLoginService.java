package com.jn.bktravels.Service;


import com.jn.bktravels.Model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdminLoginService {
    public ResponseEntity<?> adminLogin(User user) {

        if (user.getUsername().equals("admin") && user.getPassword().equals("admin123456")) {

            return ResponseEntity.ok("Admin Login Successful");
        } else if (user.getUsername().equals("superadmin") && user.getPassword().equals("superadmin")) {
            return ResponseEntity.ok("Super Admin Login Successful");
        } else {
            return ResponseEntity.badRequest().body("Invalid Credentials");
        }

    }
}
