package com.REST.blogapi.services;

import java.util.List;

import com.REST.blogapi.payloads.dto.UserDto;

public interface UserService {
    
    UserDto createUser(UserDto user);

    UserDto updateUser(UserDto user, Integer id);

    UserDto getUserById(Integer id);

    List<UserDto> getAllUsers();

    void deleteUser(Integer id);
}
