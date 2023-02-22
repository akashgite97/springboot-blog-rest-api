package com.REST.blogapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.REST.blogapi.payloads.UserDto;
import com.REST.blogapi.services.UserService;
import com.REST.blogapi.services.impl.UserServiceImpl;

@RestController
@RequestMapping("/api/user")
@ComponentScan(basePackageClasses =UserServiceImpl.class)
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        
        UserDto user = this.userService.createUser(userDto);
        
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/test")
    public String test(){
    return "working";
    }
}
