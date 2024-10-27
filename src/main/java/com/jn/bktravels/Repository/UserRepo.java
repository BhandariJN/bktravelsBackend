package com.jn.bktravels.Repository;

import com.jn.bktravels.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    User findByEmail(String email);
}
