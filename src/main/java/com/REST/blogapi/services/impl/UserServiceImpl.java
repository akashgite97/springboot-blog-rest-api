package com.REST.blogapi.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.REST.blogapi.entities.User;
import com.REST.blogapi.exceptions.ResourceNotFoundException;
import com.REST.blogapi.payloads.dto.UserDto;
import com.REST.blogapi.repositories.UserRepo;
import com.REST.blogapi.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelmapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        
        User user = this.dtoToUser(userDto);
        User savedUser = this.userRepo.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        
        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","userId",userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User updatedUser = this.userRepo.save(user);
    
        return this.userToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","userId",userId));
       
        return this.userToDto(user);
    }

   @Override
    public List<UserDto> getAllUsers() {
     
        List<User> users = this.userRepo.findAll();
        
        return users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());

    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","userId",userId));
        
        this.userRepo.delete(user);
    }


    private User dtoToUser(UserDto userDto){

        User user  = this.modelmapper.map(userDto, User.class);

        // user.setId(userDto.getId());
        // user.setName(userDto.getName());
        // user.setEmail(userDto.getEmail());
        // user.setPassword(userDto.getPassword());
        // user.setAbout(userDto.getAbout());

        return user;
    }

    private UserDto userToDto(User user){
        
        UserDto userDto = this.modelmapper.map(user, UserDto.class);
        return userDto;
    }
    
}
