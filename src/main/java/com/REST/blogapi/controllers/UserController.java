package com.REST.blogapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.REST.blogapi.payloads.UserDto;
import com.REST.blogapi.services.UserService;
import com.REST.blogapi.services.impl.UserServiceImpl;

@RestController
@RequestMapping("/api/user")
@ComponentScan(basePackageClasses = UserServiceImpl.class)
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {

        UserDto user = this.userService.createUser(userDto);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<UserDto> createUser(@PathVariable int userId) {

        UserDto user = this.userService.getUserById(userId);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {

        List<UserDto> users = this.userService.getAllUsers();

        return new ResponseEntity<>(users, HttpStatus.CREATED);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable int userId) {

        UserDto user = this.userService.updateUser(userDto, userId);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{userId}")
    public void deleteuser(@PathVariable int userId) {

        this.userService.deleteUser(userId);

    }

    @GetMapping("/test")
    public String test() {
        return "working";
    }
}
