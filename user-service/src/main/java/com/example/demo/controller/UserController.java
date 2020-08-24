package com.example.demo.controller;


import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.shared.UserRequestModel;
import com.example.demo.shared.UserResponseModel;

@RestController
@RequestMapping("/api")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/users")
    public ResponseEntity<UserResponseModel> createUser(@RequestBody UserRequestModel userRequestModel)
    {
        return new ResponseEntity<UserResponseModel>(userService.createNewUser(userRequestModel), HttpStatus.CREATED);
    }
    
    @GetMapping("/users")
    public ResponseEntity<List<UserResponseModel>> getAllUsers()
    {
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }
    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponseModel> getUserByUserId(@PathVariable("userId") String userId) throws UserNotFoundException {
        UserResponseModel user=userService.findUserById(userId);
        if (user==null)
        {
            throw new UserNotFoundException("user not found with id "+userId);
        }
        return new ResponseEntity<UserResponseModel>(userService.findUserById(userId),HttpStatus.FOUND);
    }
}
