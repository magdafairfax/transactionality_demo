package com.example.transactionality_demo.runner;

import com.example.transactionality_demo.model.User;
import com.example.transactionality_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class UserRunner implements CommandLineRunner {
    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        try {
            User user1 = new User("Ion", "Sales", 2000L);
            User user2 = new User("Sam", "Tech", 3000L);

            userService.insert(Arrays.asList(user1, user2));
        }
        catch (RuntimeException exception){
            System.out.println("Exception in batch 1 ... !" +  exception.getMessage());
        }

        try {
            User user3 = new User("Ryan King", "Ops", 1200L);
            User user4 = new User("Nick", "Ops", 12000L);

            userService.insert(Arrays.asList(user3, user4));
        }
        catch (RuntimeException exception){
            System.out.println("Exception in batch 2 ... !" +  exception.getMessage());
        }
        System.out.println(userService.getUsers());
    }
}
