package com.tawhid.crudusingspringbootreactjs.controller;

import com.tawhid.crudusingspringbootreactjs.model.User;
import com.tawhid.crudusingspringbootreactjs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    User newUser(@RequestBody User newUser){
        return userRepository.save(newUser);
    }
    @GetMapping("/users")
        List<User> getAllUsers(){
            return userRepository.findAll();
        }


}
