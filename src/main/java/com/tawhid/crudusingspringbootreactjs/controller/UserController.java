package com.tawhid.crudusingspringbootreactjs.controller;

import com.tawhid.crudusingspringbootreactjs.execption.UserNotFoundException;
import com.tawhid.crudusingspringbootreactjs.model.User;
import com.tawhid.crudusingspringbootreactjs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("https://crud-springboot-fullstack.netlify.app")
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
    @GetMapping("/user/{id}")
    User getUserByID(@PathVariable Long id){
        return userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException(id));
    }

    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id){
        return userRepository.findById(id)
                .map(user ->{
                    user.setUsername(newUser.getUsername());
                    user.setName(newUser.getName());
                    user.setMail(newUser.getMail());
                    return userRepository.save(user);
                }).orElseThrow(() ->new UserNotFoundException(id));
    }
    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id){
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return "User with id "+id+" has been successfully deleted";
    }


}
