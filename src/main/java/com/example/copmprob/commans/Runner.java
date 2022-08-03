package com.example.copmprob.commans;

import com.example.copmprob.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {
    private final UserService userService;

    public Runner(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.userService.initFirstUser();
    }
}
