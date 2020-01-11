package com.example.omnicash.controller;

import com.example.omnicash.model.User;
import com.example.omnicash.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register/{name}/{phoneno}")
    public String registerDetails(@PathVariable(value = "name") String Name,
                                  @PathVariable(value = "phoneno") String Phone){
    User user=new User();
    user.setContact(Phone);
    user.setUserName(Name);
    userRepository.save(user);
    return "Success";

    }
}
