package com.jn.bktravels;

import com.github.javafaker.Faker;
import com.jn.bktravels.Model.User;
import com.jn.bktravels.Repository.UserRepo;
import com.jn.bktravels.Service.UserRegisterService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BkTravelsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BkTravelsApplication.class, args);
    }
   // @Bean
    CommandLineRunner commandLineRunner(UserRepo userRepo,
    UserRegisterService userRegisterService) {

        return args -> {
for(int i=0;i<10;i++){
    Faker faker = new Faker();
    User user = User.builder()
            .email(faker.internet().emailAddress())
            .username(faker.name().username())
            .password("sabin123")
            .build();
    userRegisterService.registerUser(user);
}

        };

    }

}
