package com.jn.bktravels.Service;


import com.jn.bktravels.dtos.AdminDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdminLoginService {
    public ResponseEntity<?> adminLogin(AdminDto adminDto, HttpSession httpSession) {

        if (adminDto.getUsername().equals("superadmin") || adminDto.getUsername().equals("admin")) {
            if (adminDto.getPassword().equals("bktravels@2024")) {
                return ResponseEntity.ok(httpSession.getId());
            } else {
                return new ResponseEntity<>("Password Doesnt Match", HttpStatus.FORBIDDEN);
            }
        }
        else {
            return new ResponseEntity<>("Username Not Found", HttpStatus.NOT_FOUND);
        }
    }
}
